package Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;

public class ColorButton extends JButton implements Button {
    private Color color;
    private Dimension size;

    public ColorButton(Color color, Dimension size) {
        this.color = color;
        this.size = size;
    }

    public void build() {
        setBackground(color);
        setPreferredSize(size);
    }
}
