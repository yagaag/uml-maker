package Controller;

import Model.DrawnClasses;

public class ParseUML {

    ChainableParser headParser;

    public ParseUML() {
        setupParseChain();
    }

    private void setupParseChain() {
        headParser = new AssociationParser();
        AggregationParser secondParser = new AggregationParser();
        InheritanceParser thirdParser = new InheritanceParser();
        ClassNameParser tailParser = new ClassNameParser();
        thirdParser.setNextParser(tailParser);
        secondParser.setNextParser(thirdParser);
        headParser.setNextParser(secondParser);
    }

    public String parseUML() {

        String s = "";
        int numClasses = DrawnClasses.getInstance().getLength();
        for (int i=0; i<numClasses; i++) {
            s += headParser.parse((DrawnClasses.getInstance().getClassByID(i)));
            s += "\n\n";
        }
        return s;
    }
}
