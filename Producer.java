import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: danielryan
 * Date: 11/5/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Producer extends Thread
{
    protected ProducerConsumerClient.CustomBuffer buffer;
    protected Random rand;

    public Producer(ProducerConsumerClient.CustomBuffer buffer)
    {
        this.buffer = buffer;
        rand = new Random();
    }

    public void produce() throws InterruptedException
    {
        int producedNumber = rand.nextInt(100);
        buffer.put(producedNumber);
    }

    @Override
    public void run() {
        try
        {
            for(int i = 0 ; i < 10 ; i++)
                produce();
        }
        catch (InterruptedException e){}
    }
}
