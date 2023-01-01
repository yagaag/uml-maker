package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddFunctionPanel extends JPanel implements ActionListener {

    JTextField nameField = new JTextField();
    JTextField returnField = new JTextField();
    JButton addParam = new JButton("Add Parameter");
    ArrayList<String> paramNames = new ArrayList<>();
    ArrayList<String> paramTypes = new ArrayList<>();
    public AddFunctionPanel() {
        JLabel nameLabel = new JLabel("Name: ");
        JLabel returnLabel = new JLabel("Return type: ");
        nameLabel.setBounds(10,10,100,40);
        nameField.setBounds(120,10,100,40);
        returnLabel.setBounds(10,60,100,40);
        nameLabel.setBounds(110,60,100,40);
        addParam.setBounds(85, 110, 60, 40);
        addParam.addActionListener(this);
        this.add(nameLabel);
        this.add(nameField);
        this.add(returnLabel);
        this.add(returnField);
        this.add(addParam);
    }

    public String getFunctionName() {
        return nameField.getText();
    }

    public String getFunctionReturn() {
        return returnField.getText();
    }

    public String getFunctionParams() {
        return nameField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
