package Controller;

import Model.Connection;
import Model.ConnectionType;
import Model.UserClass;
import Model.ViewConstants;
import View.CodeViewPanel;

import java.awt.*;
import java.util.ArrayList;

public class InheritanceParser extends ChainableParser {

    @Override
    public void parse(UserClass userClass, CodeViewPanel panel) {
        boolean extended = false;
        panel.appendToPanel("class "+ userClass.getTitle(), ViewConstants.baseSyntaxColor);
        ArrayList<Connection> connections = userClass.getConnections();
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).getType() == ConnectionType.INHERITANCE) {
                if (extended) {
                    panel.appendToPanel(", " + connections.get(i).getToClass().getTitle(), ViewConstants.baseSyntaxColor);
                } else {
                    extended = true;
                    panel.appendToPanel(" extends " + connections.get(i).getToClass().getTitle(), ViewConstants.baseSyntaxColor);
                }
            }
        }
        panel.appendToPanel(" {\n", ViewConstants.baseSyntaxColor);
        super.parse(userClass, panel);
    }
}
