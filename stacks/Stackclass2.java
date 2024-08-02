import java.util.*;
public class Stackclass2 { // we dont implement stack using linked list or array list..instead we use java collections framework
                            //we also write code for pushing an element at bottom of the stack and to reverse a stack using reccursion


     public static void pushAtBottom(int data, Stack<Integer>s) {

       if(s.isEmpty()) {
        s.push(data);
        return;
       }
       
        int top = s.pop(); // yaha se top elements remove karte gye taaki last tak aate aate empty stack mil jae aur bottom pe apna required element add kar de
        pushAtBottom(data, s);
        s.push(top);//is step se phle recursion implement hote hote hamara bottom pe jo add karna tha vo add o gya..ab is step m vapas se top elements add karte gye

     }    
     
     public static void reverse(Stack<Integer> s) {
        if(s.isEmpty()) {
        return;
    }

        int top = s.pop(); // top elements ko hatate gye
        reverse(s); // agar stack m 1,2,3 the elements bottom to top..to ye step perform hote hote hume emty stack de dega...
        pushAtBottom(top, s); // using previous function pushatbottom, apne previous top elements ko bottom pe push karte gye...isse stack reverse ho jaega
                                // yaani last m top element 1 tha ..vo pushAtBottom ke through bottom pe jaega..fir 2..fir 3...aur hume 1,2,3 top to bottom mil jaenge..yaani stack reverse
     }
    public static void main(String args[]) {
        Stack<Integer> s = new Stack<>();
        // push peek pop and isEmpty ye chaaro functions java collections framework m already defined h

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        pushAtBottom(5,s); // data aur stack s pass kar diye.


        reverse(s);
        while(!s.isEmpty()) { 
            System.out.println(s.peek());
            s.pop();
        }
 }
}
//reccusrsion has its own stack..thatbis created by memory and is called implicit stack. //stack that we create is called explicit stack
