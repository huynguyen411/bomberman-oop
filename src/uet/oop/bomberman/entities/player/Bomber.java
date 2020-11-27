package uet.oop.bomberman.entities.player;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.ViewManager.Controller;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Rectangle;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Mob {
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isSpaceKeyPressed;
    private boolean isBombed = false;
    private int life = 3;

    private final Image[] animationPlayerUp = {Sprite.player_up.getFxImage(), Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage()};
    private final Image[] animationPlayerDown = {Sprite.player_down.getFxImage(), Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage()};
    private final Image[] animationPlayerLeft = {Sprite.player_left.getFxImage(), Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage()};
    private final Image[] animationPlayerRight = {Sprite.player_right.getFxImage(), Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage()};
    private final Image[] deadAnimation = {Sprite.player_dead1.getFxImage(), Sprite.player_dead2.getFxImage(), Sprite.player_dead3.getFxImage()};
    public Bomber(double x, double y, Image img) {
        super(x, y, img);
        this.rec = new Rectangle(x + 0.1, y + 0.1, 0.8, 1.02);
        this.speed = 0.04;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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
            } else if(keyEvent.getCode() == KeyCode.SPACE) {
                isSpaceKeyPressed = true;
            }
        });

        Controller.scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = false;
            } else if (keyEvent.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = false;
            } else if (keyEvent.getCode() == KeyCode.UP){
                isUpKeyPressed = false;
            } else if (keyEvent.getCode() == KeyCode.DOWN){
                isDownKeyPressed = false;
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                isSpaceKeyPressed = false;
            }
        });
    }

    private void move() {
        createListener();
        if (life > 0) {
            if (isDownKeyPressed && canMoveDown()) {
                moveDown(animationPlayerDown);
            } else if (isUpKeyPressed && canMoveUp()) {
                moveUp(animationPlayerUp);
            }
            if (isRightKeyPressed && canMoveRight()) {
                moveRight(animationPlayerRight);
            } else if (isLeftKeyPressed && canMoveLeft()) {
                moveLeft(animationPlayerLeft);
            }
        }
    }

    public void reborn() {
        this.setX(1);
        this.setY(1);
    }

    public void dead() {
        destroyedAnimation(deadAnimation);
    }
    public boolean touchEnemy() {
        for (Entity entity : Controller.entities) {
            if ((entity instanceof Enemy || entity instanceof Flame) && checkIfStuck(entity)) {
                life--;
                System.out.println(life);
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        if (touchEnemy()) {
            if (this.life > 0) {
                reborn();
            } else {
                this.setMark(true);
                dead();
            }
        }
        move();
    }

}
