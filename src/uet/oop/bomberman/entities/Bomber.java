package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.ViewManager.Controller;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    private final Image[] animationPlayerUp = {Sprite.player_up.getFxImage(), Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage()};
    private final Image[] animationPlayerDown = {Sprite.player_down.getFxImage(), Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage()};
    private final Image[] animationPlayerLeft = {Sprite.player_left.getFxImage(), Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage()};
    private final Image[] animationPlayerRight = {Sprite.player_right.getFxImage(), Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage()};

    private int indexUp = 0;
    private int indexDown = 0;
    private int indexLeft = 0;
    private int indexRight = 0;
    public Bomber(double x, double y, Image img) {
        super(x, y, img);
    }

    public Bomber(double x, double y, Image image, Sprite sprite) {
        super(x, y, image);
//        this.sprite = sprite;
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

        Controller.scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = false;
            } else if (keyEvent.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = false;
            }
            if (keyEvent.getCode() == KeyCode.UP){
                isUpKeyPressed = false;
            } else if (keyEvent.getCode() == KeyCode.DOWN){
                isDownKeyPressed = false;
            }
        });
    }
    public void moveRight() {
        if (indexRight == 12)
            indexRight = 0;
        this.x += STEP * 5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.x -= STEP * 5;
            } else {
                this.rec.setX(this.x);
            }
        }
        this.x = (double) Math.round(x * 100) / 100;
        this.img = animationPlayerRight[indexRight / 4];
        indexRight++;
    }

    public void moveLeft() {
        if (indexLeft == 12)
            indexLeft = 0;
        this.x -= STEP*5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.x += STEP * 5;
            } else {
                this.rec.setX(this.x);
            }
        }
        this.x = (double)Math.round(x * 100) / 100;
        this.img = animationPlayerLeft[indexLeft / 4];
        indexLeft++;
    }
    public void moveDown() {
        if (indexDown == 12)
            indexDown = 0;
        this.y += STEP*5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.y -= STEP * 5;
            } else {
                this.rec.setY(this.y);
            }
        }
        this.y = (double)Math.round(y * 100) / 100;
        this.img = animationPlayerDown[indexDown / 4];
        indexDown++;
    }
    public void moveUp() {
        if (indexUp == 12)
            indexUp = 0;
        this.y -= STEP*5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.y += STEP * 5;
            } else {
                this.rec.setY(this.y);
            }
        }
        this.y = (double)Math.round(y * 100) / 100;
        this.img = animationPlayerUp[indexUp / 4];
        indexUp++;
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

    private boolean checkIfStuck(Entity entity) {
        return this.rec.collision(entity.rec);
//        return false;
    }

    @Override
    public void update() {
        move();

    }

}
