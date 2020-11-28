package uet.oop.bomberman.sound;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Sound extends Application {

    private Media media;
    private MediaPlayer player;
    public Sound(String file) {
        String link = "res/sound/" + file;
        media = new Media(new File(link).toURI().toString());
        player = new MediaPlayer(media);
    }

    public void play() {
        player.play();
    }
    @Override
    public void stop() throws Exception {
        player.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        player.play();
    }
}