package hp11c;

/**
 * Created by Refi on 07.03.2017.
 */
public class Hp11C {

    private Hp11cEngine engine;
    private Hp11cGui gui;

    public Hp11C() {
        engine = new Hp11cEngine("RPN", "martin", "0.1", 0);
        gui = new Hp11cGui(engine);
        gui.setVisible(true);
    }

}