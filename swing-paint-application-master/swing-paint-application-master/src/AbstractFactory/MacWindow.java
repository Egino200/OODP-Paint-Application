package AbstractFactory;

import javax.swing.*;

public class MacWindow extends Window{
    MacWindow(String text){this.osName = text;}



    public JLabel getLabel(){

        return new JLabel(osName + "Mac OS");
    }
}