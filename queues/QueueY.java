class QueuesY { //queue contains front and rear....also called first in first out structure(FIFO)
                // 3 operations on queues ..1. enque (Add)  2.Dequeue (remove)  3.peek(front element ko dekhna)  teeno operations m O(1) ka time lagna chahiye

       //implementation of queue using array..add m to O(1) ka time lagta h..baaki dono operations m O(n) lagta h isisliye array se implement karna queue ko jaada achha nai hota ..aur array ka size bhi fixed hota h to ye bhi acgha nai hota
    static class Queue {
        static int arr[]; // is array ko static banaya h taaki doosre functions isko access kar paaye...aur abhi size declare nai kiya h ..vo constructor banate time karenge
       
       static int size; //static variable banaya size naam se
       static int rear = -1;  //front banane ki jaroorat nai vo 0 hi hota h 

        Queue(int n) {
            arr = new int[n]; //array ka size define kar diya jo input diya constructor m
            this.size = n; // jo static size banaya tha uski value  constructor input vaale n ke equalho gai
         }

         public static boolean isEmpty() {
            return rear == -1; // jaise stack m banaya tha..if queue empty means rear is still-1...to true return hoga

         }

         public static void add(int data) {
            if(rear == size-1) { // rear n-1 index pe h yaani last index means full queue
                System.out.println("full queue");
                return;
            }
            //if queue not full
            rear++; //rear  abhi last element pe tha ab ++ kiya ek aage baraya aur usme data store kar diya
            arr[rear] = data;
         }

            //dequeue  ..O(n) KA TIME laga is operation me 
         public static int remove() { // return type int isiliye h taaki remove ke saath satah konsa element remove kiya vo bhi return ho jaye
            if(isEmpty()) {
                System.out.println("empty queue");
                return -1; // just like in stacks
            }
            int front = arr[0]; // hume is function se queue ka front element delete karna h..to front delete kar ke aage ke elements ko ek ek peeche shift karna parega..taaki naya front ,mil jaaye

            for(int i = 0; i <rear; i++) {
                arr[i] = arr[i+1];
            }
            rear--; // har element peeche shift hua to rear ki value bhi ek position peeche shift ho jaegi
            return front; // jo element delete kiya (initially jo front tha ) usi ko return kar diya

         } 

         //peek
         public static int peek() {
            if(isEmpty()) {
                System.out.println("empty queue");
                return -1; // just like in stacks

         }
         return arr[0];
    } 
} 

    public static void main(String artgs[])  {
        Queue q = new Queue(5);
        q.add(1);
        q.add(2);
        q.add(3);

        while(!q.isEmpty()) {
            System.out.println(q.peek());
            q.remove();
        }
        

    }
}