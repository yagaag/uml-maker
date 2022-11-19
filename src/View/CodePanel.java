package View;

import Controller.ParseUML;
import Model.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CodePanel extends JPanel implements Observer {

    JTextArea code;
    ParseUML parseUML;

    public CodePanel(int x, int y, int width, int height) {
        parseUML = new ParseUML();
        this.setBounds(x,y,width,height);
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        this.setBackground(Color.white);
        code = new JTextArea("");
        code.setEditable(false);
        code.setBounds(10,5,width-20,height-20);
        this.setLayout(null);
        this.add(code);
    }

    @Override
    public void update(Observable o, Object arg) {
        code.setText(parseUML.parseUML());
    }
}
