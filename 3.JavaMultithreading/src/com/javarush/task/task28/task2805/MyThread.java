package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static volatile AtomicInteger priority = new AtomicInteger(1);

    private synchronized void setPriority() {
        int newPriority = priority.getAndIncrement();
        newPriority = getThreadGroup() != null && newPriority > getThreadGroup().getMaxPriority() ? getThreadGroup().getMaxPriority() : newPriority;
        setPriority(newPriority);

        if (priority.get() > MAX_PRIORITY){
            priority.set(1);
        }
    }

    public MyThread() {
        setPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority();
    }

    public MyThread(String name) {
        super(name);
        setPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority();
    }
}
