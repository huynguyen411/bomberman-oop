package uet.oop.bomberman.sound;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Sound {

    private Media media;
    private MediaPlayer player;
    public static boolean isSound = false;
    public Sound(String file) {
        String link = "res/sound/" + file;
        media = new Media(new File(link).toURI().toString());
        player = new MediaPlayer(media);
    }

    public void play() {
        if (isSound)
            player.play();
    }

    public void stop() {
        if (isSound)
            player.stop();
    }

    public void start(Stage stage) {
        if (isSound)
            player.play();
    }

    public void setSound(boolean sound) {
        isSound = sound;
    }

    private boolean getSound() {
        return isSound;
    }
}