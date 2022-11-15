package View;

import Model.ConnectionType;
import Model.DrawnClasses;
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
    JMenuItem association = new JMenuItem("Association");
    JMenuItem inheritance = new JMenuItem("Inheritance");
    JMenuItem dependency = new JMenuItem("Dependency");
    JPanel codePanel;
    DesignPanel designPanel;
    JPanel statusBar;

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
        dependency.addActionListener(this);
        file.add(save);
        file.add(load);
        file.add(clear);
        connectionType.add(association);
        connectionType.add(inheritance);
        connectionType.add(dependency);
        menuBar.add(file);
        menuBar.add(help);
        menuBar.add(connectionType);
        menuBar.setBounds(0,0,1000,35);
        menuBar.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        menuBar.setBackground(Color.white);
        codePanel = new JPanel();
        codePanel.setBounds(10,45,300,690);
        codePanel.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        codePanel.setBackground(Color.white);
        designPanel = new DesignPanel(320, 45, 670, 690);
        statusBar = new JPanel();
        statusBar.setBounds(0,745,1000,35);
        statusBar.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        statusBar.setBackground(Color.white);

        this.setPreferredSize(new Dimension(1000,808));
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
            designPanel.setConnectionType(ConnectionType.ASSOCIATION);
        }
        else if(e.getSource() == inheritance) {
            designPanel.setConnectionType(ConnectionType.INHERITANCE);
        }
        else if(e.getSource() == dependency) {
            designPanel.setConnectionType(ConnectionType.DEPENDENCY);
        }
        else if(e.getSource() == clear) {
            designPanel.clearAll();
            DrawnClasses.getInstance().reset();
        }
    }
}
