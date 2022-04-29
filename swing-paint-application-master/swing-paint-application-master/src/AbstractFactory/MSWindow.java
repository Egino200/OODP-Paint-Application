package AbstractFactory;

import javax.swing.*;

//concrete product 1
public class MSWindow extends Window{
    MSWindow(String text){this.osName = text;}



    public JLabel getLabel(){

        return new JLabel(osName + "MS Windows");
    }


}
