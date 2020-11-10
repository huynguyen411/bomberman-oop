package uet.oop.bomberman.ViewManager;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private Stage stage;
    public static Scene scene;
    private AnimationTimer timer;

    public static Bomber bomberman;
    private GraphicsContext gc;
    private GraphicsContext gc1;
    private Canvas initialCanvas;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static double[] xBrick = {7, 8, 10, 15, 19, 22, 24 ,26, 7, 13, 15, 23 ,25, 27, 4, 10, 11, 12, 15, 22, 26, 28,
            11, 17, 19, 29, 13, 14, 17, 20, 19, 23 , 4, 11, 14, 21, 9, 17, 19, 6, 7, 10, 18, 3, 17, 12, 16, 19};
    public static double[] yBrick = { 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5,
            5, 5, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 11, 11, 11};

    public Controller() {
        start();
    }
    private void start() {
        // Tao Canvas
        initialCanvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = initialCanvas.getGraphicsContext2D();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc1 = canvas.getGraphicsContext2D();
        stage = new Stage();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(initialCanvas);
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        createMap();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        createBalloon();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //render();
                update();
            }
        };createBrick();
        timer.start();
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1 || (i % 2 == 0 && j % 2 == 0)) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }

        stillObjects.forEach(g -> g.render(gc));
    }

    private void createBrick() {
        int[] xAxis = new int[] {7, 8, 10, 15, 19, 22, 24 ,26, 7, 13, 15, 23 ,25, 27, 4, 10, 11, 12, 15, 22, 26, 28,
                11, 17, 19, 29, 13, 14, 17, 20, 19, 23, 1, 4, 11, 14, 21, 9, 17, 19, 1, 6, 7, 10, 18, 3, 17, 12, 16, 19};
        int[] yAxis = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5,
                5, 5, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 11, 11, 11};

        for (int i = 0; i < xAxis.length; i++) {
            Entity object;
            object = new Brick(xAxis[i], yAxis[i], Sprite.brick.getFxImage());
            stillObjects.add(object);
        }
    }

    private void createBalloon() {
        int[] xAxis = new int[] {13, 18, 24};
        int[] yAxis = new int[] {1, 3, 5};

        for (int i = 0; i < xAxis.length; i++) {
            Entity object;
            object = new Balloon(xAxis[i], yAxis[i], Sprite.balloon.getFxImage());
            entities.add(object);
        }
    }

    private void createOneal() {
        int[] xAxis = new int[] {17, 24};
        int[] yAxis = new int[] {1, 3};

        for (int i = 0; i < xAxis.length; i++) {
            Entity object;
            object = new Oneal(xAxis[i], yAxis[i], Sprite.oneal.getFxImage());
            entities.add(object);
        }
    }


    public void update() {
        gc1.clearRect(0, 0, initialCanvas.getWidth(), initialCanvas.getHeight());
        entities.forEach(Entity::update);
        entities.forEach(g -> g.render(gc1));
    }

    private void drawMap() {

    }

    public void render() {
//        gc.clearRect(0, 0, initialCanvas.getWidth(), initialCanvas.getHeight());
//        stillObjects.forEach(g -> g.render(gc));
//        createBrick();
//        createBalloon();
//        createOneal();
        entities.forEach(g -> g.render(gc1));
    }

//    private void moveDown() {
//        createListener();
//        for (int i = 0; i < Controller.yBrick.length; i++) {
//            if (bomberman.getY() ==  Controller.yBrick[i]) {
//                return;
//            }
//        }
//        if (isDownKeyPressed && !isUpKeyPressed){
//            bomberman.moveDown();
//            System.out.println(bomberman.getY());
//        }
//    }
}


