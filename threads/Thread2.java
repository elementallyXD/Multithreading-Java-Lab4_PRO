package threads;

import commonresource.CommonRes1;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread2 extends Thread{
	CommonRes1 CR;
    Semaphore sem1,sem2;
    CyclicBarrier brp;
    
    public Thread2(CommonRes1 CR, CyclicBarrier brp){
        super("Thread2");
        System.out.println("Thread2 started");
        this.CR = CR;
        this.brp = brp;
	
        start();
    }    
    
    @Override
    public void run(){
        
       while(true){

           System.out.println("Thread2 stands before CyclicBarrier and wait full sync with Thread1");
            try {
                brp.await();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } catch (BrokenBarrierException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Thread2 works after full sync with Thread1");
            
            if(CR.stopcount == 0) {
            	
               	System.out.println ("Thread2 goes die\n");
               	break;
               } 
            
            CR.consume(); 
            
       }
       brp.reset();
    }
}