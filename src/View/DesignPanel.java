package View;

import Controller.ConnectionGeometryProcessor;
import Controller.DrawingPanelController;
import Model.*;

import javax.swing.*;
import java.awt.*;

/**
 * The panel in which UML can be designed
 *
 * @author yagaa
 * @version 1.0.0
 */
public class DesignPanel extends JPanel implements DrawingPanel {

    DrawingPanelController controller;
    DrawableComposite drawableComposite;

    /**
     * Sets up panel position and components
     *
     * @param x The start position of the panel (x-axis)
     * @param y The start position of the panel (y-axis)
     * @param width The width of the panel
     * @param height The height of the panel
     */
    public DesignPanel(int x, int y, int width, int height) {
        controller = new DrawingPanelController(this);
        this.setBounds(x,y,width,height);
        this.setBackground(ViewConstants.accentColor);
        this.addMouseListener(controller);
        this.addMouseMotionListener(controller);
    }

    /**
     * Draw a rectangle and add content to it
     *
     * @param x The start position of the rectangle (x-axis)
     * @param y The start position of the rectangle (y-axis)
     * @param content The text to be added to the rectangle
     */
    public void drawRectangle(int x, int y, String content) {
        Graphics g = this.getGraphics();
        g.setColor(ViewConstants.classColor);
        g.fillRect(
                x-(ViewConstants.userClassWidth/2),
                y-(ViewConstants.userClassHeight/2),
                ViewConstants.userClassWidth,
                ViewConstants.userClassHeight
        );
        g.drawRect(
                x-(ViewConstants.userClassWidth/2),
                y-(ViewConstants.userClassHeight/2),
                ViewConstants.userClassWidth,
                ViewConstants.userClassHeight
        );
        g.setColor(ViewConstants.textColor);
        g.drawString(content, x-(int)(content.length()*3.9), y+5);
    }

    /**
     * Draw a connection arrow
     *
     * @param from The UserClass from which the connection must start
     * @param to The UserClass which the connection must reach
     * @param type The ConnectionType of the connection
     */
    public void drawConnection(UserClass from, UserClass to, ConnectionType type) {
        ConnectionGeometryProcessor connectionProcessor = new ConnectionGeometryProcessor(from, to);
        switch (type) {
            case ASSOCIATION -> {
                drawableComposite = new DrawArrow();
                drawableComposite.addDrawable(new DrawLine());
                drawableComposite.draw(this, connectionProcessor);
            }
            case INHERITANCE -> {
                drawableComposite = new DrawTriangle();
                drawableComposite.addDrawable(new DrawLine());
                drawableComposite.draw(this, connectionProcessor);
            }
            case COMPOSITION -> {
                drawableComposite = new DrawDiamond();
                drawableComposite.addDrawable(new DrawArrow());
                drawableComposite.addDrawable(new DrawLine());
                drawableComposite.draw(this, connectionProcessor);
            }
        }
    }

    /**
     * Redraw all DrawnClasses
     */
    @Override
    public void redraw() {
        super.paintComponent(this.getGraphics());
        DrawnClasses drawnClasses = DrawnClasses.getInstance();
        for (UserClass userClass: drawnClasses.getClasses()) {
            drawRectangle(userClass.xCoord(), userClass.yCoord(), userClass.getTitle());
            for (Connection connection: userClass.getConnections()) {
                drawConnection(userClass, connection.getToClass(), connection.getType());
            }
        }
    }

    /**
     * Clears the panel
     */
    public void clearAll() {
        repaint();
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
    }
}
