package io_concurrency;

public class MessageConsumer implements Runnable {
    MessageQueue q;
    public MessageConsumer(MessageQueue q){ this.q=q; }
    public void run(){
        try{
            while(true){
                Message m=q.consume();
                Thread.sleep(2);
            }
        }catch(Exception ignored){}
    }
}
