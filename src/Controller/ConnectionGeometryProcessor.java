package Controller;

import Model.UserClass;
import Model.ViewConstants;

public class ConnectionGeometryProcessor {
    
    UserClass from;
    UserClass to;
    int fromX;
    int fromY;
    int toX;
    int toY;
    
    public ConnectionGeometryProcessor(UserClass from, UserClass to) {
        this.from = from;
        this.to = to;
        int fromAngle = getAngle(to, from);
        int toAngle = getAngle(from, to);
        System.out.println(fromAngle);
        System.out.println(toAngle);
        findPositions(fromAngle, toAngle);
    }
    
    /// Calculates the angle between two coordinates
    private int getAngle(UserClass a, UserClass b) {
        int pivotX = a.xCoord()+10;
        int pivotY = a.yCoord();
        double angle = ((Math.atan2(b.yCoord()-pivotY, b.xCoord()-pivotX) - (Math.atan2(a.yCoord()-pivotY, a.xCoord()-pivotX))) * 180.0) / Math.PI;
        return (int)(angle < 0.0 ? angle+360.0 : angle);
    }
    
    private void findPositions(int fromAngle, int toAngle) {

        if (fromAngle <= 45 || fromAngle > 315) {
            fromX = from.xCoord() + (ViewConstants.userClassWidth/2);
            fromY = from.yCoord();
        } else if (fromAngle <= 135) {
            fromX = from.xCoord();
            fromY = from.yCoord() + (ViewConstants.userClassHeight/2);
        } else if (fromAngle <= 225) {
            fromX = from.xCoord() - (ViewConstants.userClassWidth/2);
            fromY = from.yCoord();
        } else {
            fromX = from.xCoord();
            fromY = from.yCoord() - (ViewConstants.userClassHeight/2);
        }

        if (toAngle <= 45 || toAngle > 315) {
            toX = to.xCoord() + (ViewConstants.userClassWidth/2);
            toY = to.yCoord();
        } else if (toAngle <= 135) {
            toX = to.xCoord();
            toY = to.yCoord() + (ViewConstants.userClassHeight/2);
        } else if (toAngle <= 225) {
            toX = to.xCoord() - (ViewConstants.userClassWidth/2);
            toY = to.yCoord();
        } else {
            toX = to.xCoord();
            toY = to.yCoord() - (ViewConstants.userClassHeight/2);
        }
    }

    public void findControlPoints(String side) {
//        if (side == "from") {
//
//        } else {
//
//        }
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }
}
