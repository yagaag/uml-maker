package View;

import Controller.ClickEventProcessor;
import Model.ConnectionType;
import Model.PanelMode;
import Model.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;

public class DesignPanel extends JPanel implements MouseListener {

    PanelMode mode = PanelMode.NEW;
    ConnectionType connectionType = ConnectionType.ASSOCIATION;
    ClickEventProcessor processor = new ClickEventProcessor();
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

    public void setConnectionType(ConnectionType type) {
        connectionType = type;
    }

    public void clearAll() {
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        mode = PanelMode.NEW;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PanelMode clickMode = processor.categoriseClickEvent(e.getX(), e.getY());
        if (clickMode == PanelMode.CONNECT) {
            System.out.println("Connect mode");
            if (mode == PanelMode.CONNECT) {
                System.out.println("Already in connect mode");
                int connectPanelID = processor.getLastClickPanelID();
//                makeConnection(lastClickPanelID, connectPanelID);
            }
            else {
                System.out.println("Entering connect mode");
                mode = PanelMode.CONNECT;
                lastClickPanelID = processor.getLastClickPanelID();
            }
        }
        else {
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
