
public class stackclass { //3 major operations in stack data structure..1) push O(1) 2) pop O(1) 3) peek O(1)
    // stack data structure also called last in first out data structure(LIFO)
    //stack can be implemented using array,  arraylist and linked list
    // following is stack implementation using linked list

static class Node{
int data;
Node next;
public  Node(int data) {
this.data = data;
next = null;
}
}

static class Stack{
public static Node head; //ye static node h head yaani har function m ek hi head ki baat ho rhi h

//we first write a function to check whether our stack is empty or not
public static boolean isEmpty() {
return head==null; // head agar null h to true return kar dega yaani stack empty
}

//push
public static void push(int data) {
Node newNode = new Node(data);

if(isEmpty()) { // isEmpty ne agar true return kiya to ye if statement follow hogi yaani empty stack and hamare newNode ko head bana denge
    head = newNode;
    return;
}
newNode.next = head; // yaani stack empty nai h to newNode ke next ko head kar ke new Node ko head bana diua
head = newNode;

}
//pop
public static int pop() { // pop function top element ko delete bhi karta h aur us element ko return bhi karta h hence int return type
if(isempty()) { // if stack empty to kuch pop nai kar skte
    return -1; // -1 represents stack empty
}

int top = head.data;
    head = head.next;
    return top;

}
//peek
public static int peek() {
if(isempty()) { 
    return -1;
}
return head.data; // top element ko return kar diya


}

}
public static void main(String args[]) {
Stack s = new Stack();
s.push(1);
s.push(2);
s.push(3);
s.push(4);

while(s.isEmpty() ) {
System.out.println(s.peek()); // top element print kar diya 
s.pop(); // top element delete kar diuya taaki next elemet print ho paaye..tab tak print karaenge jab tak stack empty nahi ho jaata
System.out.println("fej");

}

}
}