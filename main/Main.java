package main;

import commonresource.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import threads.*;

public class Main {

    public static void main(String[] args) {
        Semaphore sem1 = new Semaphore(0, true);
        Semaphore sem2 = new Semaphore(0, true);
        
        ReentrantLock mutex = new ReentrantLock();
        
        CyclicBarrier brp = new CyclicBarrier(2);
        
        CommonRes1 CR1 = new CommonRes1();
        CommonRes2 CR2 = new CommonRes2();
       
        new Thread1(CR1,brp);
        new Thread2(CR1,brp);
        new Thread4(CR1);
        new Thread5(CR1);
        
        CyclicBarrier br1 = new CyclicBarrier(2);
        
        new Thread3(CR2,br1,mutex,sem1,sem2);
        new Thread6(CR2,br1,mutex,sem1,sem2);
    }
    
}