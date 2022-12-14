package com.javarush.task.task16.task1617;

/* 
Отсчет на гонках
*/

public class Solution {
    public static int numSeconds = 4;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        Thread.sleep(3500);
        clock.interrupt();
        //add your code here - добавь код тут
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {
            try {
                while (!isInterrupted() && numSeconds > 0) {
                    System.out.print(numSeconds + " ");
                    Thread.sleep(1000);
                    numSeconds--;
                }
                System.out.print("Марш!");
            } catch (InterruptedException e) {
                System.out.print("Прервано!");
            }
            //add your code here - добавь код тут
        }
    }
}
