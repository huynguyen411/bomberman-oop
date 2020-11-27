package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
        super.img = Sprite.powerup_speed.getFxImage();
    }

}
