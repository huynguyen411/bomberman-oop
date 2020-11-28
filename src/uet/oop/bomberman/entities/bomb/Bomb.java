package uet.oop.bomberman.entities.bomb;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;

import static uet.oop.bomberman.viewmanager.Controller.bomberman;
import static uet.oop.bomberman.viewmanager.Controller.entities;
import static uet.oop.bomberman.entities.bomb.Flame.*;

public class Bomb extends Entity {

    private Sound explosionSound = new Sound("bomb_explosion_1.wav");

    public void setExplosionSound(Sound explosionSound) {
        this.explosionSound = explosionSound;
    }

    public static final Image[] animationBomb = {Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(),
            Sprite.bomb_2.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb.getFxImage(), Sprite.bomb_exploded.getFxImage(),
            Sprite.bomb_exploded1.getFxImage(), Sprite.bomb_exploded2.getFxImage(), Sprite.bomb_exploded1.getFxImage(),
            Sprite.bomb_exploded.getFxImage()};

    private int timeExplosion = animationBomb.length;
    private Timeline animation;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setImage(Image image) {
        super.img = image;
    }
    

    public void timeDelay(ArrayList<Flame> flames) {
        animation = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
            if (timeExplosion > 0) {
                this.img = animationBomb[animationBomb.length - timeExplosion];
                if (timeExplosion == 5) {
                    for(Flame flame : flames) {
                        if (flame.blockFLame(this)) {
                            flame.checkCollision();
                        }
                    }
                    for(Flame flame : flames) {
                        if (flame.canExplode(this)) {
                            entities.add(flame);
                        }
                    }
                }

                if (timeExplosion < 5 && timeExplosion > 0) {
                    if (!bomberman.getBuffRange()) {
                        flames.get(0).setImg(animationBombLeft[animationBombLeft.length - timeExplosion]);
                        flames.get(1).setImg(animationBombRight[animationBombRight.length - timeExplosion]);
                        flames.get(2).setImg(animationBombDown[animationBombTop.length - timeExplosion]);
                        flames.get(3).setImg(animationBombTop[animationBombDown.length - timeExplosion]);
                    } else {
                        flames.get(0).setImg(animationBombHorizontal[animationBombHorizontal.length - timeExplosion]);
                        flames.get(1).setImg(animationBombHorizontal[animationBombHorizontal.length - timeExplosion]);
                        flames.get(2).setImg(animationBombVertical[animationBombVertical.length - timeExplosion]);
                        flames.get(3).setImg(animationBombVertical[animationBombVertical.length - timeExplosion]);
                        flames.get(4).setImg(animationBombLeft[animationBombLeft.length - timeExplosion]);
                        flames.get(5).setImg(animationBombRight[animationBombRight.length - timeExplosion]);
                        flames.get(6).setImg(animationBombDown[animationBombTop.length - timeExplosion]);
                        flames.get(7).setImg(animationBombTop[animationBombDown.length - timeExplosion]);
                    }
                }
                timeExplosion--;
            }
            if (timeExplosion == 0) {
                for (Flame flame : flames) {
                    flame.setMark(true);
                }
                this.setMark(true);
            }
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    @Override
    public void update() {

    }
}
