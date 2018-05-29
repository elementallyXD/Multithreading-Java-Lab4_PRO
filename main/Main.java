package main;

import commonresource.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import threads.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
    	
    	System.out.println("Program started!");
        Semaphore sem1 = new Semaphore(0, true);
        Semaphore sem2 = new Semaphore(0, true);
        
        ReentrantLock mutex = new ReentrantLock();
        
        CyclicBarrier brp = new CyclicBarrier(2);
        
        CommonRes1 CR1 = new CommonRes1(10);
        CommonRes2 CR2 = new CommonRes2(10);
       
        Thread t1 = new Thread1(CR1,brp);
        Thread t2 = new Thread2(CR1,brp);
        Thread t4 = new Thread4(CR1);
        Thread t5 = new Thread5(CR1);
        
        CyclicBarrier br1 = new CyclicBarrier(2);
        
        Thread t3 = new Thread3(CR2,br1,mutex,sem1,sem2);
        Thread t6 = new Thread6(CR2,br1,mutex,sem1,sem2);
      
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
       
        System.out.println("Program ended!");
    }
    
}