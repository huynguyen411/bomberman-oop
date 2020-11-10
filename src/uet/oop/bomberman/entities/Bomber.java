package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.ViewManager.Controller;

public class Bomber extends Entity {
    public static double STEP = 0.01;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    public Bomber(double x, double y, Image img) {
        super(x, y, img);
    }

    private void createListener() {
        Controller.scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = true;
            } else if (keyEvent.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = true;
            } else if (keyEvent.getCode() == KeyCode.UP){
                isUpKeyPressed = true;
            } else if (keyEvent.getCode() == KeyCode.DOWN){
                isDownKeyPressed = true;
            }
        });

        Controller.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT){
                    isLeftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.UP){
                    isUpKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.DOWN){
                    isDownKeyPressed = false;
                }
            }
        });
    }
    public void moveRight() {
        super.x += STEP*5;
        super.x = (double)Math.round(x * 100) / 100;
        for (int i = 0; i < Controller.xBrick.length; i++) {
            if (this.getX() + 0.6 ==  Controller.xBrick[i] ) {
                return;
            }
        }
    }
    public void moveLeft() {
        super.x -= STEP*5;
        super.x = (double)Math.round(x * 100) / 100;
    }
    public void moveDown() {
        super.y += STEP*5;
        super.y = (double)Math.round(y * 100) / 100;
    }
    public void moveUp() {
        super.y -= STEP*5;
        super.y = (double)Math.round(y * 100) / 100;
    }

    private void move() {
        createListener();
        if (isRightKeyPressed){
            this.moveRight();
        } else if (isLeftKeyPressed) {
            this.moveLeft();
        } else if (isDownKeyPressed) {
            this.moveDown();
        } else if (isUpKeyPressed) {
            this.moveUp();
        }
    }

    public double getX() {
        return super.x;
    }
    public double getY() {
        return super.y;
    }

    @Override
    public void update() {
        move();
    }

}
