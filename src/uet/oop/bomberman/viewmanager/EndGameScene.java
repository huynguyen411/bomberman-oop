package uet.oop.bomberman.viewmanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Buttons.GameButton;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;

public class EndGameScene {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private AnchorPane menuPane;
    private Stage stage;
    private Scene scene;
    private Sound sound;
    private static final int NEW_BUTTONS_START_X = 100;
    public static final int NEW_BUTTONS_START_Y = 150;

    ArrayList<GameButton> menuButtons;

    public EndGameScene(Stage menutage, String imageUrl) throws Exception {
        menutage.hide();
        menuButtons = new ArrayList<>();
        menuPane = new AnchorPane();
        scene = new Scene(menuPane, WIDTH, HEIGHT);
        stage = new Stage();
        stage.setScene(scene);
        createButtons();
        createBackground(imageUrl);
        stage.show();
    };


    private void addMenuButtons(GameButton button){
        button.setLayoutX(NEW_BUTTONS_START_X);
        button.setLayoutY(NEW_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        menuPane.getChildren().add(button);
    }

    private void createButtons(){
        createExitButton();
    }

    private void createStartButton(){
        GameButton button = new GameButton("PLAY");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    sound.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sound = new Sound("level_start.wav");
                try {
                    sound.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Controller controller = new Controller(stage);
            }
        });
    }

    private void createScoresButton(){
        GameButton button = new GameButton("SCORES");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            }
        });
    }

    private void createMusicButton(){
        GameButton button = new GameButton("MUSIC");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    private void createExitButton(){
        GameButton button = new GameButton("EXIT");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
    }

    private void createBackground(String imgUrl){
        Image image = new Image(imgUrl, 1280, 720, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        menuPane.setBackground(new Background(backgroundImage));
    }

}
