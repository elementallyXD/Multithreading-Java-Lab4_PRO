package threads;

import commonresource.CommonRes1;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Thread1 extends Thread{
    
    CommonRes1 CR;
    Semaphore sem1,sem2;
    CyclicBarrier brp;

    public Thread1(CommonRes1 CR, CyclicBarrier brp){
        super("Thread1");
        System.out.println("Thread1 started");
        this.CR = CR;
        this.brp = brp;
	
        start();      
    }    
    
    @Override
    public void run(){
        while(true){
        	if(CR.stopcount <= 0) {
            	brp.reset();
             	System.out.println ("Thread1 goes die\n");
             	break;
             } 
        	
            System.out.println("Thrad1 stands before CyclicBarrier and wait full sync with Thread2");
            try {
               if (CR.stopcount > 0) brp.await();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } catch (BrokenBarrierException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Thrad1 works after full sync with Thread2");
            
            CR.produce();
           
        }
        
    }
}