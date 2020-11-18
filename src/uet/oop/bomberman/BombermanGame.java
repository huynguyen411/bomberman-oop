package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import uet.oop.bomberman.ViewManager.Controller;

public class BombermanGame extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Controller controller = new Controller();
    }
    public static void main(String[] args) {
        //Application.launch(args);
        Rectangle a= new Rectangle(1, 1, 1, 1);
        Rectangle b= new Rectangle(1, 1, 0.98, 0.98);
        System.out.println(a.intersects(b.getLayoutBounds()));
    }

}
