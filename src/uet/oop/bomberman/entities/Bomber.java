package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.ViewManager.Controller;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public static double STEP = 0.01;
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
        if (indexRight == 12)
            indexRight = 0;

        super.x += STEP*5;
        super.x = (double)Math.round(x * 100) / 100;

        super.img = animationPlayerRight[indexRight / 4];
        indexRight++;
    }
    public void moveLeft() {
        if (indexLeft == 12)
            indexLeft = 0;
        super.x -= STEP*5;
        super.rec.setX(x*Sprite.SCALED_SIZE);
        super.x = (double)Math.round(x * 100) / 100;
        super.img = animationPlayerLeft[indexLeft / 4];
        indexLeft++;
    }
    public void moveDown() {
        if (indexDown == 12)
            indexDown = 0;
        super.y += STEP*5;
        super.y = (double)Math.round(y * 100) / 100;
        super.img = animationPlayerDown[indexDown / 4];
        indexDown++;
    }
    public void moveUp() {
        if (indexUp == 12)
            indexUp = 0;
        super.y -= STEP*5;
        super.y = (double)Math.round(y * 100) / 100;
        super.img = animationPlayerUp[indexUp / 4];
        indexUp++;
    }

    private void move() {
        createListener();
        for (Entity entity : Controller.stillObjects){
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                System.out.println("stuck");
                return;
            }
        }
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
//        if (isLeftKeyPressed || isRightKeyPressed) {
//            if ((Math.abs(this.getX() - entity.getX()) < 0.75 && this.getY() == entity.getY()) ||
//                    (Math.abs(this.getY() - entity.getY()) < 0.75 && this.getX() + 0.7 == entity.getX()))
//                return true;
//        }
//        else if (isUpKeyPressed || isDownKeyPressed) {
//            if ((Math.abs(this.getY() - entity.getY()) < 0.75 && this.getX() == entity.getX()) ||
//                    (Math.abs(this.getX() - entity.getX()) < 0.75 && this.getY() + 0.7 == entity.getY()))
//                return true;
//        }
        return this.rec.intersects(entity.rec.getLayoutBounds());
//        return false;
    }

    @Override
    public void update() {
        move();

    }

}
