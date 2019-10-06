import java.util.concurrent.CopyOnWriteArrayList;

public class customerGenerator extends Thread {
    private CopyOnWriteArrayList<customer> room1;
    long workingTime;
    int room1Size;
    int customerToGenerate;
    int prefixedCustomerNumber;

    public customerGenerator(CopyOnWriteArrayList<customer> newRoom1, int newRoom1Size, long newWorkingTime) {
        this.room1 = newRoom1;
        this.workingTime = newWorkingTime;
        this.room1Size = newRoom1Size;
        this.prefixedCustomerNumber = 0;
    }

    public customerGenerator(CopyOnWriteArrayList<customer> newRoom1, int numberOfCustomers){
        this.room1 = newRoom1;
        this.customerToGenerate = numberOfCustomers;
        this.room1Size = numberOfCustomers;
        this.prefixedCustomerNumber = 1;
    }


    public void run() {
        long startTime = System.currentTimeMillis();
        long frequency = 1000;
        if(prefixedCustomerNumber == 0) {
            while ((System.currentTimeMillis() - startTime) <= workingTime) {
                if (room1.size() < room1Size) {
                    room1.add(new customer());
                    System.out.println("Un nuovo cliente è arrivato in sala 1 ");
                }
                pause(frequency);
            }
            System.out.println("\nArrivo di nuovi clienti terminato.\n");
        }
        else{
            int numberOfStartingCustomer = customerToGenerate;
            while(customerToGenerate > 0){
                if (room1.size() < room1Size){
                    room1.add(new customer());
                    customerToGenerate--;
                }
            }
            System.out.println("\nSono arrivati " + numberOfStartingCustomer + " clienti in sala 1.\n");
        }
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
