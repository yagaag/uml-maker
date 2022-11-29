package Controller;

import Model.DrawnClasses;
import Model.UserClass;
import View.CodeViewPanel;

import java.awt.*;

public class CodeProcessor {

    ChainableParser headParser;

    public CodeProcessor() {
        setupParseChain();
    }

    private void setupParseChain() {
        headParser = new InheritanceParser();
        CompositionParser secondParser = new CompositionParser();
        AssociationParser tailParser = new AssociationParser();
        secondParser.setNextParser(tailParser);
        headParser.setNextParser(secondParser);
    }

    public void parseUML(CodeViewPanel panel) {
        for (UserClass userClass: DrawnClasses.getInstance().getClasses()) {
            headParser.parse(userClass, panel);
            panel.appendToPanel("\n\n", Color.black);
        }
    }
}
