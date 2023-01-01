package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopupMenu extends JPopupMenu implements ActionListener {
    JMenuItem addFunction;
    public PopupMenu() {
        addFunction = new JMenuItem("Add function");
        addFunction.addActionListener(this);
        this.add(addFunction);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddFunctionPanel addFunctionPanel = new AddFunctionPanel();
        int result = JOptionPane.showConfirmDialog(null, addFunctionPanel,
                "Please enter function details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println(addFunctionPanel.getFunctionName());
            System.out.println(addFunctionPanel.getFunctionReturn());
        }
    }
}
