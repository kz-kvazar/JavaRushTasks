package com.javarush.task.task22.task2213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс Field описывает "поле клеток" игры Тетрис
 */
public class Field {
    //ширина и высота
    private final int width;
    private final int height;

    //матрица поля: 1 - клетка занята, 0 - свободна
    private int[][] matrix;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Метод возвращает значение, которое содержится в матрице с координатами (x,y)
     * Если координаты за пределами матрицы, метод возвращает null.
     */
    public Integer getValue(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return matrix[y][x];

        return null;
    }

    /**
     * Метод устанавливает переданное значение(value) в ячейку матрицы с координатами (x,y)
     */
    public void setValue(int x, int y, int value) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            matrix[y][x] = value;
    }

    /**
     * Метод печатает на экран текущее содержание матрицы
     */
    public void print() {
        //Создаем массив, куда будем "рисовать" текущее состояние игры
        int[][] canvas = new int[height][width];

        //Копируем "матрицу поля" в массив
        for (int i = 0; i < height; i++) {
            System.arraycopy(matrix[i], 0, canvas[i], 0, width);
        }

        //Копируем фигурку в массив, только непустые клетки
        int left = Tetris.game.getFigure().getX();
        int top = Tetris.game.getFigure().getY();
        int[][] brickMatrix = Tetris.game.getFigure().getMatrix();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (top + i >= height || left + j >= width) continue;
                if (brickMatrix[i][j] == 1)
                    canvas[top + i][left + j] = 2;
            }
        }


        //Выводим "нарисованное" на экран, но начинаем с "границы кадра".
        System.out.println("---------------------------------------------------------------------------\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int index = canvas[i][j];
                if (index == 0)
                    System.out.print(" . ");
                else if (index == 1)
                    System.out.print(" X ");
                else if (index == 2)
                    System.out.print(" X ");
                else
                    System.out.print("???");
            }
            System.out.println();
        }


        System.out.println();
        System.out.println();
    }

    void removeFullLines() {
        List<int[]> lines = new ArrayList<>();
        for (int[] row : matrix) {
            boolean isFull = true;
            for (int cell : row) {
                if (cell == 0) {
                    isFull = false;
                    break;
                }
            }
            if (!isFull) {
                lines.add(row);
            }
        }
        while (lines.size() < height) {
            lines.add(0, new int[width]);
        }
        matrix = lines.toArray(new int[height][width]);
    }
}
