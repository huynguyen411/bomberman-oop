package uet.oop.bomberman.sound;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Sound extends Application {
    public Sound(String file) {
        String link = "res/sound/" + file;
        Media media = new Media(new File(link).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }

    @Override
    public void start(Stage stage) throws Exception {
        new Sound("background_music.wav");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
