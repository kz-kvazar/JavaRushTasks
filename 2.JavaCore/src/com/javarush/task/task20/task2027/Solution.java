package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (String word : words) {
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[0].length; j++) {
                    if (crossword[i][j] == word.getBytes()[0]) {
                        checkDirectory(crossword, word, list, i, j, 0, 1);
                        checkDirectory(crossword, word, list, i, j, 0, -1);
                        checkDirectory(crossword, word, list, i, j, 1, 0);
                        checkDirectory(crossword, word, list, i, j, -1, 0);
                        checkDirectory(crossword, word, list, i, j, 1, 1);
                        checkDirectory(crossword, word, list, i, j, -1, 1);
                        checkDirectory(crossword, word, list, i, j, 1, -1);
                        checkDirectory(crossword, word, list, i, j, -1, -1);
                    }
                }
            }
        }
        return list;
    }

    private static void checkDirectory(int[][] crossword, String word, List<Solution.Word> list, int y, int x, int stepY, int stepX) {
        try {
            int startX = x;
            int startY = y;
            for (Byte b : word.getBytes()) {
                if (!(crossword[y][x] == b)) {
                    return;
                }
                y += stepY;
                x += stepX;
            }
            Word wordToAdd = new Word(word);
            wordToAdd.setStartPoint(startX, startY);
            wordToAdd.setEndPoint(x - stepX, y - stepY);
            list.add(wordToAdd);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }

}
