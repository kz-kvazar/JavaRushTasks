package com.javarush.task.task35.task3513;

public class MoveEfficiency {
    private int score;
    private int numberOfEmptyTiles;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.score = score;
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }
}
