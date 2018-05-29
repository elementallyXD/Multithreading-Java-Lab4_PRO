package threads;

import commonresource.CommonRes2;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread3 extends Thread{
    private CommonRes2 cr2;
    private CyclicBarrier cb1;
    private ReentrantLock mutex;
    private Semaphore sem1,sem2;
     
    public Thread3(CommonRes2 cr2,CyclicBarrier cb1,ReentrantLock mutex,Semaphore sem1,Semaphore sem2){
        super("Thread3");
        System.out.println("Thread3 started");
        this.cr2 = cr2;
        this.cb1 = cb1;
        this.sem1 = sem1;
        this.sem2 = sem2;        
        this.mutex = mutex;
	
        start();
    }    
    
    @Override
    public void run(){
        int i = 87;
        while(true){
        	if(cr2.stopcount <= 0) {
        		sem2.release();
        		cb1.reset();
        		System.out.println ("Tread3 goes die\n");
            	break;
            } 
        	/*if(cr2.stopcount != 0) {
        		sem2.release();
        		cb1.reset();
            } */
        	
            System.out.println("Thread3 wait sync with Thread6 by CyclicBarrier (cb1)");
            try {
                if (cr2.stopcount >0)
                	cb1.await();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } catch (BrokenBarrierException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Thread3 work after sync with Thread6 by CyclicBarrier (cb1)");
                       
            System.out.println("Thread3 lock mutex and started working with CR2");
            
            mutex.lock();
            
            System.out.println("Thread3 modify CR2");
            
            cr2.val_1 = i-3;
            cr2.val_2 = 3*i+4;
            cr2.val_3 = i*i-9;
            cr2.val_4 = i*(i+4)*i;
            cr2.val_5 = (2*i+3)*(i*i-7);
            cr2.val_6 = (i*7)/(3*i*i);
            cr2.val_7 = false;
            cr2.val_8 = true;     
            cr2.stopcount--;
            
            mutex.unlock();
            
            System.out.println("Thread3 unlock mutex and finished working with CR2");
            
            System.out.println("Thread3 wait sync with Thread6 by Semaphore");
            sem2.release();
            try {
                sem1.acquire();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Thread3 work after sync with Thread6 by Semaphore");
        }
        
        
    }
}