package View;

import Controller.ConnectionGeometryProcessor;
import Model.Point;
import Model.UserClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawArrow extends DrawableComposite {

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
        g.setColor(Color.white);
        ArrayList<Point> points = connectionProcessor.findControlPoints("to");
        for (int i=0; i<points.size(); i++) {
            g.drawLine(
                    connectionProcessor.getToPoint().xCoord(),
                    connectionProcessor.getToPoint().yCoord(),
                    points.get(i).xCoord(),
                    points.get(i).yCoord()
            );
        }
    }
}
