package AbstractFactory;


public class MSWindowWidgetFactory extends AbstractFactory{
    public Window createWindow(String title){
        MSWindow window = new MSWindow(title);
        return window;
    }
}
