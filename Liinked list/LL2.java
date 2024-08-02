import java.util.*;  // .* ki jagah .LinkedList BHI USE KAR SAKTE  hai 

class ll2 {  // to implement linked list usuing java collections framework;
    public static void main(String args[]) {

        /*to make LinkedList..format:
        LinkedList<type> name = new LinkedList<type>();
        */

        LinkedList<String> list = new LinkedList<String>(); // empty list ban ke  tayyar ho gai h..ab bas isme elements add karne h

        //adding elements to the list
        list.addFirst("a");
        list.addFirst("is");
        System.out.println(list);
        list.addFirst("this");
        list.addLast("list");
        System.out.println(list);

        list.add("dshjv"); // ye specify nahii kiya ki add first h ya last...to by default naye elements list m last , add hpte h.
       
       //size of list
        System.out.println(list.size());

        //printing the list
        for(int i = 0; i <list.size(); i++) {
            System.out.println(list.get(i) + " "); // .get kar ke fun hota h to get element at any index.  just as in array list
        }

    //deletefirst
    list.removeFirst();
    System.out.println(list);

    //delete last

    list.removeLast();
    System.out.println(list);

    //kisi random index ke elemet ko delete karna ho to

    list.remove(2);// 2nd index ke element ko remove karna h
    System.out.println(list);

    //reversing a linkedlost using collections frameweork
    Collections.reverse(list);
    System.out.println(list);

}
}