package threads;

import commonresource.CommonRes2;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Thread6 extends Thread{
    private CyclicBarrier cb1;
    private CommonRes2 cr2;
    private ReentrantLock mutex;
    private Semaphore sem1,sem2;
   
    public Thread6(CommonRes2 cr2, CyclicBarrier cb1, ReentrantLock mutex,Semaphore sem1,Semaphore sem2){
        super("Thread6");
        System.out.println("Thread6 started");
        this.cr2 = cr2;
        this.cb1 = cb1;
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.mutex = mutex;
	
        start();
    }    
    
    @Override
    public void run(){
        while(true){
            System.out.println("Thread6 wait sync with Thread3 with CyclicBarrier");
            try {
                cb1.await();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } catch (BrokenBarrierException ex) {
                System.out.println(ex.getMessage());
            }
             System.out.println("Thread6 work after sync with Thread3 with CyclicBarrier");

           System.out.println("Thread6 wait sync with Thread3 by Semaphore");
            
           sem1.release();
            try {
                sem2.acquire();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Thread6 work after sync with Thread3 by Semaphore");
            
            System.out.println("Thread6 lock mutex and start to use with CR2");
            mutex.lock();
                System.out.println("val1 = " + cr2.val_1 + "\n" + 
                                    "val2 = " + cr2.val_2 + "\n" +
                                    "val3 = " + cr2.val_3 + "\n" +
                                    "val4 = " + cr2.val_4 + "\n" +
                                     "val5 = " + cr2.val_5 + "\n" +
                                     "val6 = " + cr2.val_6 + "\n" +
                                     "val7 = " + cr2.val_7 + "\n" +
                                      "val8 = " + cr2.val_8);
                cr2.stopcount--;
            mutex.unlock();
            System.out.println("Thread6 lock mutex and start to use with CR2");
            
            if(cr2.stopcount == 0) {
            	System.out.println ("Thread6 goes die\n");
            	break;
            } 
        }
        
    }
}