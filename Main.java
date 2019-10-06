import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RejectedExecutionException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = askCustomerNumber();
        int room1size;
        if(n == 0)
            room1size = askRoomSize(1);
        else
            room1size = n;

        int room2size = askRoomSize(2);

        int workingTime = 0;
        if(n == 0)
            workingTime = askWorkingTime();

        scanner.close();

        PostalOffice ufficio = new PostalOffice(3, 2500, room2size);
        CopyOnWriteArrayList<customer> room1 = new CopyOnWriteArrayList<>();

        customerGenerator room1Filler;
        if(n == 0)
            room1Filler = new customerGenerator(room1, room1size, workingTime);
        else
            room1Filler = new customerGenerator(room1, n);

        customer nextCustomer = null;
        boolean customerJoinedRoom2;
        boolean customerFromRoom1;

        long startTime = System.currentTimeMillis();
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
        printStatistics(ufficio, startTime);
    }

    static void printStatistics(PostalOffice officeToGetReportFrom, long officeStartTime){
        while(!officeToGetReportFrom.allCustomersGotServed()){
            //aspetta che tutti i clienti siano stati serviti
        }
        long elapsedTime = System.currentTimeMillis() - officeStartTime;
        System.out.flush();
        System.out.println("\nSono stati serviti " + customer.customerServed() + " clienti in " + elapsedTime + " millisecondi.\n");

    }

    static int askCustomerNumber(){
        System.out.println("Inserire numero n >= 0 di clienti da far arrivare all' ufficio postale");
        System.out.println("n = 0  ==> arriverà un nuovo cliente ogni secondo.");
        System.out.println("n > 0  ==> arriveranno n clienti.");
        int result;
        while( (result = scanner.nextInt()) < 0 )
            System.out.println("Numero non valido: inserire valore >= 0");
        return result;
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
