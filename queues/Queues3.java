class Queues3 { //here we implement queue using linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }


    static class Queue {
        static Node head = null;
        static Node tail = null; // BHI HEAD AUR tail dono ko null rakha h
      
    
    
    public static boolean isEmpty() { //ab isEmpty ki condition change ho jaegi
    return head == null && tail==null; 
    
    }
    
    
    
    //enque(add)
    public static void add(int data) {
        Node newNode = new Node(data);
        
        if(isEmpty()) {
            tail = head = newNode;
        }


        tail.next = newNode;
        tail = newNode;

    }
    
    //dequeue  ...     remove function front element ko delete kar dega
    public static int remove() { // return type int isiliye h taaki remove ke saath satah konsa element remove kiya vo bhi return ho jaye
    if(isEmpty()) {
        System.out.println("empty queue");
        return -1; // just like in stacks
    }
    int front = head.data;
    if(head == tail) { // yaani single element tha bas
        tail = null;

    }

   
    head = head.next;
    return front;
   
   
    } 
    
    
    
    //peek   
    public static int peek() {
    if(isEmpty()) {
        System.out.println("empty queue");
        return -1; // just like in stacks
    
    }
    return head.data;
    } 
    } 
    
    public static void main(String artgs[])  {
    Queue q = new Queue();
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


  
