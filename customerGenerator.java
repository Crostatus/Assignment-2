package com.company;
import java.util.concurrent.CopyOnWriteArrayList;

public class customerGenerator extends Thread {
    private CopyOnWriteArrayList<customer> room1;
    long workingTime;
    int room1Size;

    public customerGenerator(CopyOnWriteArrayList<customer> newRoom1, int newRoom1Size, long newWorkingTime) {
        this.room1 = newRoom1;
        this.workingTime = newWorkingTime;
        this.room1Size = newRoom1Size;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        long frequency = 1000;
        while ((System.currentTimeMillis() - startTime) <= workingTime) {
            if (room1.size() < room1Size) {
                room1.add(new customer());
                System.out.println("Un nuovo cliente è arrivato in sala 1 ");
            }
            pause(frequency);
        }
        System.out.println("\nArrivo di nuovi clienti terminato.\n");
    }

    private void pause(long pauseTime){
        try{
            Thread.sleep(pauseTime);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
