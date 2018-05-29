package threads;

import commonresource.CommonRes1;

public class Thread5 extends Thread{
	CommonRes1 CR;
    public Thread5(CommonRes1 CR){
        super("Thread5");
        System.out.println("Thread5 started");
        this.CR = CR;
	
        start();
    }    
    
    @Override
    public void run(){
        while(true){
          
        	CR.consume();
        	
        }
    }
}