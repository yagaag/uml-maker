package View;

import Controller.AppController;
import Model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Primary JFrame with all panels
 *
 * @author yagaa
 * @version 1.0.0
 */
public class App extends JFrame {

    /**
     * Sets up UI components and displays the JFrame
     */
    public App() {
        JMenuBar menuBar;
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem association = new JMenuItem(ConnectionType.Association.name);
        JMenuItem inheritance = new JMenuItem(ConnectionType.Inheritance.name);
        JMenuItem aggregation = new JMenuItem(ConnectionType.Composition.name);
        JMenuItem signin = new JMenuItem("Sign in");
        CodePanel codePanel;
        DesignPanel designPanel;
        StatusBar statusBar = new StatusBar(-2, 745, ViewConstants.panelWidth+4, 37);
        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu connectionType = new JMenu("Connection");
        AppController appController = new AppController();
        save.addActionListener(appController);
        load.addActionListener(appController);
        clear.addActionListener(appController);
        association.addActionListener(appController);
        inheritance.addActionListener(appController);
        aggregation.addActionListener(appController);
        file.add(save);
        file.add(load);
        file.add(clear);
        help.add(signin);
        connectionType.add(association);
        connectionType.add(inheritance);
        connectionType.add(aggregation);
        menuBar.add(file);
        menuBar.add(connectionType);
        menuBar.add(help);
        menuBar.setBounds(-2,-2,ViewConstants.panelWidth+4,35);
        menuBar.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        menuBar.setBackground(Color.white);
        codePanel = new CodePanel(10, 45, 300, 690);
        codePanel.setBounds(10,45,300,690);
        codePanel.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        codePanel.setBackground(Color.white);
        designPanel = new DesignPanel(320, 45, 670, 690);
        GlobalStatus.getInstance().addObserver(statusBar);
        DrawnClasses.getInstance().addObserver(codePanel);
        DrawnClasses.getInstance().addObserver(designPanel);

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

    public static void main(String[] args) {
        new App();
    }

}
