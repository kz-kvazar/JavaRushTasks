package com.javarush.task.task24.task2413;

import static sun.swing.MenuItemLayoutHelper.max;

public abstract class BaseObject {
    private double x;
    private double y;
    private double radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public boolean intersects(BaseObject o){
        double distanceAB = Math.hypot((this.x-o.getX()),(this.y-o.getY()));
        if (distanceAB <= Math.max(this.getRadius(), o.getRadius())){
            return true;
        }return false;
    }
    public abstract void draw(Canvas canvas);
    public abstract void move();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
