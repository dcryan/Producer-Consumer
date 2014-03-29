/**
 * Created with IntelliJ IDEA.
 * User: danielryan
 * Date: 11/5/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Consumer extends Thread
{
    protected ProducerConsumerClient.CustomBuffer buffer;

    public Consumer(ProducerConsumerClient.CustomBuffer buffer)
    {
        this.buffer = buffer;
    }

    public void consume() throws InterruptedException
    {
        int consumedNumber = buffer.get();
    }

    @Override
    public void run() {
        try
        {
//            Thread.sleep(1000);
            for(int i = 0 ; i < 10 ; i++)
                consume();
        }
        catch (InterruptedException e){}
    }
}
