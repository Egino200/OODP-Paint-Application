package AbstractFactory;

import javax.swing.*;

public class GUIBuilder{
    public JLabel buildWindow(AbstractFactory widgetFactory, String title){
        Window window = widgetFactory.createWindow(title);

        return window.getLabel();
    }
}
