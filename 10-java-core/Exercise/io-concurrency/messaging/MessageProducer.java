package io_concurrency;

public class MessageProducer {
    MessageQueue q;
    public MessageProducer(MessageQueue q){ this.q=q; }
    public void produceMessages(int n){
        for(int i=0;i<n;i++)
            q.publish(new Message(i,"msg"+i,1));
        System.out.println("Produced "+n+" messages");
    }
}
