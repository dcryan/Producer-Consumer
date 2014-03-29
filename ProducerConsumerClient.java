/**
 * Created with IntelliJ IDEA.
 * User: danielryan
 * Date: 11/5/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProducerConsumerClient {
    public static class CustomBuffer
    {
        int[] buffer;
        int size;
        int bufferLimit;

        public CustomBuffer(int bufferLimit)
        {
            this.buffer = new int[bufferLimit];
            this.size = 0;
            this.bufferLimit = bufferLimit;
        }

        public synchronized void put(int producedNumber) throws InterruptedException
        {
            while(size >= bufferLimit)
            {
                wait();
            }

            buffer[size] = producedNumber;
            size++;

            System.out.println("Produced: " + producedNumber);

            notify();
        }

        public synchronized int get() throws InterruptedException
        {
            while(size <= 0)
            {
                wait();
            }

            size--;
            int consumedNumber = buffer[size];

            System.out.println("Consumed: " + consumedNumber);

            notify();
            return consumedNumber;
        }
    }
    public static void main(String[] args)
    {
        CustomBuffer buffer = new CustomBuffer(10);
        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);

        Thread prod = new Thread(p);
        Thread cons = new Thread(c);

        prod.start();
        cons.start();

        // Wait for the threads to finish
        try {
            prod.join();
            cons.join();
        } catch (InterruptedException e) {return;}

    }
}
