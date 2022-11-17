package View;

import Model.DrawnClasses;
import Model.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CodePanel extends JPanel implements Observer {

    JLabel code;

    public CodePanel(int x, int y, int width, int height) {
        this.setBounds(x,y,width,height);
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        this.setBackground(Color.white);
        code = new JLabel(DrawnClasses.getInstance().getText());
        code.setBounds(10,5,width-20,30);
        this.setLayout(null);
        this.add(code);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("waa");
        code.setText(DrawnClasses.getInstance().getText());
    }
}
