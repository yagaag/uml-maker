package Controller;

import Model.UserClass;
import View.CodeViewPanel;

public interface Parser {
    void parse(UserClass userClass, CodeViewPanel panel);
}
