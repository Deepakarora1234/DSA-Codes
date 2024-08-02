import java.util.*;
public class HashMap2 { /*yaha HashMap ka implementation kaise hota h vo seekhenge
                        HashMap is implemented in java with the help of an array of linked list..(yaani array ke har index pe ek linked list h)
                        kuch variables dekhte h...n-no. of nodes ..ye equal hoga total no.\of keys ke equal
                        N-ye h array of linked list vaale array ka size...is array ke har ek index ko conventionally bucket khte h;
                        */

                        /*hashing ka literal meaning hota h data ki form ko change kar dena...
                        for eg...'abc' agar data diya h to hashing karke usko 3586 m convert kar diy..
                         java m hashing ke liye bohot saare functions in built hote h ..hume unki detail aur algorithm pe nai jaana 
                        ye data ki form change karne vaala concept password banane time use hota h..kisi bhi cheez ka password aise us data ki form change kar ke data base m store hota h...taaki agar vo data base hack bhi ho jaye..to hacker ko changed form vaala data mile khaali .orginal data na mile
                        hume khaali hashing ki implementation pe jaana h to hum maan lenge ki aisa koi algorithm h jo hume data ki form change kar ke de dega
                        */
                        
                        /* ab 'put' function ke implementation ki baat karte h O(1) m..
                        put function phle ye dekhta h ki key exist to nai karti..agar exist karti h to uski value update ho jaegi..otherwise ek nai node banke vo key aur value map m store ho jaegii..
                        ek tareeka to ye h ki har index ki har linked list ke saare nodes pe jaa jaa ke check kare ki key exist karti h  ki nai..lkn usme O(n) lag jaega..yaani ye galat h;
                         isi liye hum in built hashing function ka use kar ke hamari key ke liye bucket index pata kar lenge 
                         jab hume bucket index pata h to us index pe jo linked list h uskki nodes m search karna h hume apni key ko...mil gai to value update kar denge..otherwiise nai node bana ke strore kar denge..
                        ab hume linked list ki nodes pe search karna h to hume time lagega O(size_of_linkedlist)..
                        aur ye jo hashing function hota h ye data ko aise store karta h ki N BUCKETS pe n nodes uniformly store ho...n/N =lambda
                        ye lambda hamesha <= threshold (k)....jaise jaise n barta h fir ye in built function buckets increase kar deta h..this is called rehashing..
                        to put ke liye hume O(lambda) time lagta h which is a constant time complexity whih is roughly equal to O(1) time complexity...
                        jab n barta h..to n/N barta h yaani lambda barta h..aur ye lambda bar ke jaise hi threshold (k) se aage nikla to hume rehashing karni paregii..
                        rehashing ke liye hum buckets increase kar dete h ...yani previously jo array liya tha usse jaada size ka naya array leke usme saari nodes daal dete h so that lambda is less than k and put function ki time complexity vapas optimise h jae by decreasing lambda
                        */

                        /*IMPORTANT NOTE- average cases m put ki time complexity O(lambda) aati h which is constant time complexity..aur hashmap ke andar usually hum average case ki hi bat karte h to hum kh dete h put ki time complexity constant h ..but
                         WORST case(jo bohot rare hota h) m put ki time complexity O(n) aati h..ye tab hota h jab humara hashFunction(neeche banaya h) vo itna optimise na ho aur har key ke liye hamara hashFunction(blackbox) hume ek hi bucket index deta rhe...to saari nodes ek hi bucket index ki linkedlist m aajaengi aur uska size lambda ki jagah n ho jaega..to is worst case m time O(n) ho jaega..
                        kabhi kabhi data hi aisa hota h ki hume har key ke liye bucketindex ek hi milta rhe tab bhi O(n)..aur kuch kuch case m hume rehashing bhi bohot baar karni parti h...tab bhi worst case time complexity lagti h..
                        that means worst case m hashmap ki time complexity O(n)..lkn most of the cases(yaani average cases m) constant time complexity hotu h O(lambda) which is roughly equal to O(1);
                        */
                        
                        //ab phle put ke liye code likhenge
      static class HashMap<K,V> { //ye K and V key aur value h..inke hume types nai pata isi liye angular brackets m khaali K and V likh diya...isko java m generics bolte h

        private class Node{
            K key;
            V value; // ye phli baaar dekha h aise define karna variables ko
            public Node(K key, V value) { // Node class ka constructor
                this.key=key;
                this.value=value;
            }
        }

        private int n; //n-no.of Nodes
        private int N; //N-no. of buckets
        private LinkedList<Node> buckets[]; //buckets naam ka array of linkedList banaya that means ..N=buckets.length();

        @SuppressWarnings("unchecked") // java ke kuch kuch versions m warnings show hoti h ..error nai hoata warnings aati h agar linkedlis ko bina type ke define kiya to..us warning ko hatyane ke liye ye line likhi h

        public HashMap() {
            this.N=4;
            this.buckets=new LinkedList[4]; //upar humne apne linked list vaale array ko initialize kiya tha khaali..yaha uska size define kar diya 
            //ab har ek index pe jaake hume ek khaali linkedlist bnani h;
            for(int i=0; i<N; i++) {
                this.buckets[i]=new LinkedList<>(); // hume aisa array chahiye tha jiske har index pe linked list h..to humne loop chala ke har index pe ek khaali linked list create kar di...jab tak ye khaali liked list nai hogi kisi index pe us index pe hum apna data store nai kar paenge fir


            }

        }
        private int hashFunction(K key) { // hashFunction hamara black box h jo hume bucket index deta h kisi bhi key ka ..lets see kaise
            int bi = hashCode(); // java ke andar ek pre defined function hota h hashCode naam ka jo hume int value deta h..ye value humare node ki bucket index hi hoti h...yaani kisi bhi ek hashmap ke liye har specif key ke liye ye ek specific bi hi dega key exist kare ye na kare us map ke andar...aur har alag alag maps le liye ye bi alag ho sakta h
            //hashCode koi si bhi value return kar sakta h..negative ya positive..to agar negative hui to hume positive banana parega as our index can not be negative;
           return Math.abs(bi)%N; // bi 0 se N-1 tak hi ho sakta h aur hashCode to koi si bhi value de sakta h...to agar us positive value ka hum N se divide kar ke remainder le le to hume 0se N-1 tak hi value milegi;
            //kisibhi no.ko agar N se divide kar rhe h to remainder hamesha [0,N-1] m liekarta h ..for eg kisi no. ko 3 se divide kiya to remainder can be only 0,1,or 2  // is property ka use kiya h upar vaale step m

        }

        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi]; // bi pe jo linked list h usko ll naam ki linkedLst m store kara liya;
            for(int i=0; i< ll.size(); i++) {
                if(ll.get(i).key == key) {
                    return i; // ye i hamara data index 'di' h
                }
            }
            return -1; //yaani agar linked list m key nai mili to data index -1 hoga


        }

        private void rehash() {
            LinkedList<Node> oldBucket[] = buckets; // buckets naam ka originally jo linkedList ka array tha use oldBuckets naam ke linkedlist ke array m store kara diya
            buckets = new LinkedList[N*2]; // aur ab original array ka size previous size se double ka update lar diya

            for(int i=0; i<N*2; i++) {
                buckets[i] = new LinkedList<>(); // ab naye array ke har index pe jaake ek ek empty linkedList create kar di;
            }

            for(int i=0; i<oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i]; // oldBucket naam ke array ke har index pe linked list h usko loop ki har cycle m ll m store kara rhe h
               
                for(int j=0; j <ll.size(); j++) { // is loop se ith index pe jo linkedList h us list pe traverse kar rhe h..har node pe jaake 'node' naam ki node m store kar rhe h jth index vaali node...fir us node ko put function use kar ke buckets(extended version) m put kar rhe h
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }

            }
        }

        public void put(K key, V value) {
            int bi=hashFunction(key); //ye hashFunction vo h jisse hume kisi key ke liye bucket index mil jaata h;
            int di = searchInLL(key, bi); //dataIndex; // ye bucket index bi pe jo linked list h usme search karega key ko aur hume us key ke liye data index return kar ke dega // data index yaani given bucket index ki linked list me konsi node pe hmari key mili hume;
            // agar dataIndex di ki value is >0 yaani valid value..that means key exists..if it is -1..that means key does not exist;
            if(di==-1) { //key does not exist
                //yaani yaha hume nai node bana ke key add karni h usme;
                buckets[bi].add(new Node(key, value));  // is step m bucketIndex bi jo h array ka vahha vaali linkedList m ek nai node banai jiski key aur value humne put function ke arguments m di thi
                n++; // kyu ki nai node banai h to size bar jaega aur n++ ho jaega;
            }
            else {  // key exists;
                Node node = buckets[bi].get(di); // data index pe jo node h usko node naam ki node m store kara liya
                node.value = value; // us node naam ki node ki value m put function ki argument vaali value daal di;

            }
            double lambda = (double)n/N;
            if(lambda>2.0) { // rehashing....2.0 hamara k(threshold) hai
                rehash();
            }

        }
        //get - get function ke liye bhi same cheez karni h..get function agar key exist karti h to uski value deta h agar nai karti to null deta h
        //agar china ke liye get kiya to phle china ke liye bucketIndex nikal lenge fir us 'bi' pe jo linkedList h usme search kar lenge china ke liye

        public V get(K key) { // V h return type kyu ki get function key ki value return karta h aur value V type ki h

            int bi=hashFunction(key);  // ye kaam put function  vala hi kiya h phle bucket and data index nikal liye
            int di = searchInLL(key, bi);
            if(di==-1) { 
                return null; // data index -1 yaani bucketindex vaali linked list m vo key nai thi t null return kar diya
            }
            else {   //yaani vo key exist karti h data index 'di' pe 
                Node node = buckets[bi].get(di); // us 'di' pe jo node h usko node naam ki node m store kara ke uski value return kara di
                return node.value; 

            } 
        }
        
        //same tareeke se containsKey function bhi bana sakte h  hum

        public boolean containsKey(K key) {
            int bi=hashFunction(key);
            int di = searchInLL(key, bi); //pichli baar ki tarah 'bi'and 'di'nikal liye..'di'agar -1 yaani linkedlist m key exist nai karti and we return false ..else we return true
            if(di==-1) { 
                return false;
            }
            else {
                return true;
            }

        }

        //remove..remove function ke liyebhi phle kisi key ke liye 'bi'and 'di' nikal lenge..fir agar 'di' -1 hua yaani 'bi' vaali linked list m vo key exist nai karti aur hum null return kara denge
        //aur agar 'di' valid value hui to us 'di' pe jaake linkedlist.remove(di) ko use kar ke vo key remove kar enge aur saath ke saath jo key remove ki h uski value return kara denge
        
        public V remove(K key) { // return type V h kyu ki value return karani h us key ki jo remove ki h aur value V type ki h
            int bi=hashFunction(key);
            int di = searchInLL(key, bi); // har baar ki tarah 'bi'and 'di' nikal liye 
            if(di==-1) { //exist nai karti key isis liye null return kaara diya
                return null;
            }
            else { // exist karti h to 'di' index pe karti h..'di'index vaali node ko remove kiya aur saath ke saath node naam ki node m store kara ke uski value return kara di
                Node node =buckets[bi].remove(di);
                n--; // node delete ki h to size ko -- karn parega
                
                return node.value;
            }

        }

        //isEmpty ... hashmap tab khaali hoga jab uske andar ek bhi pair store nai h yaani ek bhi node nai h yaani n=0;
        public boolean isEmpty(){
            if(n==0) {
                return true;
            }
            else{
                return false;
            }
        }

        //keySet....ye fnction ek ArrayList m saari ki saari keys ko daal ke return kar ke dega
        public ArrayList<K> keySet() {  //keys ki arraylist h isiliye ArrayList<K> h return type
            ArrayList<K> keys = new ArrayList<>(); //ek arraylist create ki K type ki
            
            for(int i=0; i<buckets.length; i++) { //ye outer loop saare bucketindex pe traverse karega
                LinkedList<Node> ll=buckets[i]; // har cycle pe ith index pe jo lnked list h usko ll m store kara liye..ab inner loop is ll ke saare nodes pe traverse karega aur ek ek kar ke saari nodes ki keys ko array list m store karata jeaga
                for(int j=0; j<ll.size(); j++) {
                    Node node = ll.get(j);
                    keys.add(node.key);

                }

            }
            return keys;


        }


      }                  


                        
    public static void main(String args[]) {
        HashMap<String, Integer> map = new HashMap<>(); //HashMap class ka object bana liya aur key ka typestring aur value ka type 
        //aur ye hum java ka inbuilt hashmap use ni kar rhe ..hum use kar rhe h HashMap nam ki jo humne class banai h usko
        map.put("India", 120);
        map.put("china", 150);
        map.put("us", 30);
        ArrayList<String> KEYS=map.keySet(); // map.keySet arraylist return karta h usko KEYS m store kara liya
        for(int i=0;i<KEYS.size();i++){
            System.out.println(KEYS.get(i)+" "+map.get(KEYS.get(i))); // KEYS.get(i) se ith index vaali key milegi ajr map.get(KEYS.get(i)) jo h usme get function ke liye parameter m ith index vaali key di h aur get function us index  ki key ki value dega
        }
    }
}