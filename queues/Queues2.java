class Queues2 { // circular queue using array ....circular queue m add remove aur peek teeno m time O(1) lagta h

static class Queue {
static int arr[]; // is array ko static banaya h taaki doosre functions isko access kar paaye...aur abhi size declare nai kiya h ..vo constructor banate time karenge

static int size; //static variable banaya size naam se
static int rear = -1; 
static int front = -1; // ab front bhi define karna parega

Queue(int n) {
arr = new int[n]; //array ka size define kar diya jo input diya constructor m
this.size = n; // jo static size banaya tha uski value  constructor input vaale n ke equalho gai
}

public static boolean isEmpty() { //ab isEmpty ki condition change ho jaegi
return rear == -1 && front==-1; 

}

public static boolean isFull() {
    return (rear+1)%size == front; // ye condition bohot important h circular queue m ..isi condiditon ki help se jab front vaala element delete hoga aur naya element agar add kiya to vo front vaali posotion pe aayega aur rear circular way m change hga aur remove aur peek ki timeO(n) lagega


}

//add  O(1) ka time
public static void add(int data) {
  if(isFull()) {

    System.out.println("full queue");
    return;
}
//if queue not full

if(front==-1) { // yaani jab sabse phla element add kar rhe h to to front ko -1 se 0 karna parega ek baar
    front = 0;
}

rear = (rear+1)%size;
arr[rear] = data;
}

//dequeue  ..O(1) KA  time lagega..     remove function front element ko delete kar dega
public static int remove() { // return type int isiliye h taaki remove ke saath satah konsa element remove kiya vo bhi return ho jaye
if(isEmpty()) {
    System.out.println("empty queue");
    return -1; // just like in stacks
}
int result = arr[front]; 


//single element condition
if(rear==front) { // rearvfront ke equal h (0 ke equal)  yaani single element h bas 
      rear = front = -1;
}

else {
    front = (front+1)%size; // important   yaha front++ nai kar sakte kyu ki agar front last element pe hota aur next element circular ghoom ke peeche ki side hota to front++ kar ke us element pe nai jaa paate ..isi liye aise hi update karna parega front ko
}
return result;//front element ko remove kiya usi ko return kar diya
} 



//peek   O(1) ka time lagega
public static int peek() {
if(isEmpty()) {
    System.out.println("empty queue");
    return -1; // just like in stacks

}
return arr[front];
} 
} 

public static void main(String artgs[])  {
Queue q = new Queue(5);
q.add(1);
q.add(2);
q.add(3);
q.add(4);
q.add(5);
System.out.println(q.remove());
q.add(6); // jab upar 1 delete hoke print hua to front ban gya 2 aur jab 6 add karns h to vo 1 vaali jagah add hoga aur rear circular ghoom ke 5 se 6 ban jaega
System.out.println(q.remove()); // ab 2 remove hokje print hua..front ban jaega 3 aur ab jab neeche 7 add karenge vo 2 vaali jagah add hoga aur rear 7 ban jaega ..to aise circular queueu chalegi
q.add(7);


while(!q.isEmpty()) {
System.out.println(q.peek());
q.remove();
}


}
}