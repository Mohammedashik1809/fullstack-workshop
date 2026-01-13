package io_concurrency;

import java.util.concurrent.Executors;

public class MessagingTest {

    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();

        long start = System.currentTimeMillis();

        try (var exec = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int i=0;i<1000;i++)
                exec.submit(new MessageConsumer(queue));

            new MessageProducer(queue).produceMessages(10_000);
        }

        long end = System.currentTimeMillis();
        System.out.println("Processed in " + (end-start) + " ms");
    }
}
