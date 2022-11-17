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

    private void drawRectangle(int x, int y) {
        System.out.println("Drawing");
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
    }

    public void clearAll() {
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        mode = PanelMode.NEW;
    }

    public void makeConnection(int from, int to) {
        UserClass a = DrawnClasses.getInstance().getClassByID(from);
        UserClass b = DrawnClasses.getInstance().getClassByID(to);
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
            GlobalStatus.getInstance().setDrawStatus("Selected i");
            System.out.println("Connect mode");
            if (mode == PanelMode.CONNECT) {
                GlobalStatus.getInstance().setDrawStatus("Connecting i with j");
                System.out.println("Already in connect mode");
                int connectPanelID = processor.getLastClickPanelID();
                makeConnection(lastClickPanelID, connectPanelID);
                mode = PanelMode.NEW;
            }
            else {
                System.out.println("Entering connect mode");
                mode = PanelMode.CONNECT;
                lastClickPanelID = processor.getLastClickPanelID();
            }
        }
        else {
            GlobalStatus.getInstance().setDrawStatus("No class selected");
            mode = PanelMode.NEW;
            processor.newUserClass(e.getX(), e.getY());
            this.drawRectangle(e.getX(),e.getY());
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
