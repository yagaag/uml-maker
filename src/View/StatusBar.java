package View;

import Model.GlobalStatus;
import Model.ViewConstants;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class StatusBar extends JPanel implements Observer {

    JLabel status;

    public StatusBar(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        this.setBackground(Color.white);
        status = new JLabel(GlobalStatus.getInstance().getDrawStatus());
        status.setBounds(10, 5, 400, 25);
        this.setLayout(null);
        this.add(status);
    }

    @Override
    public void update(Observable o, Object arg) {
        status.setText(GlobalStatus.getInstance().getDrawStatus());
    }
}
