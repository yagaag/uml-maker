package Controller;

import Model.Connection;
import Model.ConnectionType;
import Model.UserClass;
import Model.ViewConstants;
import View.CodeViewPanel;

import java.awt.*;
import java.util.ArrayList;

public class CompositionParser extends ChainableParser {

    @Override
    public void parse(UserClass userClass, CodeViewPanel panel) {

        ArrayList<Connection> connections = userClass.getConnections();
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).getType() == ConnectionType.COMPOSITION) {
                panel.appendToPanel("  " + connections.get(i).getToClass().getTitle() + "\n", ViewConstants.baseSyntaxColor);
            }
        }
        super.parse(userClass, panel);
    }
}
