package Controller;

import Model.ConnectionType;
import Model.DrawnClasses;
import Model.FileManager;
import Model.GlobalStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String itemSelected = e.getActionCommand();
        if (itemSelected.equals("Clear")) {
            DrawnClasses.getInstance().reset();
        } else if (itemSelected.equals("Save")) {
            save();
        } else if (itemSelected.equals("Load")) {
            load();
        } else {
            GlobalStatus.getInstance().setConnectionType(ConnectionType.valueOf(itemSelected));
        }
    }
    private void save() {
        String filename = JOptionPane.showInputDialog("Enter a filename:");
        if (filename == null || filename.length() == 0) {
            GlobalStatus.getInstance().setDrawStatus("Filename cannot be empty");
            return;
        }
        filename = filename + ".txt";
        FileManager.save(filename);
        GlobalStatus.getInstance().setDrawStatus("File saved successfully");
    }

    private void load() {
        String filename = JOptionPane.showInputDialog("Enter the filename to load:");
        if (filename == null || filename.length() == 0) {
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
