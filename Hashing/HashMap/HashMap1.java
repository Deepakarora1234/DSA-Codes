import java.util.*; // HashMap ko implement karne ke liye package hote h util.HashMap;...humne lkn .star use kiya h

public class HashMap1 { /*HashMap do tarah ki information ko store karta h..ek hoti h key aur us key ke corresponding value
                         2 value ka pair (key and value) jaha bhi store karna h vaha HashMap ka use karte h
                        key is always unique ..value same ho sakti h do keys ki 
                        */
    public static void main(String[] args) {
        /*creation  .. example..country(key), population(value)

        HashMap<key_type, value_type> name_of_map = new HashMap<>();
        */

       HashMap<String,Integer> map=new HashMap<>();

       //insertion ... insertion ke liye map.put function use karte h

       map.put("India",120);
       map.put("china",150);
       map.put("usa",30);

       //printing the map
       System.out.println(map);   //jis order m insert kiye h elements jaroori nai print ke time usi order m print ho

       /*that means HashMaps are unordered maps

       dobara kisi key ke liye value insert ki to print karane pe us key ke corresponding uski nai value udpate ho jaegii
       */

       map.put("china",180);
       System.out.println(map);

       /*search/lookup //maps m search ke 2 operations hote h...ek to containsKey....aur ek get
       containsKey true return karta h agar key present hoti otherwise false return karta h
       get d=function kisi key ki corresponding value deta h ..agar vo key present nai h map m to null return karta h;
       */

       if(map.containsKey("India")) {
        System.out.println("key is present");
       }
       else {
        System.out.println("not present");
       }

       System.out.println(map.get("china")); // china ki corresponding value aajegii
       System.out.println(map.get("spain")); // null ajega

       //iteration....iterate karne se phle ek naye type ka for loop seekhna parega
        int[] arr={1,2,3};
        //for loop ka ek to standard tareeka h:
        for(int i=0; i<3; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();

        //2nd type of for loop:
        for(int value : arr) {  // yaani for(int any_name_example_value : array_name) ...ye vaala for loop collection ke alag alag index ka use na karke direct value pe jaata h
            System.out.print(value+" ");
        }
        System.out.println();

        /*is naye vaale for loop ko use kar ke hum map pe iterate kareng
        HashMap ke andar for loop ka syntax kuxh aisa hota h:

         for(Map.Entry<key_type, value_type> e:Map.entrySet() )   .. e for element...kuch aur bhi le sakyte h naam
         hamare HashMap ka naam map tha ..to map.entrySet hamare for loop ke liye collection ban jaega 
         */
        
        for(Map.Entry<String, Integer> e: map.entrySet()) { // ab is e m poore ka poora pair aayega har baar
            System.out.println(e.getKey());// isse key print hogi
            System.out.println(e.getValue()); //  isse value  print hogi

        }
        // map.entrSet se map ki saari entries (key and value both)  ka set bn jaata h

        //ek function hota h keySet jisse khaali keys ka set banta h
        Set<String> keys = map.keySet();
        // ab is set pe naya vaala for loop lagaenge
        for(String k:keys) {
            System.out.println(k+" "+map.get(k)); // to har cycle m k m key hogi and get function se us key ki value aajaegi 
        } 

        //remove a pair    .remove function m jo key remove karni h vo daal do
        map.remove("china");  //to\china vaala pair remove ho jaegaa
        System.out.println(map);  //china vaala pair is deleted

        //size
        System.out.println(map.size());





        
    }
}