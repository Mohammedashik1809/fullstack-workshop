package io_concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageProcessor {

    private final MessageQueue queue;
    private final AtomicInteger processedCount = new AtomicInteger();

    public MessageProcessor(MessageQueue queue) {
        this.queue = queue;
    }

    public void startProcessing(int consumerCount, int totalMessages) {

        long start = System.currentTimeMillis();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < consumerCount; i++) {
                executor.submit(() -> {
                    try {
                        while (true) {
                            Message msg = queue.consume();
                            process(msg);
                            if (processedCount.incrementAndGet() >= totalMessages) {
                                break;
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }

        long end = System.currentTimeMillis();

        long duration = end - start;
        System.out.println("Processed " + processedCount.get() + " messages");
        System.out.println("Time taken: " + duration + " ms");
        System.out.println("Average processing time: " +
                (duration * 1.0 / processedCount.get()) + " ms per message");
    }

    private void process(Message message) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
