package Controller;

import Model.DrawnClasses;
import Model.FileManager;
import Model.GlobalStatus;

import javax.swing.*;
import java.io.File;

public class SaveEventProcessor {

    public void save() {
        String filename = JOptionPane.showInputDialog("Enter a filename:");
        if (filename == null) {
            return;
        }
        if (filename.length() == 0) {
            GlobalStatus.getInstance().setDrawStatus("Filename cannot be empty");
            return;
        }
        filename = filename + ".txt";
        FileManager.save(filename);
    }

    public void load() {
        String filename = JOptionPane.showInputDialog("Enter the filename to load:");
        if (filename == null) {
            return;
        }
        if (filename.length() == 0) {
            GlobalStatus.getInstance().setDrawStatus("Filename cannot be empty");
            return;
        }
        filename = filename + ".txt";
        if (!FileManager.validateFileExist(filename)) {
            GlobalStatus.getInstance().setDrawStatus("File not found!");
            return;
        }
        FileManager.load(filename);
        GlobalStatus.getInstance().setDrawStatus("Loaded " + filename);
    }
}
