import java.util.Iterator; // Iterator ko implement karne ke liye package import karna parega
                            // .util.*; bhi kar sakte h

import java.util.HashSet;  // HashSet ko banane ke liye ye package import karna parega
                            //util.*; ye bhi import kar sakte h

public class HashSet1{  //Set is a data structure in which all elements are unique
                        // hashSet is important data structure because of its time complexity....kam time complexity lagti h different operations m
                        // for insert/add....search/contains.....and delete/remove...sab m O(1) rhti h time complexity

    public static void main(String[] args) {

        //creating
        HashSet<Integer> set =new HashSet<>(); // just like in the case of ArrayList..LinkedList..stack ..queue etc

        //insert
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1); // dobara 1 ko add karne pe bhi 1 add nai hoga as set ke andar unique elements store hote h bas 

        //search- search ke liye set m special function hota h called 'contains'

        if(set.contains(1)) {
            System.out.println("set contains 1"); // set.contains ne agar true return kiya 
        }
        if(!set.contains(6)) { // not operator laga h
            System.out.println("does not contain 6"); // set.contains ne agar false return kiya
        }

        //delete   jaise ArrayList m .remove function hota h vaise hi isme bhi hota h

        set.remove(1);
        if(!set.contains(1)) {
            System.out.println("1 deleted successfully");
        }

        //size-  like ArrayList  there exists a .size function ... size function set ka repeating element ko ignore kar deta h
        System.out.println(("size of set is: "+set.size()));

        //printing all elements of set
        System.out.println(set);

        //Iterator....set ke andar elements ka index nahi hota...to hum normally traverse  nahi kar sakte elements pe...uske liye iterator hota h uske liye ek package bhi import karna parta h
        
        Iterator it=set.iterator();

        //is iterator ke 2 special functions hote h..1)hasNext, 2)next

        //working of iterator functions..
        //for eg let our set has 1,2,3 as elements..[1,2,3]...and initially iterator it points at null...an it has a property called next which points at next element..
        // in set ..elements are stored in unordered formm..
        //so for understanding..let iterator it points at null..than its next points at 1 then 2 then 3...this 1,2,3 can be in any order as welll..
        //set blackbox ki tarah h..agar phle 1 add kiya h to zarooori nai ki phle 1 hi milega
        // now if we write function it.next..to ye phle return karega null ka next yani 1
        // dobara it.next kiya to 1 ka next yaani 2 return hoga ...aise hi agli aar 3 return hoga

        // hasNext jo function hota h vo true ya false return karta h..
        //agar iterator it ke pass next h to hasnext true return karega..varna false return karega


        // in do functions ko use karke hum set ke elements pe traverse karenge as shown below:

        while(it.hasNext()){ // yaani jab tak iterator it ke pass next h yaani aage koi element h..tab tak hum it.ext ko print kara denge

            System.out.println(it.next()); //it.next print karaya to iterator it apne aap update ho jaata h hume ++ ya -- karne ki need nai h
        }

        //hashSet unordered data structur h...koi gurantee nai h ki jis order m elements add kiye the traverse karne aur iterate karne pe usi order m elements milenge...order alag ho sakta h
    }
}