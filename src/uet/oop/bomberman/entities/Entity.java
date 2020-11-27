package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;


public abstract class Entity {

    protected double x;
    protected double y;
    protected Image img;
    protected Rectangle rec;
    protected boolean getMark = false;
    protected Timeline t = new Timeline();

    public boolean getMark() {
        return getMark;
    }

    public void setMark(boolean getMark) {
        this.getMark = getMark;
    }

    public void setX(double x) {
        this.x = x;
        this.rec.setX(x);
    }

    public void setY(double y) {
        this.y = y;
        this.rec.setY(y);
    }

    public Rectangle getRec() {
        return rec;
    }

    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.rec = new Rectangle(x, y, 1.025, 1.025);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    public void destroyedAnimation(Image[] animation) {
        t.setCycleCount(1);
        for (int i=0; i<animation.length;i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5*(i+1)), event -> this.setImg(animation[finalI])));
        }
        t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5*(animation.length+1)), event -> this.getMark = true));
        t.play();
    }



    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public abstract void update();
}
