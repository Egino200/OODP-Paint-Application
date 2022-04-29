package AbstractFactory;

public class UnixWindowWidgetFactory extends AbstractFactory {



    public Window createWindow(String title) {
        UnixWindow window = new UnixWindow(title);
        return window;
    }
}