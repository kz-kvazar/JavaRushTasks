package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private Tile[][] gameTiles;
    private static final int FIELD_WIDTH = 4;

    public Model() {
        resetGameTiles();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTile = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTile.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTile;
    }

    private void addTile() {
        int tileValue = Math.random() < 0.9 ? 2 : 4;
        List<Tile> emptyTile = getEmptyTiles();
        if (!emptyTile.isEmpty()) {
            Tile randomTile = emptyTile.get((int) (Math.random() * emptyTile.size()));
            randomTile.value = tileValue;
        }
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
}