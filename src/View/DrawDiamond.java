package View;

import Controller.ConnectionGeometryProcessor;
import Model.UserClass;

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
        g.setColor(Color.red);

    }
}
