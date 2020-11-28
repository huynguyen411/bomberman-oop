package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.viewmanager.Controller;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.bomb.Flame;

import java.util.Random;

public abstract class Enemy extends Mob {
    protected int dir;
    protected boolean moving = false;

    public Enemy(double x, double y, Image img) {
        super(x, y, img);
    }

    public abstract void move();

    public boolean dead(Image[] deadAnimation) {
        for (Entity entity : Controller.entities) {
            if (entity instanceof Flame && checkIfStuck(entity)) {
                this.dir = 0;
                destroyedAnimation(deadAnimation);
                return true;
            }
        }
        return false;
    }

    public void setDir() {
        Random rand = new Random();
        dir = 1 + rand.nextInt(4);
    }
    @Override
    public abstract void update();

}
