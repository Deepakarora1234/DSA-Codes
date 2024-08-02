class LL{ /*in array list, time complexity of inserting an element in b/w is O(n) and of search is O(1).
          in linked list, "     "        "     "      "     "      "    is O(1) and that of search is O(n).
          linked list is of variable size
          non continuous memory
          basic structure of linked list is called node.
          node contains two things ...data and next pointer(that points to next node in the linked list)
          first node is called head node and last node is called tail node.  linked list are of3 types..singular, double,circular
          */
Node head;

private int size;
LL() {
    this.size= 0;
}

class Node{
    String data;
    Node next;
    Node(String data) { //paframeterized comstructor banaya 
        this.data = data;
        this.next = null;//naya node bana rhe h to next by default null hoga ...means node create ho rha h ek bsd..list create nahi ho rhi
        
        size++;
    }
}

//add- first, last...add first means adding a node at first position...kuch na diya ho to add ka matlab add last as it is difficult to implement

public void addFirst(String data) {
    Node newNode = new Node(data);

    //if head is null..yaani ek node banani h hame aur use hi head bana dena h
    if(head==null) {
        head = newNode;
        return;
    }
    //if head is not null..yaani ab nai node ka next existing head ki taraf point karana h...aur fir nai node ko head bana dena h.

    newNode.next = head;
    head = newNode;
}

//add last

public void addLast(String data) {

    //addFirst ki tarah check karenge ki head khaali to nahi h
    Node newNode = new Node(data);
    if(head==null) {
        head = newNode;
        return;
    }

    //ab hum list m traverse karenge to find the node whose next is pointing to null, that node will be last node and uske next ko hum apne naye node pe point kara denge aur naya node ek tarah se last m add ho jaega
    Node currNode = head;
    while(currNode.next!=null) { //hume tab tak traverse karna h jab tak hame last node yaani vo node jiska next null ke ppint kare na mil jae
       
        currNode = currNode.next; // agar currNode ka next null nahi h to currNode aage vaali node ho jaegi yaani jo currNode phle maani thi uske .next ko currNode kar denge
    }
    currNode.next = newNode;
     
}

    //printing the list
    public void printList(){
        if(head==null) {
            System.out.println("list is empty");
            return;
        }
        Node currNode = head;
        while(currNode!=null) { 
            System.out.print(currNode.data + "->");
           
            currNode = currNode.next; 
        }
        System.out.print("NULL");
        

    }
    //delete first

    public void deleteFirst() {
        if(head==null) {
            System.out.println("list is empty");
            return;
        }
        head = head.next;
        size--;
    }

    //delete last

    public void deleteLast() {
        if(head==null) {
            System.out.println("list is empty");
            return;
        }

        size--;  // ye sizze-- vaali line neeche vaali if statement se phle aayegi ..kyu ki agar upar vaali if statement ki condition satisfy nai hui aur hum vaha se neeche aaye to matlab ek node delete hogi aur size decrese by 1.
        
        if(head.next==null) {  //head.next = null means ek hi node hai list m.
            head = null;
            return;
         }

        Node secondLast = head;
        Node lastNode = head.next;
        while(lastNode.next!=null) {
            lastNode = lastNode.next;
            secondLast = secondLast.next;
        }
        secondLast.next = null;


    }

    public int getSize() {
        return size;
    }

    //reversing a linked list using both iterative and recursive methods
    // while reversing a list, no extra memory should be used ...space complexity must be same(   O(1)  ). tme complexity is O(n).
 
    public void reverseIterate() {  //iterative tareeke hum 3 variables ka iuse karte h..previous node, current node, and next node
        if(head ==null || head.next ==null) {
            return;
        }
        Node  prevNode = head;
        Node  currNode = head.next;
        while(currNode!= null) {
          

            


            Node  nextNode = currNode.next;
            currNode.next = prevNode;

            //update
            prevNode = currNode;
            currNode = nextNode;
        }
        head.next = null; //ye step jo initially head aur next vaali node connected thi use tor ke head ko null point kara raha h yaani jo initially head tha use list ka last node bana diya is step m
        head = prevNode;  // yaha jab humne while loop se bahar aaye yaani currNode is time null h that means prevNode last node hogi (as  currNode = prevNode.next ...yaani head humne last node ko bana diya ..that means list reverse ho gai
        
    }
        public Node reverseRecursion(Node head) { //function return karega har level pe head node..isi liye node return type

           if(head == null || head.next==null) {
            return head;
           }
            Node newHead= reverseRecursion(head.next);
           head.next.next = head;
           head.next = null;
           return newHead;

        }

    


    public static void main(String args[]) {
        LL list = new LL();

        //addFirst fun ko call
        list.addFirst("a"); //phle list m "a" store kiya fir addFrst use kar ke "is" ko "a" se phle store kiya..aur "is" point kar raha h "a" ko.
        list.addFirst("is"); 
        list.printList();
    list.addLast("List");
    list.printList();
    list.addFirst("this");
    list.printList();

    list.deleteFirst();
    System.out.println();
    list.printList();
    list.deleteLast();
    System.out.println();
    list.printList();

    System.out.println(list.getSize());
    list.reverseIterate();
    list.printList();
    list.head = list.reverseRecursion(list.head);
    list.printList();
        
    }
}