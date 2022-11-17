package View;

import Controller.ParseCode;
import Model.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CodePanel extends JPanel implements Observer {

    JTextArea code;
    ParseCode parseCode;

    public CodePanel(int x, int y, int width, int height) {
        parseCode = new ParseCode();
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
        code.setText(parseCode.parse());
    }
}
