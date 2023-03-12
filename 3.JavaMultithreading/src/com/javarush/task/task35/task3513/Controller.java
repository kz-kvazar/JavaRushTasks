package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_ESCAPE){
            resetGame();
        } else if (!model.canMove()) {
            view.isGameLost = true;
        }if (!view.isGameLost && !view.isGameWon){
            if ( keyCode == KeyEvent.VK_LEFT){
                model.left();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                model.right();
            } else if (keyCode == KeyEvent.VK_UP) {
                model.up();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                model.down();
            }
        }
        if (model.maxTile == WINNING_TILE){
            view.isGameWon = true;
        }
        view.repaint();
    }

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }
    
    public int getScore(){
        return model.score;
    }
    public void resetGame(){
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }
}