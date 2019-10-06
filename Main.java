package com.company;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RejectedExecutionException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
	    int room1size = askRoomSize(1);
	    int room2size = askRoomSize(2);
	    int workingTime = askWorkingTime();
	    scanner.close();

        PostalOffice ufficio = new PostalOffice(3, 2500, room2size);
        CopyOnWriteArrayList<customer> room1 = new CopyOnWriteArrayList<>();
        customerGenerator room1Filler = new customerGenerator(room1, room1size, workingTime);

        customer nextCustomer = null;
        boolean customerJoinedRoom2;
        boolean customerFromRoom1;

        room1Filler.start();
        while(room1.size() > 0 || room1Filler.isAlive()) {
            try {
                nextCustomer = room1.get(0);
                customerFromRoom1 = true;
            } catch (IndexOutOfBoundsException e) {
                pause(1000);
                customerFromRoom1 = false;
            }
            if (customerFromRoom1) {
                try {
                    ufficio.joinRoom2(nextCustomer);
                    customerJoinedRoom2 = true;
                } catch (RejectedExecutionException e) {
                    customerJoinedRoom2 = false;
                }
                if (customerJoinedRoom2)
                    room1.remove(0);
            }
        }
        ufficio.close();
    }

    static int askRoomSize(int roomNumber){
        System.out.println("Inserire dimensione della stanza " + roomNumber + ":");
        int result;
        while( (result = scanner.nextInt()) < 1 )
            System.out.println("Dimensione non valida: inserire valore >= 1");
        return result;
    }

    static int askWorkingTime(){
        System.out.println("Inserire per quanti secondi si vuole accettare nuovi clienti nell' ufficio:");
        int result;
        while( (result = scanner.nextInt()) < 1 )
            System.out.println("Dimensione non valida: inserire valore >= 1");
        return result * 1000;
    }

    private static void pause(long timeTowait){
        try{
            Thread.sleep(timeTowait);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}