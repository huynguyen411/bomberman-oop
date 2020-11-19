package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;


public abstract class Entity {
    public static double STEP = 0.01;
    protected double x;
    protected double y;
    protected Image img;
    protected Rectangle rec;

    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.rec = new Rectangle(x + STEP*10, y + STEP*10, img.getWidth()/Sprite.SCALED_SIZE - STEP*10, img.getHeight()/Sprite.SCALED_SIZE - STEP*10);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
    public abstract void update();
}
