package AbstractFactory;

import javax.swing.*;

// abstract product
public abstract class Window{
    protected String osName;
    public abstract JLabel getLabel();
}

