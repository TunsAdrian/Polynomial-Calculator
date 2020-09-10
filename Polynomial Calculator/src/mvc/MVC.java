package mvc;

import controller.Controller;
import view.UserInterface;

import javax.swing.*;

public class MVC {

    public static void main(String[] args) {

        UserInterface view = new UserInterface();
        Controller con = new Controller(view);

        SwingUtilities.invokeLater(view);
    }
}
