package View;

import Controller.ClickEventProcessor;
import Controller.ConnectionGeometryProcessor;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DesignPanel extends JPanel implements MouseListener {

    PanelMode mode = PanelMode.NEW;
    ClickEventProcessor processor = new ClickEventProcessor();
    DrawableComposite drawableComposite;
    int lastClickPanelID;

    public DesignPanel(int x, int y, int width, int height) {
        this.setBounds(x,y,width,height);
        this.setBackground(ViewConstants.accentColor);
        this.addMouseListener(this);
    }

    private void drawRectangle(int x, int y, String className) {
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
        g.drawString(className, x-(int)(className.length()*3.9), y+5);
    }

    public void clearAll() {
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        mode = PanelMode.NEW;
    }

    public void makeConnection(int from, int to) {
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
            case AGGREGATION -> {
                DrawnClasses.getInstance().addConnection(from, to, ConnectionType.AGGREGATION);
                drawableComposite = new DrawDiamond();
                drawableComposite.addDrawable(new DrawArrow());
                drawableComposite.addDrawable(new DrawLine());
                drawableComposite.draw(this, connectionProcessor);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PanelMode clickMode = processor.categoriseClickEvent(e.getX(), e.getY());
        if (clickMode == PanelMode.CONNECT) {
            int connectPanelID = processor.getLastClickPanelID();
            String className = DrawnClasses.getInstance().getClassByID(connectPanelID).getTitle();
            GlobalStatus.getInstance().setDrawStatus("Selected " + className);
            if (mode == PanelMode.CONNECT) {
                makeConnection(lastClickPanelID, connectPanelID);
                mode = PanelMode.NEW;
            }
            else {
                mode = PanelMode.CONNECT;
                lastClickPanelID = processor.getLastClickPanelID();
            }
        }
        else {
            if (processor.newUserClass(e.getX(),e.getY())) {
                this.drawRectangle(e.getX(),e.getY(), processor.getLastClassName());
            }
            mode = PanelMode.NEW;
            GlobalStatus.getInstance().setDrawStatus("No class selected");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
