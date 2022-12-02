package Controller;

import Model.Point;
import Model.UserClass;
import Model.ViewConstants;

import java.util.ArrayList;

public class ConnectionGeometryProcessor {
    
    UserClass fromClass;
    UserClass toClass;
    int fromAngle;
    int toAngle;

    Point fromPoint;
    Point toPoint;
    
    public ConnectionGeometryProcessor(UserClass from, UserClass to) {
        this.fromClass = from;
        this.toClass = to;
        fromAngle = getAngle(to, from);
        toAngle = getAngle(from, to);
        findPositions(fromAngle, toAngle);
    }
    
    /// Calculates the angle between two coordinates
    private int getAngle(UserClass a, UserClass b) {
        int pivotX = a.xCoord() + 10;
        int pivotY = a.yCoord();
        double angle = ((Math.atan2(b.yCoord()-pivotY, b.xCoord()-pivotX) - (Math.atan2(a.yCoord()-pivotY, a.xCoord()-pivotX))) * 180.0) / Math.PI;
        return (int)(angle < 0.0 ? angle+360.0 : angle);
    }
    
    private void findPositions(int fromAngle, int toAngle) {

        if (fromAngle <= 45 || fromAngle > 315) {
            fromPoint = new Point(fromClass.xCoord() + (ViewConstants.userClassWidth/2), fromClass.yCoord());
        } else if (fromAngle <= 135) {
            fromPoint = new Point(fromClass.xCoord(), fromClass.yCoord() + (ViewConstants.userClassHeight/2));
        } else if (fromAngle <= 225) {
            fromPoint = new Point(fromClass.xCoord() - (ViewConstants.userClassWidth/2), fromClass.yCoord());
        } else {
            fromPoint = new Point(fromClass.xCoord(), fromClass.yCoord() - (ViewConstants.userClassHeight/2));
        }

        if (toAngle <= 45 || toAngle > 315) {
            toPoint = new Point(toClass.xCoord() + (ViewConstants.userClassWidth/2), toClass.yCoord());
        } else if (toAngle <= 135) {
            toPoint = new Point(toClass.xCoord(), toClass.yCoord() + (ViewConstants.userClassHeight/2));
        } else if (toAngle <= 225) {
            toPoint = new Point(toClass.xCoord() - (ViewConstants.userClassWidth/2), toClass.yCoord());
        } else {
            toPoint = new Point(toClass.xCoord(), toClass.yCoord() - (ViewConstants.userClassHeight/2));
        }
    }

    private Point generatePoint(double cosTheta, double sinTheta, Point pt, int i, int aMajor, int aMinor) {
        double radConv = Math.PI/180;
        double iCos = Math.cos(i*radConv);
        double iSin = Math.sin(i*radConv);
        int x1 = (int)(aMajor * iCos * cosTheta);
        int x2 = (int)(aMinor * iSin * sinTheta);
        int y1 = (int)(aMajor * iCos * sinTheta);
        int y2 = (int)(aMinor * iSin * cosTheta);
        return new Point(pt.xCoord()+x1-x2, pt.yCoord()+y1+y2);
    }

    public ArrayList<Point> findControlPoints(String side) {
        ArrayList<Point> points = new ArrayList<>();
        double radConv = Math.PI/180;
        if (side == "from") {
            double cosTheta = Math.cos(fromAngle*radConv);
            double sinTheta = Math.sin(fromAngle*radConv);
            points.add(generatePoint(cosTheta, sinTheta, fromPoint, 45, 15, 15));
            points.add(generatePoint(cosTheta, sinTheta, fromPoint, 0, 22, 22));
            points.add(generatePoint(cosTheta, sinTheta, fromPoint, 315, 15, 15));
        } else {
            double cosTheta = Math.cos(toAngle*radConv);
            double sinTheta = Math.sin(toAngle*radConv);
            points.add(generatePoint(cosTheta, sinTheta, toPoint, 45, 15, 15));
            points.add(generatePoint(cosTheta, sinTheta, toPoint, 315, 15, 15));
        }
        return points;
    }

    public Point getFromPoint() {
        return fromPoint;
    }

    public Point getToPoint() {
        return toPoint;
    }
}
