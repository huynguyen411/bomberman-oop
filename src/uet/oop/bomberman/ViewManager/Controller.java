package uet.oop.bomberman.ViewManager;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static String[] map;

    public void createMapFromFile() {
        try {
            Scanner scf = new Scanner(new BufferedReader(new FileReader("res/levels/Level1.txt")));
            int row = scf.nextInt();
            row = scf.nextInt();
            map = new String[row];
            int col = scf.nextInt();
            String s = scf.nextLine();

            for (int i = 0; i < row; i++) {
                map[i] = scf.nextLine();
                for (int j = 0; j < map[i].length(); j++) {
                    char key = map[i].charAt(j);
                    Entity object = null;
                    switch (key) {
                        case '#': {
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
                        case '*': {
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
                        case '1': {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            Entity balloon = new Balloon(j, i, Sprite.balloon_left1.getFxImage());
                            entities.add(balloon);
                            break;
                        }
                        case '2': {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            Entity enemy = new Balloon(j, i, Sprite.oneal_left1.getFxImage());
                            entities.add(enemy);
                            break;
                        }
                        default: {
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
                    }
                }
            }
            stillObjects.forEach(g -> g.render(gc));
        } catch (Exception e) {
        System.out.println(e.getMessage());
    }

}
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
        stage.setResizable(false);
        stage.show();

        createMapFromFile();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //render();
                update();
            }
        };
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


//    private void createBalloon() {
//        int[] xAxis = new int[] {13, 18, 24};
//        int[] yAxis = new int[] {1, 3, 5};
//
//        for (int i = 0; i < xAxis.length; i++) {
//            Entity object;
//            object = new Balloon(xAxis[i], yAxis[i], Sprite.balloon.getFxImage());
//            entities.add(object);
//        }
//    }

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


