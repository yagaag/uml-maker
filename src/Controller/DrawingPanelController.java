package Controller;

import Model.DrawnClasses;
import Model.GlobalStatus;
import Model.PanelMode;
import View.DrawingPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawingPanelController implements MouseListener {

    DrawingPanel panel;
    ClickEventProcessor processor = new ClickEventProcessor();
    int lastClickPanelID;
    PanelMode mode = PanelMode.NEW;

    public DrawingPanelController(DrawingPanel p) {
        panel = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PanelMode clickMode = processor.categoriseClickEvent(e.getX(), e.getY());
        if (clickMode == PanelMode.CONNECT) {
            int connectPanelID = processor.getLastClickPanelID();
            String className = DrawnClasses.getInstance().getClassByID(connectPanelID).getTitle();
            GlobalStatus.getInstance().setDrawStatus("Selected " + className);
            if (mode == PanelMode.CONNECT) {
                panel.drawConnection(lastClickPanelID, connectPanelID);
                mode = PanelMode.NEW;
            }
            else {
                mode = PanelMode.CONNECT;
                lastClickPanelID = processor.getLastClickPanelID();
            }
        }
        else {
            if (processor.newUserClass(e.getX(),e.getY())) {
                panel.drawRectangle(e.getX(),e.getY(), processor.getLastClassName());
            }
            mode = PanelMode.NEW;
            GlobalStatus.getInstance().setDrawStatus("No class selected");
        }
    }

    public void setPanelMode(PanelMode m) {
        mode = m;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
