import java.util.concurrent.*;

public class postalOffice {
    private ThreadPoolExecutor managerSportelli;

    public postalOffice(int coreSize, int mSecs, int queueCap){
        this.managerSportelli = new ThreadPoolExecutor(coreSize, 4, mSecs, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueCap));
    }

    public void joinRoom2(Runnable customer){
        this.managerSportelli.submit(customer);
    }

    public void close(){
        managerSportelli.shutdown();
    }

    public boolean isStillWorking(){
       return this.managerSportelli.isTerminated();
    }




















}
