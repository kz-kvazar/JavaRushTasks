package com.javarush.task.task37.task3711;

public class Computer {

    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        hardDrive = new HardDrive();
    }
    public void run(){
        cpu.calculate();
        memory.allocate();
        hardDrive.storeData();
    }
}
