package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;


public abstract class Entity {
    public static double STEP = 0.01;
    protected double x;
    protected double y;
    protected Image img;
    protected boolean mark;
    protected int layer;
    protected Timeline t = new Timeline();
    public Rectangle rec;


    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.mark = false;
        this.rec = new Rectangle(x, y, 1, 1);
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setX(double x) {
        this.x = x;
        this.rec.setX(x);
    }

    public void setY(double y) {
        this.y = y;
        this.rec.setY(y);
    }

    public void animation(Image[] animation) {
        t.setCycleCount(1);
        for (int i=0; i<animation.length;i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5*(i+1)), event -> this.setImg((animation[finalI]))));
        }
        t.play();
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getMark() {
        return mark;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public void destroyedAnimation(Image[] animation) {
        t.setCycleCount(1);
        for (int i=0; i<animation.length;i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5*(i+1)), event -> this.setImg(animation[finalI])));
        }
        t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5*(animation.length+1)), event -> this.mark = true));
        if (((Mob) this).getLife() == 0) {
            if (this instanceof Bomber) {
                t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), event -> new Sound("player_die_1.wav").play()));
            } else if (this instanceof Enemy) {
                t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), event -> new Sound("enemy_die.wav").play()));
            }
        }
        t.play();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public boolean checkIfStuck(Entity entity) {
        return this.rec.collision(entity.rec);
    }

    public abstract void update();
}
