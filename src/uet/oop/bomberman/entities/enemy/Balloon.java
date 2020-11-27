package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {
    private final Image[] leftAnimation = {Sprite.balloon_left1.getFxImage(), Sprite.balloon_left2.getFxImage(), Sprite.balloon_left3.getFxImage()};
    private final Image[] rightAnimation = {Sprite.balloon_right1.getFxImage(), Sprite.balloon_right2.getFxImage(), Sprite.balloon_right3.getFxImage()};
    private final Image[] deadAnimation = {Sprite.balloon_dead.getFxImage(), Sprite.mob_dead1.getFxImage(), Sprite.mob_dead2.getFxImage(), Sprite.mob_dead3.getFxImage()};
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        this.speed = 0.02;
    }

    @Override
    public void move() {
        switch (this.dir) {
            case 1:
                this.moving = canMoveLeft();
                if (canMoveLeft()) {
                    moveLeft(leftAnimation);
                }
                break;
            case 2:
                this.moving = canMoveRight();
                if (canMoveRight()) {
                    moveRight(rightAnimation);
                }
                break;
            case 3:
                this.moving = this.canMoveDown();
                if (this.canMoveDown()) {
                    this.moveDown(leftAnimation);
                }
                break;
            case 4:
                this.moving = this.canMoveUp();
                if (this.canMoveUp()) {
                    this.moveUp(rightAnimation);
                }
                break;
        }
    }

    @Override
    public void update() {
        this.dead(deadAnimation);
        if (!this.getMark) {
            if (!this.moving) {
                this.setDir();
            }
            this.move();
        }
    }

}
