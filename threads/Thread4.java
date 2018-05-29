package threads;

import commonresource.CommonRes1;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Thread4 extends Thread{
	CommonRes1 CR;
    
    public Thread4(CommonRes1 CR){
        super("Thread4");
        System.out.println("Thread4 started");
        this.CR = CR;
	
        start();
    }    
    
    @Override
    public void run(){
        while(true){
        	
        	if(CR.stopcount == 0) {
            	System.out.println ("Thread4 goes die\n");
            	break;
            } 
            
            CR.produce();
            
        
        }
    }
}