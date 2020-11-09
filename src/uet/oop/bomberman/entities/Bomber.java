package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.ViewManager.Controller;

public class Bomber extends Entity {

    public Bomber(double x, int y, Image img) {
        super(x, y, img);
    }
    public void movement() {
        super.x += (double) 0.1;
        super.x = (double)Math.round(x * 100) / 100;

    }
    public void moveDown() {
        super.y += (double) 0.1;
        super.y = (double)Math.round(y * 100) / 100;
    }
    public double getX() {
        return super.x;
    }
    public double getY() {
        return super.y;
    }

    @Override
    public void update() {

    }

}
