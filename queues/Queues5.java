import java.util.*;
public class Queues5 {  // here we implemet queue using 2 stacks..this is not an efficient way of implementing queues
                 // if we use stacks..either time of push(add) is O(n)  or time of remove(pop) and peek is O(n)  and we need all 3 to be O(1)..hence inefficient
                 //here we will use algorithm where time of only add is O(n)
static class Queues{
    static Stack<Integer> s1 = new Stack<Integer>();
    static Stack<Integer> s2 = new Stack<Integer>();

    //isEmpty function
    public static boolean isEmpty() {
        return s1.isEmpty(); // queue will be empty if there are no elements in stack 1....and we use in built function of collections ..i.e., s1.isEmpty()

    }

    //add
    public static void add(int data) {
        while(!s1.isEmpty()) {

        
        s2.push(s1.pop()); //naya element sab se neeche daalne ke liye s1 se saare purane elements utha ke ek ek kar ke s2 me daale jab tak s1 empty nai hua
        }
        s1.push(data); // ab s1 is empty and we add our element

        while(!s2.isEmpty()) { // now s2 se ek ek kar ke vapas rakh diye elements

        
        s1.push(s2.pop());
        }
        
    }
    //remove
    public static int remove() {
        if(isEmpty()) {
            System.out.println("empty queue");
            return -1;
        }

        return s1.pop(); // s1 ka top elemenrt hi queue ka front h vo remove kar ke rety=urn kara diya
    }
    //peek
    public static int peek() {
        if(isEmpty()) {
            System.out.println("empty queue");
            return -1;
        }

        return s1.peek(); 


    }


}
   
   
     public static void main(String args[]) {
        Queues q = new Queues();

        
        q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    q.add(5);
   
    
    while(!q.isEmpty()) {
    System.out.println(q.peek());
    q.remove();
        
        
    }
}
}