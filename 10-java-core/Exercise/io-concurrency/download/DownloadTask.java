package io_concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class DownloadTask implements Runnable {

    private static final AtomicInteger completed = new AtomicInteger();
    private final String url;

    public DownloadTask(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            for (int p : new int[]{0,25,50,75,100}) {
                System.out.println(Thread.currentThread().getName()
                        + " Downloading " + url + " " + p + "%");
                Thread.sleep(500);
            }
            completed.incrementAndGet();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static int getCompleted() {
        return completed.get();
    }
}
