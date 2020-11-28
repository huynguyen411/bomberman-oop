package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.viewmanager.Controller;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.viewmanager.Controller.stillObjects;

public class Brick extends Entity{
    public static final Image[] animationBrick = {Sprite.brick_exploded.getFxImage(), Sprite.brick_exploded1.getFxImage(),
                Sprite.brick_exploded2.getFxImage()};
    private int movement = animationBrick.length;
//    private boolean mark = false;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }
    public void breakBrick() {
        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
            if (movement > 0) {
                this.img = animationBrick[animationBrick.length - movement];
                movement--;
            }
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        if (Controller.map[(int) y].charAt((int) x) == 'f' || Controller.map[(int) y].charAt((int) x) == 's' ||
                Controller.map[(int) y].charAt((int) x) == 'b') {
            for (Entity entity : stillObjects) {
                if (entity instanceof Item && entity.getX() == x && entity.getY() == y)
                    ((Item) entity).setShown(true);
            }
        }
    }

    @Override
    public void update() {

    }
}
