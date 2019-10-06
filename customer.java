import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class customer implements Runnable {
    private int delay;
    private String name;
    SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

    public customer(int newDelay, String newName) throws NullPointerException {
        if(newDelay > 0)
            this.delay = newDelay;
        if(newName.equals(null))
            throw new NullPointerException();
        this.name = newName;
    }

    public customer(String newName) throws NullPointerException {
        Random timeNeeded = new Random();
        this.delay = timeNeeded.nextInt(4000) + 1000;
        if(newName.equals(null))
            throw new NullPointerException();
        this.name = newName;
    }

    public void run() {
        try {
            Date date = new Date();
            System.out.println(formatter.format(date) + " " + Thread.currentThread().getName() + ": servendo il cliente " + this.name);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!this.name.equals(null)) {
            Date date = new Date();
            System.out.println(formatter.format(date) + " " + Thread.currentThread().getName() + " ha servito il cliente " + this.name + " in " + this.delay + " millisecondi.");
        }
    }
}











