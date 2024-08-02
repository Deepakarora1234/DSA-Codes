import java.util.*;    // or java.util.PriorityQueue
public class Heaps
{
    /*
     * concept of priority queues:-
     * 
     * priority queue is a queue in which elements are stored such as the first element has the highest priority...
     * if priority is on the basis of ascending order to jab PQ me se remove karenge to apne aap hi sab se chotta element phle niklega...hum queues ki tarah hi PQ ko Integer String Character ya kisi class
     * ke object ke type ka bana sakte h..
     * if Integer type ki PQ banai h to by default smalllest element ki sab se jaada priority hoti h,..
     * this priority can also be changed..
     * agar kisi class ke objects ki priority queue anai h to to priority kis cheez ke basis pe hogi ye hume khud se define karna parega using Comaparable interface of java and overriding a compareTo funciton
     * 
     * we implement PQ by java collections framework..
     * using this collections framework..for Pq we have 3 operations
     * 
     * add - O(logn)    remove-O(logn)   and peek  O(1)
     */

     static class Student implements Comparable<Student>   // ye graph m para tha ki jab hume sort karane ho object kisi class ki property ke basis pe jaise yaha rank ke basis pe to hume class ko comparable banana parega using comparable interface and then we need to override the compare to function and then wirte  the logic od comparison(ascending or descending)
     {
        String name;
        int rank;
        public Student(String n, int r)
        {
            this.name = n;
            this.rank = r;
        }
        @Override
        public int compareTo(Student s2)
        {
            return this.rank-s2.rank;   // ye hamare ascending order ke liye h logic..for descending..s2.rank - this.rank
        }
     }
    public static void main(String args[])
    {
        //defining a PQ

        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // pq.add(3);
        // pq.add(2);
        // pq.add(4);
        // while(!pq.isEmpty())
        // {
        //     System.out.println(pq.remove());    // add phle 3 kiya tha lkm  remove hoke phle 2 nikla as it is PQ and by default phle smallest element niklega
        // }

        //if we want to change this priortiy and want to get the max element first..:-
        // PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());

        // pq1.add(3);
        // pq1.add(2);
        // pq1.add(4);
        // while(!pq1.isEmpty())
        // {
        //     System.out.println(pq1.remove());   //ab phle max element niklega
        // }

        // to define a PQ of class objects
      PriorityQueue<Student> pq = new PriorityQueue<>();
      pq.add(new Student("abc", 10));
      pq.add(new Student("ab", 5));
      pq.add(new Student("a", 20));
      pq.add(new Student("abcd", 1));
      while(!pq.isEmpty())
      {
        Student s = pq.remove();
        System.out.println(s.rank);   // to rank ke basis pe jiskii kam rank thi vo phle niklega priority queue se kyu ki ye logic humne class m define kar diya tha ..agar jaada rank vaala chahiye to vahi comparator,reverse order
      }



        
    }
}