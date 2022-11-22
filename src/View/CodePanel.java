package View;

import Controller.CodeProcessor;
import Model.ViewConstants;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CodePanel extends JPanel implements CodeViewPanel, Observer {

    JTextPane code;
    CodeProcessor codeProcessor;

    public CodePanel(int x, int y, int width, int height) {
        codeProcessor = new CodeProcessor();
        this.setBounds(x,y,width,height);
        this.setBorder(BorderFactory.createLineBorder(ViewConstants.accentColor, 2));
        this.setBackground(Color.white);
        code = new JTextPane();
        code.setEditable(false);
        code.setBounds(10,5,width-20,height-20);
        this.setLayout(null);
        this.add(code);
        this.setVisible(true);
    }

    public void appendToPanel(String msg, Color c) {

        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet attribSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
        Document doc = code.getDocument();
        try {
            doc.insertString(doc.getLength(), msg, attribSet);
        } catch (BadLocationException e) {
            code.setCharacterAttributes(attribSet, false);
            code.setText(code.getText() + msg);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        code.setText("");
        codeProcessor.parseUML(this);
    }
}
