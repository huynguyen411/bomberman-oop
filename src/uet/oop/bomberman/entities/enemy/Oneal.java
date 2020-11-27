package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private final Image[] onealDeadAnimation = {Sprite.oneal_dead.getFxImage(), Sprite.mob_dead1.getFxImage(), Sprite.mob_dead2.getFxImage(), Sprite.mob_dead3.getFxImage()};
    private final Image[] onealLeftAnimation = {Sprite.oneal_left1.getFxImage(), Sprite.oneal_left2.getFxImage(), Sprite.oneal_left3.getFxImage()};
    private final Image[] onealRightAnimation = {Sprite.oneal_right1.getFxImage(), Sprite.oneal_right2.getFxImage(), Sprite.oneal_right3.getFxImage()};
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        this.speed = 0.025;
    }

    private int wayCount() {
        int count = 0;
        if (this.canMoveDown()) {
            count++;
        }
        if (this.canMoveUp()) {
            count++;
        }
        if (this.canMoveLeft()) {
            count++;
        }
        if (this.canMoveRight()) {
            count++;
        }
        return count;
    }
    @Override
    public void move() {
        switch (this.dir) {
            case 1:
                this.moving = this.canMoveLeft();
                if (this.canMoveLeft()) {
                    this.moveLeft(onealLeftAnimation);
                }
                break;
            case 2:
                this.moving = this.canMoveRight();
                if (this.canMoveRight()) {
                    this.moveRight(onealRightAnimation);
                }
                break;
            case 3:
                this.moving = this.canMoveDown();
                if (this.canMoveDown()) {
                    this.moveDown(onealLeftAnimation);
                }
                break;
            case 4:
                this.moving = this.canMoveUp();
                if (this.canMoveUp()) {
                    this.moveUp(onealRightAnimation);
                }
                break;
        }
    }

    @Override
    public void update() {
        this.dead(onealDeadAnimation);
        if (!this.getMark) {
            if (!this.moving) {
                this.setDir();
            } else if (Math.abs(this.x - (int)this.x) < 0.025 && Math.abs(this.y - (int)this.y) < 0.025 && wayCount() > 2) {
                this.setDir();
            }
            this.move();
        }
    }
}
