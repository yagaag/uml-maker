package View;

import Model.DrawnClasses;
import Model.UserClass;

import java.awt.*;

public class CodeProcessor {

    ChainableParser headParser;

    public CodeProcessor() {
        setupParseChain();
    }

    private void setupParseChain() {
        headParser = new InheritanceParser();
        ChainableParser secondParser = new CompositionParser();
        headParser.setNextParser(secondParser);
        Parser tailParser = new AssociationParser();
        secondParser.setNextParser(tailParser);
    }

    public void parseUML(CodeViewPanel panel) {
        for (UserClass userClass: DrawnClasses.getInstance().getClasses()) {
            headParser.parse(userClass, panel);
            panel.appendToPanel("\n\n", Color.black);
        }
    }
}
