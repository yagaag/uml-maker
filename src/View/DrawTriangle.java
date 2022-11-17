package View;

import Controller.ConnectionGeometryProcessor;
import Model.Point;
import Model.UserClass;
import Model.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawTriangle extends DrawableComposite {

    /**
     * Draw in composed elements and then decorate each Point with square marks
     *
     * @param panel The JPanel to be drawn on
     * @param points The list of Points that need to be drawn
     */
    @Override
    public void draw(JPanel panel, ConnectionGeometryProcessor connectionProcessor) {
        super.draw(panel, connectionProcessor);
        Graphics g = panel.getGraphics();
        g.setColor(ViewConstants.accentColor);
        ArrayList<Point> points = connectionProcessor.findControlPoints("to");
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        xPoints[0] = points.get(0).xCoord();
        xPoints[1] = points.get(1).xCoord();
        xPoints[2] = connectionProcessor.getToPoint().xCoord();
        yPoints[0] = points.get(0).yCoord();
        yPoints[1] = points.get(1).yCoord();
        yPoints[2] = connectionProcessor.getToPoint().yCoord();
        g.fillPolygon(xPoints,yPoints,3);
        g.setColor(Color.white);
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
