package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bomber extends Entity {

    public Bomber(double x, int y, Image img) {
        super(x, y, img);
    }
    public void movement() {
        super.x += (float)1/3;
    }

    @Override
    public void update() {

    }

}
