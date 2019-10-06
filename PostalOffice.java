package com.company;

import java.util.concurrent.*;

public class PostalOffice {
    private ThreadPoolExecutor managerSportelli;

    public PostalOffice(int coreSize, int mSecs, int queueCap) {
        this.managerSportelli = new ThreadPoolExecutor(coreSize, 4, mSecs, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueCap));
    }

    public void joinRoom2(Runnable customer) {
        this.managerSportelli.submit(customer);
    }

    public void close() {
        managerSportelli.shutdown();
    }
}
