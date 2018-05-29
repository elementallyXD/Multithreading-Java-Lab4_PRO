package stack;

//import java.lang.Throwable;

public class Stack {
    private int size;
    private final int maxSize;
    private final int minSize;
    private int [] stack;
    
    public Stack(int maxSize, int minSize){
        stack = new int[maxSize];
        size = 0;
        this.maxSize = maxSize;
        this.minSize = minSize;
    }
    
    public int getSize(){
        return size;
    }
    
    public void push(int el){
        stack[size] = el;
        size++;   
    }
    
    public int pop() {
             size--;
            return stack[size];     
    }
}