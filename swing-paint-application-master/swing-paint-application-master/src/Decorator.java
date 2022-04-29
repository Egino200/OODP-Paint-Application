import javax.swing.*;

public abstract class Decorator extends JComponent {
    public Decorator(JComponent c){
        add(c);
    }
}