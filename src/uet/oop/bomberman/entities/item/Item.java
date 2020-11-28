package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Item extends Entity {
    protected boolean isShown = false;

    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }

    public boolean getIsShown() {
        return isShown;
    }

    @Override
    public void update() {

    }
}
