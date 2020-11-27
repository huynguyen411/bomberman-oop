package uet.oop.bomberman.graphics.Buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameButton extends Button {
    private final String FONT_PATH = "/textures/kenvector_future.ttf";
    public GameButton(String text) {
        setText(text);
//        setBackground();
//        setPrefHeight(40);
//        setPrefWidth(70);
        setButtonFont();
    }

    private void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void initializeButtonListeners(){
//        setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (event.getButton().equals(MouseButton.PRIMARY)){
//                    setButtonPressedStyle();
//                }
//            }
//        });
//
//        setOnMouseReleased(event -> {
//            if(event.getButton().equals(MouseButton.PRIMARY)){
//                setButtonFreeStyle();
//            }
//        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));
    }
}
