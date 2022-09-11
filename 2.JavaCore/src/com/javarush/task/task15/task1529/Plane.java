package com.javarush.task.task15.task1529;

public class Plane implements CanFly{
    @Override
    public void fly() {

    }

    private int pasagerCount;
    public Plane(int pasagerCount) {
        setPasagerCount(pasagerCount);
    }

    public int getPasagerCount() {
        return pasagerCount;
    }

    public void setPasagerCount(int pasagerCount) {
        this.pasagerCount = pasagerCount;
    }
}
