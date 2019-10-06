import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class customer implements Runnable {
    private int delay;
    private String name;
    SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");
    private static Integer i = 1;

    public customer(){
        Random timeNeeded = new Random();
        this.delay = timeNeeded.nextInt(4000) + 1000;
        this.name = i.toString();
        i++;
    }

    public void run() {
        try {
            Date date = new Date();
            System.out.println(formatter.format(date) + " " + Thread.currentThread().getName() + ": servendo il cliente " + this.name);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        System.out.println(formatter.format(date) + " " + Thread.currentThread().getName() + " ha servito il cliente " + this.name + " in " + this.delay + " millisecondi.");
    }

    public static int customerServed(){
        return i - 1;
    }
}
