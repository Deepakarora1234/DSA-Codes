import java.util.*;
class Queues4 { // implementing queues using java collections framework
    public static void main(String args[]) {


            //how to declare queue.....Queue<Type> name = new LinkedList<Type>()......LinkedList aata h queue ko declare karte time

            //QUEUE java m interface hota h ...class nai hoti isi liye inka object nai banta aur linedlist aur arraydeque (ye double ended queue vaala deque h) ki help se hi implement karte h..dono m slight difference hota h bas
        Queue<Integer> q = new LinkedList<Integer>();
        //Queue<Integer> q = new Arraydeque<Integer>();  Arraydeque ko use kar ke imlementation

        //add, peek,remove, isEmpty ye saare functions collections m exist karte h

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