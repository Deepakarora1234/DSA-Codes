import java.util.ArrayList; // this package needs to be imported to use arrayList...we can also import java.util.*
import java.util.Collections; //this package is imported to use collections.sort function....we can alse use java.util.*
class ops{  //Arraylist: 1) memory is non continuous 2) size is variable  in arrays size if fixed and mempry continuous
            //we can only store objects in arraylist....in arrays we can store primitive data types(int, float etc) and objects
            //arraylist me memory heap ki form me hoti h
public static void main(String[] args) {
  //to define arraylist...ArrayList<type> name = new ArrayList<type>(); ....or..Arraylist<type> name = new ArrayList<>();
   ArrayList<Integer> list = new ArrayList<Integer>(); // we have Integer class,  String class, Boolean class..in classes ko use kar ke arraylist define hoti h..direct int type ki arraylist nahi bana sakte. 

      //add elements
      list.add(0);
      list.add(2);
      list.add(21);
      System.out.println(list);
      //get elements
      int element = list.get(0);  // means we want to get element stored at 0th index
      System.out.println(element);

      //to add element in between
       list.add(1,34); // means 1st index pe 34 add karna h..vaise khaali add function elements peeche add karta jaata h...for adding elementy in between we use this add function in this way
       System.out.println(list);

       //set element..we want to change an element at any index
       list.set(0, 4); // 0th index element is made to be 4
       System.out.println(list);

       //delete element
       list.remove(1); // 1st index element deleted
       System.out.println(list);

       //size
       int size = list.size();
       System.out.println(size);

       for(int i = 0; i <list.size(); i++) { //loops bhi laga sakte h arraylist pe
        System.out.println(list.get(i));
       }

       //sorting
       Collections.sort(list); //Collections ek class hoti h java m..collections framework ke jitne bhi data structures hote h unko collections.sort use kar ke sort kar sakte h
       System.out.println(list);     

       //max and min
       System.out.println(Collections.max(list));

       //to get index of any element
       System.out.println(list.indexOf(21));
       
       
  }
     
}


