package View;

import Controller.ConnectionGeometryProcessor;
import Model.Point;
import Model.UserClass;
import Model.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawDiamond extends DrawableComposite {

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
        ArrayList<Point> points = connectionProcessor.findControlPoints("from");
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];
        xPoints[0] = points.get(0).xCoord();
        xPoints[1] = points.get(1).xCoord();
        xPoints[2] = points.get(2).xCoord();
        xPoints[3] = connectionProcessor.getFromPoint().xCoord();
        yPoints[0] = points.get(0).yCoord();
        yPoints[1] = points.get(1).yCoord();
        yPoints[2] = points.get(2).yCoord();
        yPoints[3] = connectionProcessor.getFromPoint().yCoord();
        g.setColor(Color.white);
        g.fillPolygon(xPoints,yPoints,4);
//        g.drawPolygon(xPoints, yPoints, 4);

    }
}
