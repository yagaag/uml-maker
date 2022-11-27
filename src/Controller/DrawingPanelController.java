package Controller;

import Model.ConnectionType;
import Model.DrawnClasses;
import Model.GlobalStatus;
import Model.PanelMode;
import View.DrawingPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingPanelController implements MouseListener, MouseMotionListener {

    DrawingPanel panel;
    ClickEventProcessor processor = new ClickEventProcessor();
    int lastClickPanelID;
    int currentClickPanelID;
    PanelMode lastClickMode = PanelMode.NEW;
    PanelMode currentClickMode = PanelMode.NEW;
    boolean drag = false;

    public DrawingPanelController(DrawingPanel p) {
        panel = p;
    }

//    public void setPanelMode(PanelMode m) {
//        lastClickMode = m;
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Logged as click");
        drag = false;
        currentClickMode = processor.categoriseClickEvent(e.getX(), e.getY());
        if (currentClickMode == PanelMode.CONNECT) {
            currentClickPanelID = processor.getLastClickPanelID();
            String className = DrawnClasses.getInstance().getClassByID(currentClickPanelID).getTitle();
            GlobalStatus.getInstance().setDrawStatus("Selected " + className);
            if (lastClickMode == PanelMode.CONNECT) {
                ConnectionType type = GlobalStatus.getInstance().getConnectionType();
                DrawnClasses.getInstance().addConnection(lastClickPanelID, currentClickPanelID, type);
                panel.drawConnection(lastClickPanelID, currentClickPanelID, type);
                lastClickMode = PanelMode.NEW;
            }
            else {
                lastClickMode = PanelMode.CONNECT;
                lastClickPanelID = processor.getLastClickPanelID();
            }
        }
        else {
            if (processor.newUserClass(e.getX(),e.getY())) {
                panel.drawRectangle(e.getX(),e.getY(), processor.getLastClassName());
            }
            lastClickMode = PanelMode.NEW;
            GlobalStatus.getInstance().setDrawStatus("No class selected");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Logged as press");
        currentClickMode = processor.categoriseClickEvent(e.getX(), e.getY());
        if (currentClickMode == PanelMode.CONNECT) {
            currentClickPanelID = processor.lastClickPanelID;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Logged as release");
        if (drag) {
            System.out.println("Logged as drag release");
            System.out.println("Drag");
            if (currentClickMode == PanelMode.CONNECT) {
                DrawnClasses.getInstance().changeClassPosition(currentClickPanelID, e.getX(), e.getY());
//                panel.clearAll();
                panel.redraw();
                System.out.println("Exiting redraw");
                lastClickMode = PanelMode.NEW;
            }
        }
        else {
            System.out.println("Click");
        }
        drag = false;
        System.out.println(lastClickMode);
        System.out.println(currentClickMode);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Logged as drag");
        drag = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
