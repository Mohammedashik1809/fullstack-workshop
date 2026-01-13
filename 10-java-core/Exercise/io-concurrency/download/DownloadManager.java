package io_concurrency;

import java.util.List;
import java.util.concurrent.*;

public class DownloadManager {

    private final ExecutorService executor;

    public DownloadManager(int threads) {
        executor = Executors.newFixedThreadPool(threads);
    }

    public void downloadAll(List<String> urls) {
        try {
            List<Future<?>> futures = urls.stream()
                    .map(u -> executor.submit(new DownloadTask(u)))
                    .toList();

            for (Future<?> f : futures) {
                f.get(30, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        System.out.println("Downloaded " + DownloadTask.getCompleted() + " files total");
    }
}
