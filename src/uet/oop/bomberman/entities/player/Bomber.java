package uet.oop.bomberman.entities.player;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Mob.Mob;
import uet.oop.bomberman.entities.Rectangle;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.viewmanager.Controller;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.item.BombItem;
import uet.oop.bomberman.entities.item.FlameItem;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static uet.oop.bomberman.viewmanager.Controller.entities;
import static uet.oop.bomberman.viewmanager.Controller.stillObjects;

public class Bomber extends Mob {
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isSpaceKeyPressed;
    private boolean isBombed = false;
    private boolean buffRange = false;
    private boolean buffSpeed = false;
    private boolean buffBomb = false;

    private final Image[] animationPlayerUp = {Sprite.player_up.getFxImage(), Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage()};
    private final Image[] animationPlayerDown = {Sprite.player_down.getFxImage(), Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage()};
    private final Image[] animationPlayerLeft = {Sprite.player_left.getFxImage(), Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage()};
    private final Image[] animationPlayerRight = {Sprite.player_right.getFxImage(), Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage()};
    private final Image[] deadAnimation = {Sprite.player_dead1.getFxImage(), Sprite.player_dead2.getFxImage(), Sprite.player_dead3.getFxImage()};

    private int countDown = 2;
    public Bomber(double x, double y, Image img) {
        super(x, y, img);
        super.rec = new Rectangle(x + 0.1, y + 0.1, 0.8, 1.02);
        this.speed = 0.04;
        this.life = 3;
    }

    public int getLife() {
        return life;
    }

    private void createListener() {
        Controller.scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = true;
            } else if (keyEvent.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = true;
            } else if (keyEvent.getCode() == KeyCode.UP){
                isUpKeyPressed = true;
            } else if (keyEvent.getCode() == KeyCode.DOWN){
                isDownKeyPressed = true;
            } else if(keyEvent.getCode() == KeyCode.SPACE) {
                isSpaceKeyPressed = true;
            }
        });

        Controller.scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = false;
            } else if (keyEvent.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = false;
            }
            if (keyEvent.getCode() == KeyCode.UP){
                isUpKeyPressed = false;
            } else if (keyEvent.getCode() == KeyCode.DOWN){
                isDownKeyPressed = false;
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                isSpaceKeyPressed = false;
            }
        });
    }

    private void move() {
        if (buffSpeed)
            STEP = 0.015;
        else
            STEP = 0.01;
        createListener();

        if (life > 0) {
            if (isDownKeyPressed && canMoveDown()) {
                moveDown(animationPlayerDown);
            } else if (isUpKeyPressed && canMoveUp()) {
                moveUp(animationPlayerUp);
            }
            if (isRightKeyPressed && canMoveRight()) {
                moveRight(animationPlayerRight);
            } else if (isLeftKeyPressed && canMoveLeft()) {
                moveLeft(animationPlayerLeft);
            }
        }
        getItem();
    }

    public void setBuffRange(boolean buffRange) {
        this.buffRange = buffRange;
    }

    public boolean getBuffRange() {
        return buffRange;
    }

    public void dropBomb() {
        createListener();
        if (isSpaceKeyPressed && !isBombed && canBeDroppedBombed()) {
            makeABomb();
            new Sound("bomb_put.wav").play();
        }
    }

    private boolean canBeDroppedBombed() {
        if (Controller.map[(int) this.getY()].charAt((int) this.getX()) != '#'
            && Controller.map[(int) this.getY()].charAt((int) this.getX()) != '*' )
            return true;
        return false;
    }

    private void makeABomb() {
        int[] X = {(int) x - 1, (int) x + 1, (int) x, (int) x, (int) x - 2, (int) x + 2, (int) x, (int) x};
        int[] Y = {(int) y, (int) y, (int) y + 1, (int) y - 1, (int) y, (int) y, (int) y + 2, (int) y - 2};

        ArrayList<Flame> flames = new ArrayList<>();

        //buff range when get item
        for (int i = 0; i < X.length; i++) {
            if (!buffRange) {
                if (X[i] - (int) x == -1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombLeft[0]);
                    flames.add(flame);
                } else if (X[i] - (int) x == 1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombRight[0]);
                    flames.add(flame);
                } else if (Y[i] - (int) y == -1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombTop[0]);
                    flames.add(flame);
                } else if (Y[i] - (int) y == 1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombDown[0]);
                    flames.add(flame);
                }
            } else {
                if (X[i] - (int) x == -1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombHorizontal[0]);
                    flames.add(flame);
                } else if (X[i] - (int) x == 1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombHorizontal[0]);
                    flames.add(flame);
                } else if (Y[i] - (int) y == -1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombVertical[0]);
                    flames.add(flame);
                } else if (Y[i] - (int) y == 1) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombVertical[0]);
                    flames.add(flame);
                } else if (X[i] - (int) x == -2) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombLeft[0]);
                    flames.add(flame);
                } else if (X[i] - (int) x == 2) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombRight[0]);
                    flames.add(flame);
                } else if (Y[i] - (int) y == -2) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombTop[0]);
                    flames.add(flame);
                } else if (Y[i] - (int) y == 2) {
                    Flame flame = new Flame(X[i], Y[i], Flame.animationBombDown[0]);
                    flames.add(flame);
                }
            }
        }

        Bomb bomb = new Bomb((int) this.getX(), (int) this.getY(), Bomb.animationBomb[0]);
        entities.add(bomb);

        isBombed = true;
        double time;
        bomb.timeDelay(flames);
        final int[] countDown = {2};
        if (buffBomb) {
            time = 0.1;
        } else {
            time = 0.8;
        }
        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(time), e -> {
            if (countDown[0] == 0) {
                isBombed = false;
            }
            countDown[0]--;
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        System.out.println("bombed");
    }

    public void getItem() {
        AtomicInteger countDown = new AtomicInteger(1);
        for (Entity entity : stillObjects) {
            if (entity instanceof Item && checkIfStuck(entity) && ((Item) entity).getIsShown()) {
                if (entity instanceof FlameItem) {
                    buffRange = true;
                }
                else if (entity instanceof SpeedItem) {
                    buffSpeed = true;
                }
                else if (entity instanceof BombItem) {
                    buffBomb = true;
                }
                Timeline animation = new Timeline(new KeyFrame(Duration.seconds(30), e -> {
                    if (countDown.get() > 0) {
                        buffRange = false;
                        buffSpeed = false;
                        buffBomb = false;
                        countDown.getAndDecrement();
                    }
                }));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
                entity.setMark(true);
            }
        }
    }

    public void reborn() {
        this.setX(1);
        this.setY(1);
    }

    public void dead() {
        destroyedAnimation(deadAnimation);
    }
    public boolean touchEnemy() {
        for (Entity entity : Controller.entities) {
            if ((entity instanceof Enemy || entity instanceof Flame) && checkIfStuck(entity)) {
                this.life--;
                System.out.println(life);
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        if (touchEnemy()) {
            if (this.life > 0) {
                reborn();
                new Sound("die_restart.wav").play();
            } else {
                this.setMark(true);
                dead();
            }
        }
        move();
    }

}
