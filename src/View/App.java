package View;

import Model.ConnectionType;
import Model.DrawnClasses;
import Model.GlobalStatus;
import Model.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenuItem save = new JMenuItem("Save");
    JMenuItem load = new JMenuItem("Load");
    JMenuItem clear = new JMenuItem("Clear");
    JMenuItem association = new JMenuItem(ConnectionType.ASSOCIATION.name);
    JMenuItem inheritance = new JMenuItem(ConnectionType.INHERITANCE.name);
    JMenuItem aggregation = new JMenuItem(ConnectionType.AGGREGATION.name);
    CodePanel codePanel;
    DesignPanel designPanel;
    StatusBar statusBar;

    public App() {

        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu connectionType = new JMenu("Connection");
        save.addActionListener(this);
        load.addActionListener(this);
        clear.addActionListener(this);
        association.addActionListener(this);
        inheritance.addActionListener(this);
        aggregation.addActionListener(this);
        file.add(save);
        file.add(load);
        file.add(clear);
        connectionType.add(association);
        connectionType.add(inheritance);
        connectionType.add(aggregation);
        menuBar.add(file);
        menuBar.add(help);
        menuBar.add(connectionType);
        menuBar.setBounds(-2,-2,ViewConstants.panelWidth+4,35);
        menuBar.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        menuBar.setBackground(Color.white);
        codePanel = new CodePanel(10, 45, 300, 690);
        codePanel.setBounds(10,45,300,690);
        codePanel.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        codePanel.setBackground(Color.white);
        designPanel = new DesignPanel(320, 45, 670, 690);
        statusBar = new StatusBar(-2, 745, ViewConstants.panelWidth+4, 37);
        GlobalStatus.getInstance().addObserver(statusBar);
        DrawnClasses.getInstance().addObserver(codePanel);

        this.setPreferredSize(new Dimension(ViewConstants.panelWidth,ViewConstants.panelHeight));
        this.setLayout(null);
        this.add(menuBar);
        this.add(codePanel);
        this.add(designPanel);
        this.add(statusBar);
        this.add(new Panel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == association) {
            GlobalStatus.getInstance().setConnectionType(ConnectionType.ASSOCIATION);
        }
        else if(e.getSource() == inheritance) {
            GlobalStatus.getInstance().setConnectionType(ConnectionType.INHERITANCE);
        }
        else if(e.getSource() == aggregation) {
            GlobalStatus.getInstance().setConnectionType(ConnectionType.AGGREGATION);
        }
        else if(e.getSource() == clear) {
            designPanel.clearAll();
            DrawnClasses.getInstance().reset();
        }
    }
}
