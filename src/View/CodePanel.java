package View;

import Controller.CodeProcessor;
import Model.ViewConstants;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * CodePanel displays generated code
 *
 * @author yagaa
 * @version 1.0.0
 */
public class CodePanel extends JPanel implements CodeViewPanel, Observer {

    JTextPane code;
    CodeProcessor codeProcessor;

    /**
     * Sets up panel position and components
     *
     * @param x The start position of the panel (x-axis)
     * @param y The start position of the panel (y-axis)
     * @param width The width of the panel
     * @param height The height of the panel
     */
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

    /**
     * Add message to panel of particular color
     *
     * @param msg The message to be added
     * @param c The color of the text
     */
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

    /**
     * Update panel in case of change in data model
     *
     * @param o The Observable that has the change
     * @param arg Arguments passed by the Observable
     */
    @Override
    public void update(Observable o, Object arg) {
        code.setText("");
        codeProcessor.parseUML(this);
    }
}
