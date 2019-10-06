import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;
import java.util.LinkedList;

public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int customerNumber = askCustomers();
        int queueSizeRoom2 = askQueueSizeRoom2();
        long startTime = System.nanoTime();
        scanner.close();
        LinkedList<customer> room1 = fillRoom1(customerNumber);
        postalOffice room2 = new postalOffice(2, 3000, queueSizeRoom2 );
        Integer customerId = 1;
        customer nextCustomer;
        boolean needToAdd = false;
        while(room1.size() > 0){
            nextCustomer = room1.poll();
            try {
                room2.joinRoom2(nextCustomer);
                needToAdd = true;
            }
            catch(RejectedExecutionException e){
                room1.addFirst(nextCustomer);
                needToAdd = false;
            }
            if(customerNumber == 0 && needToAdd) {
                room1.add(new customer(customerId.toString()));
                customerId++;
            }
        }
        room2.close();
        long endTime = System.nanoTime();
        long millisecondElapsed = (endTime - startTime)/1000000;
        System.out.println("Tutti i clienti sono entrati nella seconda sala in " + millisecondElapsed + " millisecondi.");

    }

    public static int askCustomers() {
        System.out.printf("-Inserire numero n >= 0 di clienti:\nn = 0  ==> flusso continuo di clienti\nn > 0  ==> arriveranno n clienti\n");
        int result;
        while ((result = scanner.nextInt()) < 0)
            System.out.println("Numero non valido, inserire un valore >= 0");
        return result;
    }

    public static LinkedList<customer> fillRoom1(int customerNumber) {
        LinkedList<customer> list1 = new LinkedList<customer>();
        Integer i;
        Random timeNeeded = new Random();
        if (customerNumber == 0)
            customerNumber = 1;
        for(i = 1; i <= customerNumber; i++){
            list1.add(new customer(timeNeeded.nextInt(4000) + 1000, i.toString()));
        }
        return list1;
    }

    public static int askQueueSizeRoom2(){
        System.out.printf("-Inserire numero k > 0 di clienti ammessi nella seconda sala:\n");
        int result;
        while ((result = scanner.nextInt()) <= 0)
            System.out.println("Numero non valido, inserire un valore > 0");
        return result;
    }

}