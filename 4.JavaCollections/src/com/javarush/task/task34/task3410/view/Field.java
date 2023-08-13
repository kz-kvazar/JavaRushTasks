package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Field extends JPanel {
    private final View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        Set<GameObject> all = view.getGameObjects().getAll();
        for (GameObject obj : all) {
            obj.draw(g);
        }
    }
    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }
}
