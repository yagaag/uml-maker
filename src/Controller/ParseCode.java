package Controller;

import Model.DrawnClasses;
import Model.UserClass;

public class ParseCode {

    ParseAssociation headParser;

    public ParseCode() {
        setupParseChain();
    }

    private void setupParseChain() {
        headParser = new ParseAssociation();
        ParseAggregation secondParser = new ParseAggregation();
        ParseInheritance thirdParser = new ParseInheritance();
        ParseClassName tailParser = new ParseClassName();
        thirdParser.setNextParser(tailParser);
        secondParser.setNextParser(thirdParser);
        headParser.setNextParser(secondParser);
    }

    public String parse() {

        String s = "";
        int numClasses = DrawnClasses.getInstance().getLength();
        for (int i=0; i<numClasses; i++) {
            s += headParser.parse((DrawnClasses.getInstance().getClassByID(i)));
            s += "\n\n";
        }
        return s;
    }
}
