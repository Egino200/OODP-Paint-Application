package AbstractFactory;

public class MacWidgetFactory extends AbstractFactory{
    public Window createWindow(String title){
        MacWindow window = new MacWindow(title);
        return window;
    }
}