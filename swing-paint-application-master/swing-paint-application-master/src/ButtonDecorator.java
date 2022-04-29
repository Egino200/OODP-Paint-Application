import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.*;

//swing classes
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class ButtonDecorator extends Decorator {
    boolean mouse_over;    //true when mose over button
    JComponent thisComp;

    public ButtonDecorator(JButton c) {
        super(c);
        mouse_over = false;
        thisComp = this;//save this component

        //catch mouse movements in inner class
        c.addMouseListener(new MouseAdapter() {
            //set flag when mouse over
            public void mouseEntered(MouseEvent e) {
                mouse_over = true;
                c.setSize(c.getWidth()+10,c.getHeight()+10 );
            }
            //clear flag when mouse not over
            public void mouseExited(MouseEvent e) {
                mouse_over = false;
                c.setSize(c.getWidth()-10,c.getHeight()-10 );
            }
        });
    }


}
