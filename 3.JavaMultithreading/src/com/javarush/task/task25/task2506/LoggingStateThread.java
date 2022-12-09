package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    Thread thread;
    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        //setDaemon(true);
    }

    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(state);
        
        while (!state.equals(State.TERMINATED)){
            if (state != thread.getState()){
                System.out.println(thread.getState());
                state = thread.getState();
            }
        }
    }
}
