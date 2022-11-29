package View;

import Controller.ConnectionGeometryProcessor;
import Controller.DrawingPanelController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DesignPanel extends JPanel implements DrawingPanel {

    DrawingPanelController controller;
    DrawableComposite drawableComposite;

    public DesignPanel(int x, int y, int width, int height) {
        controller = new DrawingPanelController(this);
        this.setBounds(x,y,width,height);
        this.setBackground(ViewConstants.accentColor);
        this.addMouseListener(controller);
        this.addMouseMotionListener(controller);
    }

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

    public void clearAll() {
        repaint();
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
    }
}
