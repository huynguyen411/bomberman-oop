package uet.oop.bomberman.entities.player;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import uet.oop.bomberman.ViewManager.Controller;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Flame;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.BombItem;
import uet.oop.bomberman.entities.Item.FlameItem;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Item.SpeedItem;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static uet.oop.bomberman.ViewManager.Controller.entities;
import static uet.oop.bomberman.ViewManager.Controller.stillObjects;

public class Bomber extends Entity {
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

    private int indexUp = 0;
    private int indexDown = 0;
    private int indexLeft = 0;
    private int indexRight = 0;
    public Bomber(double x, double y, Image img) {
        super(x, y, img);
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
    public void moveRight() {
        if (indexRight == 12)
            indexRight = 0;
        this.x += STEP * 5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.x -= STEP * 5;
            } else {
                this.rec.setX(this.x);
            }
        }
//        this.x = (double) Math.round(x * 100) / 100;
        this.img = animationPlayerRight[indexRight / 4];
        indexRight++;
    }

    public void moveLeft() {
        if (indexLeft == 12)
            indexLeft = 0;
        this.x -= STEP*5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.x += STEP * 5;
            } else {
                this.rec.setX(this.x);
            }
        }
//        this.x = (double)Math.round(x * 100) / 100;
        this.img = animationPlayerLeft[indexLeft / 4];
        indexLeft++;
    }

    public void moveDown() {
        if (indexDown == 12)
            indexDown = 0;
        this.y += STEP*5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.y -= STEP * 5;
            } else {
                this.rec.setY(this.y);
            }
        }
//        this.y = (double)Math.round(y * 100) / 100;
        this.img = animationPlayerDown[indexDown / 4];
        indexDown++;
    }

    public void moveUp() {
        if (indexUp == 12)
            indexUp = 0;
        this.y -= STEP*5;
        for (Entity entity : Controller.stillObjects) {
            if ((entity instanceof Wall || entity instanceof Brick) && checkIfStuck(entity)) {
                this.y += STEP * 5;
            } else {
                this.rec.setY(this.y);
            }
        }
//        this.y = (double)Math.round(y * 100) / 100;
        this.img = animationPlayerUp[indexUp / 4];
        indexUp++;
    }

    private void move() {
        if (buffSpeed)
            STEP = 0.015;
        else
            STEP = 0.01;
        createListener();

        if (isRightKeyPressed){
            moveRight();
        } else if (isLeftKeyPressed) {
            this.moveLeft();
        } else if (isDownKeyPressed) {
            this.moveDown();
        } else if (isUpKeyPressed) {
            this.moveUp();
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
    @Override
    public void update() {
        move();
    }

}
