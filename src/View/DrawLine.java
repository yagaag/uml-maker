package View;

import Controller.ConnectionGeometryProcessor;
import Model.DrawnClasses;
import Model.UserClass;

import javax.swing.*;
import java.awt.*;

/**
 * A Drawable the draws a line plot of Points
 *
 * @author yagaa
 * @version 1.0
 * @see DrawableComposite
 * @see Drawable
 */
public class DrawLine implements Drawable {

    /**
     * Draw a line plot of Points
     *
     * @param panel The JPanel to be drawn on
     * @param points The list of Points that need to be drawn
     */
    @Override
    public void draw(JPanel panel, ConnectionGeometryProcessor connectionProcessor) {
        Graphics g = panel.getGraphics();
        g.setColor(Color.white);
        g.drawLine(
                connectionProcessor.getFromPoint().xCoord(),
                connectionProcessor.getFromPoint().yCoord(),
                connectionProcessor.getToPoint().xCoord(),
                connectionProcessor.getToPoint().yCoord()
        );
    }
}
