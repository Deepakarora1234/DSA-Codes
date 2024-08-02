import java.util.*;

public class Stackclass { //3 major operations in stack data structure..1) push O(1) 2) pop O(1) 3) peek O(1)
    // stack data structure also called last in first out data structure(LIFO)
    //stack can be implemented using array,  arraylist and linked list
   

//following is stacks implementation using arraylist

static class Stack { // static class banai taaki directly main function ke andr use kar paaye
    static ArrayList<Integer> list = new ArrayList<>(); // arraylist define ki ek static type ..static yaani har jahgah isi list ki baat ho ri

    //upar ki tarah isEmpty function likhenge
    public static boolean isEmpty() {
        return list.size()==0; // yaani agar size zero to return karega true
    }
    //push
    public static void push(int data) {
        list.add(data);
    }

        //pop
        public static int pop() {
            if(isEmpty()) { // if list empty
                return -1;
            }
            int top = list.get(list.size() - 1); //yaha se last element mil gya
            list.remove(list.size() - 1);//yaha last element delete ho ggya
            return top;
        
    }
    //peek
    public static int peek() {
        if(isEmpty()) { // if list empty
            return -1;
        }
        return list.get(list.size() - 1); //last index vaali value return kar di


    }
}
public static void main(String args[]) {
Stack s = new Stack();
s.push(1);
s.push(2);
s.push(3);
s.push(4);

while(!s.isEmpty() ) {
System.out.println(s.peek()); // top element print kar diya 
s.pop(); // top element delete kar diuya taaki next elemet print ho paaye..tab tak print karaenge jab tak stack empty nahi ho jaata


}

}
}