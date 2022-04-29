package AbstractFactory;

import javax.swing.*;

public class UnixWindow extends Window {
    UnixWindow(String text){this.osName = text;}

    public JLabel getLabel(){

        return new JLabel(osName + "Unix");
    }


}
