package uet.oop.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.ViewManager.Controller;

public class BombermanGame extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Controller controller = new Controller();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

}
