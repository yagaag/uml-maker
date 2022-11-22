package View;

import javax.swing.*;
import java.awt.*;

public interface CodeViewPanel {
    JTextPane code = null;
    public void appendToPanel(String msg, Color c);
}
