
package commonresource;

import stack.Stack;

public class CommonRes1 {
    public static final int maxSize = 10;
    public static final int minSize = 0;
    Stack stack = new Stack(maxSize, minSize); 
    int ind = 0;
    boolean IsEmpty = ind  == minSize;
    boolean IsFull = ind == maxSize;
    	
    synchronized public void consume()
    {
        while (IsEmpty){
            try
            {	wait(); 
            }
            catch (InterruptedException e)
            {	
                
                System.err.println("InterruptedException" + e.getMessage());
            }
        }
        
       
            System.out.println("Element : " + stack.pop() + " - was consumed by " + Thread.currentThread().getName() + "\n");
            ind--;
        
        
        IsEmpty = ind == minSize;
	IsFull = false;

        notify();
    }
	
    
	synchronized public void produce ()
	{
            while (IsFull){
                try
                {
                    wait();
                    
                }
                catch (InterruptedException e)
                {	System.out.println("InterruptedException");
                }
            }
            stack.push(ind);
            System.out.println("Element : " + ind +" - was produced by " + Thread.currentThread().getName() + "\n");
            
            ind++;
            IsFull = ind == maxSize;
            IsEmpty = false;
            notify();
	}
}