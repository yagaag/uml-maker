package Controller;

import Model.DrawnClasses;
import Model.PanelMode;
import Model.UserClass;
import Model.ViewConstants;

import javax.swing.*;

public class ClickEventProcessor {

    int lastClickPanelID = 0;

    public PanelMode categoriseClickEvent(int x, int y) {
        DrawnClasses drawnClasses = DrawnClasses.getInstance();
        PanelMode mode = PanelMode.NEW;
        for(int i=0; i<drawnClasses.getLength(); i++) {
            UserClass userClass = drawnClasses.getClassByID(i);
            if (checkWithinBoundary(userClass.xCoord(), userClass.yCoord(), x, y)) {
                lastClickPanelID = i;
                System.out.println("Claiming intersection");
                System.out.println(i);
                mode = PanelMode.CONNECT;
                break;
            }
        }
        return mode;
    }

    private boolean checkWithinBoundary(int x1, int y1, int x2, int y2) {
        if (x2 >= x1-(ViewConstants.userClassWidth/2) && x2 <= x1+(ViewConstants.userClassWidth/2)) {
            if (y2 >= y1-(ViewConstants.userClassHeight/2) && y2 <= y1+(ViewConstants.userClassHeight/2)) {
                return true;
            }
        }
        return false;
    }

    public int getLastClickPanelID() {
        return lastClickPanelID;
    }

    public String newUserClass(int x, int y) {
        String name = JOptionPane.showInputDialog("Enter the name of the class:");
        System.out.println(name);
        DrawnClasses drawnClasses = DrawnClasses.getInstance();
        if (name == null || name.length() == 0) {
            name = "Class" + (drawnClasses.getLength()+1);
        }

        drawnClasses.addUserClass(x, y, name);
        return name;
    }
}
