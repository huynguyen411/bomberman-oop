package uet.oop.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.viewmanager.StartScene;

public class BombermanGame extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        StartScene startScene = new StartScene();
    }
    public static void main(String[] args) {
        Application.launch(args);

    }

}
