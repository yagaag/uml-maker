package Controller;

import Model.*;
import View.PopupMenu;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingPanelController implements MouseListener, MouseMotionListener {
    int lastClickPanelID;
    int currentClickPanelID;
    PanelMode lastClickMode = PanelMode.NEW;
    PanelMode currentClickMode = PanelMode.NEW;
    boolean drag = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            PopupMenu menu = new PopupMenu();
            menu.show(e.getComponent(), e.getX(), e.getY());
        } else {
            drag = false;
            currentClickMode = ClickEventProcessor.getInstance().categoriseClickEvent(e.getX(), e.getY());
            if (currentClickMode == PanelMode.CONNECT) {
                DrawnClasses drawnClasses = DrawnClasses.getInstance();
                currentClickPanelID = ClickEventProcessor.getInstance().getLastClickPanelID();
                String className = drawnClasses.getClassByID(currentClickPanelID).getTitle();
                GlobalStatus.getInstance().setDrawStatus("Selected " + className);
                if (lastClickMode == PanelMode.CONNECT) {
                    ConnectionType type = GlobalStatus.getInstance().getConnectionType();
                    UserClass from = drawnClasses.getClassByID(lastClickPanelID);
                    UserClass to = drawnClasses.getClassByID(currentClickPanelID);
                    GlobalStatus.getInstance().setDrawStatus("Connected " + from.getTitle() + " with " + to.getTitle());
                    drawnClasses.addConnection(lastClickPanelID, currentClickPanelID, type);
                    //panel.drawConnection(from, to, type);
                    lastClickMode = PanelMode.NEW;
                } else {
                    lastClickMode = PanelMode.CONNECT;
                    lastClickPanelID = ClickEventProcessor.getInstance().getLastClickPanelID();
                }
            } else {
                ClickEventProcessor.getInstance().newUserClass(e.getX(),e.getY());
                lastClickMode = PanelMode.NEW;
                GlobalStatus.getInstance().setDrawStatus("No class selected");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentClickMode = ClickEventProcessor.getInstance().categoriseClickEvent(e.getX(), e.getY());
        if (currentClickMode == PanelMode.CONNECT) {
            currentClickPanelID = ClickEventProcessor.getInstance().lastClickPanelID;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drag) {
            if (currentClickMode == PanelMode.CONNECT) {
                DrawnClasses drawnClasses = DrawnClasses.getInstance();
                drawnClasses.changeClassPosition(currentClickPanelID, e.getX(), e.getY());
                String className = drawnClasses.getClassByID(currentClickPanelID).getTitle();
                GlobalStatus.getInstance().setDrawStatus("Moved class " + className);
                //panel.redraw();
                lastClickMode = PanelMode.NEW;
            }
        }
        drag = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drag = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
