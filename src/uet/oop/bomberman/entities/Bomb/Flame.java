package uet.oop.bomberman.entities.Bomb;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.ViewManager.Controller;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public static final Image[] animationBombLeft = { Sprite.explosion_horizontal_left_last.getFxImage(),
            Sprite.explosion_horizontal_left_last1.getFxImage(), Sprite.explosion_horizontal_left_last2.getFxImage(),
            Sprite.explosion_horizontal_left_last1.getFxImage(), Sprite.explosion_horizontal_left_last.getFxImage()};

    public static final Image[] animationBombRight = { Sprite.explosion_horizontal_right_last.getFxImage(),
            Sprite.explosion_horizontal_right_last1.getFxImage(), Sprite.explosion_horizontal_right_last2.getFxImage(),
            Sprite.explosion_horizontal_right_last1.getFxImage(), Sprite.explosion_horizontal_right_last.getFxImage()};

    public static final Image[] animationBombTop = { Sprite.explosion_vertical_top_last.getFxImage(),
            Sprite.explosion_vertical_top_last1.getFxImage(), Sprite.explosion_vertical_top_last2.getFxImage(),
            Sprite.explosion_vertical_top_last1.getFxImage(), Sprite.explosion_vertical_top_last.getFxImage()};

    public static final Image[] animationBombDown = { Sprite.explosion_vertical_down_last.getFxImage(),
            Sprite.explosion_vertical_down_last1.getFxImage(), Sprite.explosion_vertical_down_last2.getFxImage(),
            Sprite.explosion_vertical_down_last1.getFxImage(), Sprite.explosion_vertical_down_last.getFxImage()};

    public static final Image[] animationBombVertical = { Sprite.explosion_vertical.getFxImage(),
            Sprite.explosion_vertical1.getFxImage(), Sprite.explosion_vertical2.getFxImage(),
            Sprite.explosion_vertical1.getFxImage(), Sprite.explosion_vertical.getFxImage()};
    public static final Image[] animationBombHorizontal = { Sprite.explosion_horizontal.getFxImage(),
            Sprite.explosion_horizontal1.getFxImage(), Sprite.explosion_horizontal2.getFxImage(),
            Sprite.explosion_horizontal.getFxImage(), Sprite.explosion_horizontal.getFxImage()};

    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean canExplode(Bomb bomb) {
        if (this.getY() < 0 || this.getY() > Controller.HEIGHT || this.getX() < 0 || this.getX() > Controller.WIDTH)
            return false;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Brick || entity instanceof Wall) && x == entity.getX() && y == entity.getY())
                return false;
            if (entity instanceof Wall && ((x + 1 == entity.getX() && x + 2 == bomb.getX() && y == entity.getY()) ||
                    (x - 1 == entity.getX() && x - 2 == bomb.getX() && y == entity.getY()) ||
                    (y + 1 == entity.getY() && y + 2 == bomb.getY() && x == entity.getX()) ||
                    (y - 1 == entity.getY() && y - 2 == bomb.getY() && x == entity.getX()))) {
                return false;
            }
        }
        return true;
    }

    public boolean blockFLame(Bomb bomb) {
        for (Entity entity : Controller.stillObjects) {
            if (entity instanceof Wall && ((x + 1 == entity.getX() && x + 2 == bomb.getX() && y == entity.getY()) ||
                    (x - 1 == entity.getX() && x - 2 == bomb.getX() && y == entity.getY()) ||
                    (y + 1 == entity.getY() && y + 2 == bomb.getY() && x == entity.getX()) ||
                    (y - 1 == entity.getY() && y - 2 == bomb.getY() && x == entity.getX()))) {
                return false;
            }
        }
        return true;
    }

    public void checkCollision() {
        for (Entity entity : Controller.stillObjects) {
            if (entity instanceof Brick && this.checkIfStuck(entity)){
                System.out.println("boom");
                ((Brick) entity).breakBrick();
                Timeline animation = new Timeline(new KeyFrame(Duration.seconds(0.8), e -> {
                    entity.setMark(true);
                }));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();

                StringBuilder removeBrickFormMap = new StringBuilder(Controller.map[(int) entity.getY()]);
                removeBrickFormMap.setCharAt((int) entity.getX(), ' ');
                Controller.map[(int) entity.getY()] = removeBrickFormMap.toString();
            }
        }
    }
    @Override
    public void update() {

    }
}
