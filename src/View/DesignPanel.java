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
        System.out.println("Drawn one");
    }

    public void drawConnection(int from, int to, ConnectionType type) {
        UserClass a = DrawnClasses.getInstance().getClassByID(from);
        UserClass b = DrawnClasses.getInstance().getClassByID(to);
        GlobalStatus.getInstance().setDrawStatus("Connecting " + a.getTitle() + " with " + b.getTitle());
        ConnectionGeometryProcessor connectionProcessor = new ConnectionGeometryProcessor(a,b);
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
        System.out.println("Finish connection");
    }

    @Override
    public void redraw() {
        DrawnClasses drawnClasses = DrawnClasses.getInstance();
        int numClasses = drawnClasses.getLength();
        System.out.println(numClasses);
        for (int i=0; i<numClasses; i++) {
            UserClass userClass = drawnClasses.getClassByID(i);
            drawRectangle(userClass.xCoord(), userClass.yCoord(), userClass.getTitle());
            ArrayList<Connection> connections = userClass.getConnections();
            System.out.println(connections.size());
            for (int j=0; j < connections.size(); j++) {
                drawConnection(i, connections.get(j).getToID(), connections.get(j).getType());
                System.out.println(j);
            }
        }
        System.out.println("Redrawn da");
    }

    public void clearAll() {
//        this.clearAll();
        repaint();
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        System.out.println("Finish clear");
    }

}
