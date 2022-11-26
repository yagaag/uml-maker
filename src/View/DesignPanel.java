package View;

import Controller.ConnectionGeometryProcessor;
import Controller.DrawingPanelController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DesignPanel extends JPanel implements DrawingPanel {

    DrawingPanelController controller;
    DrawableComposite drawableComposite;

    public DesignPanel(int x, int y, int width, int height) {
        controller = new DrawingPanelController(this);
        this.setBounds(x,y,width,height);
        this.setBackground(ViewConstants.accentColor);
        this.addMouseListener(controller);
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

    public void drawConnection(int from, int to) {
        UserClass a = DrawnClasses.getInstance().getClassByID(from);
        UserClass b = DrawnClasses.getInstance().getClassByID(to);
        GlobalStatus.getInstance().setDrawStatus("Connecting " + a.getTitle() + " with " + b.getTitle());
        ConnectionGeometryProcessor connectionProcessor = new ConnectionGeometryProcessor(a,b);
        switch (GlobalStatus.getInstance().getConnectionType()) {
            case ASSOCIATION -> {
                DrawnClasses.getInstance().addConnection(from, to, ConnectionType.ASSOCIATION);
                drawableComposite = new DrawArrow();
                drawableComposite.addDrawable(new DrawLine());
                drawableComposite.draw(this, connectionProcessor);
            }
            case INHERITANCE -> {
                DrawnClasses.getInstance().addConnection(from, to, ConnectionType.INHERITANCE);
                drawableComposite = new DrawTriangle();
                drawableComposite.addDrawable(new DrawLine());
                drawableComposite.draw(this, connectionProcessor);
            }
            case COMPOSITION -> {
                DrawnClasses.getInstance().addConnection(from, to, ConnectionType.COMPOSITION);
                drawableComposite = new DrawDiamond();
                drawableComposite.addDrawable(new DrawArrow());
                drawableComposite.addDrawable(new DrawLine());
                drawableComposite.draw(this, connectionProcessor);
            }
        }
    }

    public void clearAll() {
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        controller.setPanelMode(PanelMode.NEW);
    }

}
