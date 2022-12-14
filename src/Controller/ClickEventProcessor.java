package Controller;

import Model.DrawnClasses;
import Model.UserClass;
import View.ViewConstants;

import javax.swing.*;

public class ClickEventProcessor {

    private static ClickEventProcessor instance;
    protected ClickEventProcessor() {}

    public static ClickEventProcessor getInstance() {
        if(instance == null) {
            instance = new ClickEventProcessor();
        }
        return instance;
    }
    int lastClickPanelID;
    String lastClassName;

    private boolean checkWithinBoundary(int x1, int y1, int x2, int y2) {
        if (x2 >= x1-(ViewConstants.userClassWidth/2) && x2 <= x1+(ViewConstants.userClassWidth/2)) {
            if (y2 >= y1-(ViewConstants.userClassHeight/2) && y2 <= y1+(ViewConstants.userClassHeight/2)) {
                return true;
            }
        }
        return false;
    }

    public PanelMode categoriseClickEvent(int x, int y) {
        DrawnClasses drawnClasses = DrawnClasses.getInstance();
        PanelMode mode = PanelMode.NEW;
        for(int i=0; i<drawnClasses.getLength(); i++) {
            UserClass userClass = drawnClasses.getClassByID(i);
            if (checkWithinBoundary(userClass.xCoord(), userClass.yCoord(), x, y)) {
                lastClickPanelID = i;
                mode = PanelMode.CONNECT;
                break;
            }
        }
        return mode;
    }

    public void newUserClass(int x, int y) {
        lastClassName = JOptionPane.showInputDialog("Enter the name of the class:");
        DrawnClasses drawnClasses = DrawnClasses.getInstance();
        if (lastClassName == null || lastClassName.length() == 0) {
            lastClassName = "Class" + (drawnClasses.getLength()+1);
        }
        drawnClasses.addUserClass(x, y, lastClassName);
    }

    public int getLastClickPanelID() {
        return lastClickPanelID;
    }

    public String getLastClassName() {
        return lastClassName;
    }
}
