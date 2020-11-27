package uet.oop.bomberman.entities;

public class Rectangle {
    private double x;
    private double y;
    private double w;
    private double h;

    public Rectangle(double _x, double _y, double _w, double _h) {
        this.x = _x;
        this.y = _y;
        this.w = _w;
        this.h = _h;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public boolean collision(Rectangle o) {
        double distX = Math.abs((this.x + this.w/2) - (o.x + o.w/2));
        double distY = Math.abs((this.y + this.h/2) - (o.y + o.h/2));
        double distW = (this.w + o.w)/2;
        double distH = (this.h + o.h)/2;
        return (distW - distX > 0.05 && distH - distY > 0.05);
    }

}