package uet.oop.bomberman.entities.Mob;

import javafx.scene.image.Image;
import uet.oop.bomberman.viewmanager.Controller;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Rectangle;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Wall;

public class Mob extends Entity {
    protected double speed;
    protected int indexUp = 0;
    protected int indexDown = 0;
    protected int indexLeft = 0;
    protected int indexRight = 0;

    public Mob(double x, double y, Image img) {
        super(x, y, img);
    }

    public boolean checkIfStuck(Entity entity) {
        return this.rec.collision(entity.getRec());
    }

    public boolean canMoveRight() {
        Rectangle temp = new Rectangle(this.x + speed, this.y, this.rec.getW(), this.rec.getH());
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && temp.collision(entity.getRec())) {
                return false;
            }
        }
        for (Entity entity : Controller.entities) {
            if (entity instanceof Bomb && temp.collision(entity.getRec())) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveLeft() {
        Rectangle temp = new Rectangle(this.x - speed, this.y, this.rec.getW(), this.rec.getH());
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && temp.collision(entity.getRec())) {
                return false;
            }
        }
        for (Entity entity : Controller.entities) {
            if (entity instanceof Bomb && temp.collision(entity.getRec())) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveUp(){
        Rectangle temp = new Rectangle(this.x , this.y - speed, this.rec.getW(), this.rec.getH());
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && temp.collision(entity.getRec())) {
                return false;
            }
        }
        for (Entity entity : Controller.entities) {
            if (entity instanceof Bomb && temp.collision(entity.getRec())) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveDown() {
        Rectangle temp = new Rectangle(this.x , this.y + speed, this.rec.getW(), this.rec.getH());
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && temp.collision(entity.getRec())) {
                return false;
            }
        }
        for (Entity entity : Controller.entities) {
            if (entity instanceof Bomb && temp.collision(entity.getRec())) {
                return false;
            }
        }
        return true;
    }

    public void moveRight(Image[] animationRight) {
        this.setX(x + speed);
        this.x = (double) Math.round(x * 100) / 100;
        this.img = animationRight[indexRight % 12 / 4];
        indexRight++;
    }

    public void moveLeft(Image[] animationLeft) {
        this.setX(x - speed);
        this.x = (double) Math.round(x * 100) / 100;
        this.img = animationLeft[indexLeft % 12 / 4];
        indexLeft++;
    }

    public void moveDown(Image[] animationDown) {
        this.setY(y + speed);
        this.y = (double) Math.round(y * 100) / 100;
        this.img = animationDown[indexDown % 12 / 4];
        indexDown++;
    }

    public void moveUp(Image[] animationUp) {
        this.setY(y - speed);
        this.y = (double)Math.round(y * 100) / 100;
        this.img = animationUp[indexUp % 12 / 4];
        indexUp++;
    }

    @Override
    public void update() {

    }
}
