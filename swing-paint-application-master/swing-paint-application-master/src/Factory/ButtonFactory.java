package Factory;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    public ColorButton getButton(String buttonType, Dimension size, Color color){
        if(buttonType == "ColorButton"){
            ColorButton colorButton = new ColorButton(color,size);
            colorButton.build();
            return colorButton;
        }

        return null;
    }
}
