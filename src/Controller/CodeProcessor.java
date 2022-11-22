package Controller;

import Model.DrawnClasses;
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

        int numClasses = DrawnClasses.getInstance().getLength();
        for (int i=0; i<numClasses; i++) {
            headParser.parse((DrawnClasses.getInstance().getClassByID(i)), panel);
            panel.appendToPanel("\n\n", Color.black);
        }
    }
}
