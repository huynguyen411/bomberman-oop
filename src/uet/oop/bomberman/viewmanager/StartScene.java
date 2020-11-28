package uet.oop.bomberman.viewmanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uet.oop.bomberman.graphics.Buttons.GameButton;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;

public class StartScene {
    public static final int WIDTH = 920;
    public static final int HEIGHT = 688;
    private AnchorPane menuPane;
    private Stage stage;
    private Scene scene;
    private Sound sound;
    private static final int NEW_BUTTONS_START_X = 100;
    public static final int NEW_BUTTONS_START_Y = 150;

    ArrayList<GameButton> menuButtons;

    public StartScene() throws Exception {
        menuButtons = new ArrayList<>();
        menuPane = new AnchorPane();
        scene = new Scene(menuPane, WIDTH, HEIGHT);
        stage = new Stage();
        stage.setScene(scene);
        sound = new Sound("background_music.wav");
        sound.start(stage);
        createButtons();
        createBackground();

        stage.show();

    };


    private void addMenuButtons(GameButton button){
        button.setLayoutX(NEW_BUTTONS_START_X);
        button.setLayoutY(NEW_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        menuPane.getChildren().add(button);
    }

    private void createButtons(){
        createStartButton();
        createScoresButton();
        createMusicButton();
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

    private void createBackground(){
        Image image = new Image("/textures/bomberman_background.png", 920, 688, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        menuPane.setBackground(new Background(backgroundImage));
    }

//    private void createChooserSubScene() {
//        shipChooserSubScene = new SpaceShooterSubScene();
//        mainPane.getChildren().add(shipChooserSubScene);
//
//        InforLabel chooseShipLabel = new InforLabel("CHOOSE YOUR SHIP");
//        chooseShipLabel.setLayoutX(110);
//        chooseShipLabel.setLayoutY(15);
//
//        shipChooserSubScene.getPane().getChildren().add(chooseShipLabel);
//        shipChooserSubScene.getPane().getChildren().add(createShipToChoose());
//        shipChooserSubScene.getPane().getChildren().add(createButtonToStart());
//    }

//    private HBox createShipToChoose(){
//        HBox box = new HBox();
//        box.setSpacing(20);
//        shipList = new ArrayList<>();
//        for (SHIP ship : SHIP.values()){
//            ShipPicker shipToPick = new ShipPicker(ship);
//            box.getChildren().add(shipToPick);
//            shipList.add(shipToPick);
//
//            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    for(ShipPicker ship : shipList){
//                        ship.setIsCircleChosen(false);
//                    }
//                    shipToPick.setIsCircleChosen(true);
//                    chosenShip = shipToPick.getShip();
//                }
//            });
//        }
//        box.setLayoutX(300 - (118 * 2));
//        box.setLayoutY(100);
//        return box;
//    }

//    private SpaceShooterButton createButtonToStart(){
//        SpaceShooterButton startButton = new SpaceShooterButton("START");
//        startButton.setLayoutX(350);
//        startButton.setLayoutY(300);
//
//        startButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                if (chosenShip != null){
//                    GameViewManager gameViewManager = new GameViewManager();
//                    gameViewManager.createBoardGame(mainStage, chosenShip);
//                }
//            }
//        });
//
//        return startButton;
//    }

}
