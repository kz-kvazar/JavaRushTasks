package com.javarush.task.task25.task2514;

/* 
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/

public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        int i = 1;
        while (i < 5) {
            Thread thread = new Thread(new YieldRunnable(i++));
            thread.start();
        }
    }
}
