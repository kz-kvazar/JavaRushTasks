package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;

public class TestOrder extends Order{
    @Override
    protected void initDishes() throws IOException {
        super.initDishes();
        double random = Math.random();
        dishes.add(Dish.FISH);
        dishes.add(Dish.SOUP);
        dishes.add(Dish.STEAK);
        dishes.add(Dish.JUICE);
    }

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }
}
