package io_concurrency;

import java.util.concurrent.*;

public class MessageQueue {
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    public void publish(Message m){ queue.offer(m); }
    public Message consume() throws InterruptedException { return queue.take(); }
}
