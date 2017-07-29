import touhou.GameWindow;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        System.out.print("Hello world");
        GameWindow gameWindow = new GameWindow();
        //System.out.println("Starting loop");
        gameWindow.loop();
    }
}
