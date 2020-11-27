package uet.oop.bomberman.ViewManager;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Flame;
import uet.oop.bomberman.entities.Item.BombItem;
import uet.oop.bomberman.entities.Item.FlameItem;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Item.SpeedItem;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.player.Bomber;
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
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(object);
                            break;
                        }
                        case 'f': {
                            object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(object);
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        }
                        case 'b': {
                            object = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(object);
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        }
                        case 's': {
                            object = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(object);
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
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
        } catch (Exception e) {
        System.out.println(e.getMessage());
    }

}
    public Controller(Stage menuStage) {
        menuStage.hide();
        start();
    }
    private void start() {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        stage = new Stage();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        createMapFromFile();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
//                render();
                update();
            }
        };
        timer.start();

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }


    public void update() {

//        gc1.clearRect(0, 0, initialCanvas.getWidth(), initialCanvas.getHeight());
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        bomberman.dropBomb();
        delete();
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(Entity::update);
        entities.forEach(g -> g.render(gc));
    }

    public void render() {
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void delete() {
        stillObjects.removeIf(entity -> (entity instanceof Brick || entity instanceof Item) && entity.getMark());
        entities.removeIf(entity -> (entity instanceof Flame || entity instanceof Bomb) && entity.getMark());
    }
}


