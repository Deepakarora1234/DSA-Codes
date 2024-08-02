public class Trie { /*  Trie also called prefix tree , digital search tree, retrievel tree
                     1) bohot saare words agar store karane h to trie phle kuch common info nikal leta h aur make sure karta h ki common vaala part ek hi baar store ho usse search ki complexity bohot kam ho jaati hai
                     jab kisi tree m ek node ke 2 children hi hote h bas to use hum binary tree bolte h ..jab kitne bhi children ho sakte h ..lets say k children ..to us tree ko k-ary tree bolte h
                    trie is a k-ary tree
                    trie m search ki complexity turns out to be O(L) where L is the length of the word....this complexity is better than O(logn) and O(n).

                     2)root of trie is always an emplty node...root khaali children tak phonchaane ka kaam karti h

                    3)prefix is not repeated

                    formation of trie...we are given an array of strings..words[]={"the", "a","there", "their", "any"}
                                     empty root          ye empty root bani hoti h trie ke liye
                                      /  \                  ab phla word uthaya aur dekha kya vo trie m exist karta h..nai karta to letter by letter store karna shuru karenge
                                     t    a(eow)
                                    /      \
                                   h        n
                                  /          \
                                 e (eow)       y(eow)
                                / \
                               r   i
                              /     \
                             e(eow)  r(eow)                                jaha ek word khtam hua us letter pe 'eow' yaani end of word mark kar dete h..ab next word "a" uthaya ..a abhi trie m nai h to nai node bana ke store kar diya   
                                                                 next word there h..aur there m se the humare pass trie m aa chuka h ..to the dobara store nai karaenge..the ke 'e' tak jaak nai nodes m store kar denge 'r' aur 'e' ko and 'e' pe eow.
                                                                 next word their h..uska the h humare pass trie m..'i' aur 'r' nai h..to 'e' se nai node bana ke 'i' and 'r' store kar denge..aur ek eow 'r' pe bhi
                        next word any h..any ka 'a' h trie m...n aur 'y' ko a se nai node bana ke store kar denge aur ek eow 'y' pe
                     aise karke humne saare words store kara liye

                    code...binary tree ka code likhte time hum node class m data ke saath left aur right define karte the kyu ki ek node ke 2 hi chikdren ho sakte the..
                     yaha hum letter by letter store karte h to ek node ke child ke liye a to z kuch bhi ho sakta h..yaani 26 alphabets...aur ek uppercase aur special characters(%,^,&etc)include kiya to total 256 different child ho sakte  h...isi liye hum ek array define karte h required size ka

                     abhi samaghne ke liye Node[] children ; ye array 26 size ka le rhe h...har node yaani upar vaale ex m har node t vaali h vaali e vaali....saari node ke pass apna ye children naam ka 26 size ka array hoga
                    yaani agar kisi bhi node ke array m agar 0th index pe null nai h kuch store h...to matlb us node ka ek chil aisa h jisme "a" h...aise hi agar 1st index pe kuch valid address store h to yaani us node ka ek child aisa hoga jisme "b" store hoga and so on..

                     boolean endOfWorld...har ek node pe apna endOfworld..variable hota h..agr vo true h to yaani vo end of world h varna nai h

                     java m ye hota h--> 'a' -'a'=0...'b'-'a'=1.......'z'-'a'=25....yaani agar word h "the" to loop lagaenge i =0 se leke word.length tak aur har character ka Node[] children vaale array m konsa index hoga vo ith character m se 'a' ko minus kar ke mil jaega
                     a b c d e f g h......x y z
                     0 1 2 3 4 5 6 7......23 24 25  ... yaani agar word m ith character 'b' h to array m b ka index 'b'-'a'yaani 1 ho jaega 

                     IMPORTANT--> trie  me jab bohot saare nodes store karate h...to total no of nodes in a trie = the no. of unique prefixes of all the words..(ye property questions m kaam aayegi)

                     IMPORTANT-->..for a given string ..substrings are ALL PREFIX OF ALL SUFFIX...or..ALL SUFFIX OF ALL PREFIX...ye question m kaam aayega

                    code goes here
                    */

    static class Node {
        Node[] children;
        boolean eow;  //end of world

        public Node() { // jab shuruvaat m koi bhi node create hogi to uspe anpe chuildren ki koi bhi info nai h to bas hume children array ko initialize karna h
            children=new Node[26]; // 26 yaani a to z
            for(int i=0; i<26; i++) {
                children[i]=null;   // ye humne yaha ek class ke objects ka array banaya h to uske liye memory allocate karni paregi store karne ke liye...isi liye null ke saath initialoze kar diya

            }
            eow=false; // eow ko bhi false ke saath initialize kar diya

        }

    }
    static Node root =new Node(); // ye root node hamesha rahegi 

    /*insert  insert m trie ke andar O(L)ka time lagta h..l is the length of word...to Jo longest word ki length hogi worst case m vo lenge

     hum ek ek kar ke word ko uthaenge aur loop lagaenge 0 se word.length tak..aur ith ndex pe jo character hoga(word.charAt(i)) uska Node[] children vaale array m konsa index hoga vo aise nikalenge:-

     java m ye hota h--> 'a' -'a'=0...'b'-'a'=1.......'z'-'a'=25....yaani agar word h "the" to loop lagaenge i =0 se leke word.length tak aur har character ka Node[] children vaale array m konsa index hoga vo ith character m se 'a' ko minus kar ke mil jaega
     a b c d e f g h......x y z
     0 1 2 3 4 5 6 7......23 24 25  ... yaani agar word m ith character 'b' h to array m b ka index 'b'-'a'yaani 1 ho jaega
     aise index nikal ke dekh lenge ki next level vaali node ke children vaale array m is index pe agar null h to nai node bana ke store kar denge character ..varna aage bar jaenge
     har level pe root ko update kar denge ..root=root.children[index]
      */

     //insert code goes here

     public static void insert(String word) {
        Node curr=root; // hume har step m root ko update karna h lkn humne static root banai thi jo hum change nai kar sakte..isi liye curr node bana ke update karenge ..root hamari initial vaali hi rahegi
        for(int i=0; i<word.length(); i++) { //O(L)
            int idx= word.charAt(i)-'a';  // upar discuss kiya h ye step..yaha se ith character ka root aale children array m index nikal jaega

            if(curr.children[idx]==null) { // to is case m nai node bana ke character ko store kar denge

                // aur nai node isi children array ke isi index pe add hogi
                curr.children[idx]=new Node();
            }
            if(i==word.length()-1) {
                curr.children[idx].eow=true;
            }
            //aur agar null nai h to aage bar jaenge...dono case ke end m root ko update karna h

            curr=curr.children[idx]; // root ko update kar diya

        }

     }

     /*search in a trie    trie ke andar search ki time complexity O(L) lagti h L is length of the key jiske liye search kar rhe h
     ab above trie m their ko search karna h hume...to vahi similar sa process h..loop lagaenge 0 se word.length tak..aur ek ek character ke liye dekhenge..
      phle 't' ke liye dekhenge ki root ke children vaale array m t kisi index pe h..agar h to root ko root.children[idx] kar denge fir iskeliye 'h' dekhenge and so on
     important condition ye h ek ki word jo dhoond rahe h uska last character jab mila to uspe eow hona chHIYE VARNA VO  maana nai jaega..
      for eg agar "an" ke loye search kar rhe h .. to above trie m any ke vagah se an mil to jaega lkn vo mmaana nai jaega as an ke 'n' pe eow nai h

      search code goes here:-
      */

     public static boolean search(String key) {
        Node curr=root;
        for(int i=0; i<key.length(); i++) {
            int idx = key.charAt(i)-'a';
            if(curr.children[idx]==null) {
                return false;
            }
            if(i==key.length()-1&& curr.children[idx].eow==false) {
                return false;

            }
            curr=curr.children[idx];
        }
        return true;
     }
      
     //questions
     /*question 1...Word break problem

     problem statement-->given an input string and a dictionary of wordds find out if the input string can be broken into a space-seperated sequence of dictionary words

     words[]={i, like,sam,samsung,mobile,ice}

     key="ilikesamsung"

     output true

     explanation: hume key de rakhi h koi ...to ye check karna h agar ki us key ko hum break karna shuru kare ..to break kar ke saare words jo mile h vo agar words naam ke array m h to true otherwise false
     yaani agar given key m i like samsung  ..aise break kiya to true hoga kyu ki saare words mil gye array m
     aur agar aise break kiya ..ilik esamsung .. to false.....yaani agar koi sa bhi aisa combination mil gya jisme saare words h array m to key ke liye true return karna h

     approach..yaha recursive approach follow karenge...phle guven key ke liye trie form kar lenge..
     ab key h ilikesamsung...to phle first i ke liye check karenge...i trie m mil gya with eow true...to i pe cut laga ke bacha hua likesamsung ko recursively check karenge....
     yaani i ke baad l check hoga..aur l trie m with eow true nai h...to l pe cut nai lagega aur li ko check karenge..
     li bhi eow true ke saath nai h...to lik ke liye check karenge..vo bhi nai h...to like ke liye check karenge
     like eow true ke saath mil gya trie m..to likeke baaad cut laga denge aur bache hue samsung ke liye recursive call
     samsung ke liye jab check karenge to break sam ke baad lagaenge ..lkn fir bacha hua sung array m nai h ..to sam ke liye true aur sung ke liye false yaani ye sam vaala break galat h..
     to aur aage check karenge sams ke liye fir samsu ke liye ..aise karte karte samsung poora aajega uske baad break.... to i like samsung mil jaega aur output true

     code goes here:-
     */

     public static boolean wordBreak(String key) {
        if(key.length()==0) {
            return true;
        }
        for(int i=1; i<=key.length(); i++) {                                                 //for loop hume bataega kaha kaha cut lagaane h...phle i =1 h..to ilikesamsung ka firstPart hoga 'i'
            String firstPart=key.substring(0,i);                                 //   ye i ko hum search function use kar ke dekhenge agar true hua to iske saath bacha hua likesamsung check kar lenge recursively...
            if(search(firstPart) && wordBreak(key.substring(i))) {                           // fir likesamsung ke liye recursive call m ek aur for loop se sab se phle firstPart m 'l' aajaega..
                return true;                                                                 // 'l' ke liye search function false dega to if vaali condition satisfy nai hui aur loop age chalega aur firstPart ho jaega 'li'
            }                                                                                // fir aise hi 'li', 'lik' dono ke false hoga aur jab firstPart like hoga hunme true mil jaega search se to hum recursively agle part yaani samsung ke liye call laga denge
        }
       return false;

     }

     /*
      * Question 2--> Count Unique Substrings
      Statement--> given a string of length n of lowercase alphabet characters,  we need to count total number of distinct substrings of this string
      str="ababa"
      ans=10;
      is question m kisi given string ke liye empty string "" bhi ek unique substring ki tarah count hota h 

      approach:-

      aise ques ke liye we need to know about prefix-suffiix concept
      apple agar ek word h ..to prefix ho gye a,ap,app,appl,apple  suffix ho gye apple,pple,ple,le,e
      prefix suffix dono aasani se for loop laga ke nikal sakte h
      upar ek baat likhi h ki for a given string..substrings are all prefix of all suffix or all suffix of all prefix
      yaani agar apple h string..to iske har suffix yaani apple pple ple le e...inke seperate seperate saare prefix nikal le agar..yaani e ke prefix, le ke , ple ke, pple ke, apple ke..to hume apple ke saari possible substrings mil jaengi
      is tareeke m lkn preffix repeat honge..to hume saari substrings mil jaengi lkn unique chahiey hume ..yaani ek hi baar .. to humne suffixes ke jo jo prefix nikale h..usme se unique prefix ke no.count kar lenge..vo hume uniq substrings ka number de dega

      trie data structure hume pata h unique prefix store karta h..yaani usme prefix repeat nai hote ek prefix ek hi baar aata h..
      aur upar ek baat aur likhi thi ki trie ban ne ke baad no. of nodes equals total no.of unique preffixes...iska use karenge hum

      to agar hum apne suffixes ko trie m daalte jae ..aur trie form hone ke baad apne trie ki nodes count kar le to hume unique prefix ka count mil jaega..aur ye preffixes string ke suffixes ke unique vaale h..yaani hume unique substrings ka count mil jaega

      ab trie m nodes count karne ke liye bhi thora alag tareeka h
      binary tree ke liye to hume pata h ki har node ke 2 children ho sakte h..to hum left subtree ke liye aur right subtree ke liye recursion use kar ke nodes count kar lete the fir +1 kar dete the root node ka count include karne ke lye

      yaha phle hum root node ke children vaale array ke liye 0 se 25 tak loop lagaenge
      agar ith index pe null nai h.. to count increase kar denge,,fir usi time us ith index ke liye us node ko root bana ke recursively count vaala function call kar denge
      ye count vaala function fir us ith node ko root maan ke us ke children array m loop lagaega..
      aie karte karte last m return kar ke vapas oroginal root vaale array ke liye loop jo chal rha tha vaha aajenge.. fir usme i update hoga fir aise poore array m check kar lenge

      last m root ke liye +1aur is trah saari nodes ka count mil jaega using recusrsion
      code goes here:-

    */

    /*
     * function likhne se phle kisi given string ke liye uske saare suffiix nikal ke unko trie m daalne ka code check kar lete h
     * lets say given string is str="ababa";
     * for(int i=0; i<str.length(); i++) {
     * insert(str.substring(i));     str.substring() function m agar ek index do to yaani vo starting index h aur end index last tak maan leta h;
     * }
     *  
     */
    //code for ques 2:-      saare suffix ko main function m trie m daal diya ..ab khaali us trie ki nodes count karni h to get the no. of unique prefixes of all suffixes...aur vo equal hoga unique substrings ke

    public static int countNodes(Node root) {
        if(root==null) {
            return 0;
        }
        int count=0;
        for(int i=0; i<26; i++) {
            if(root.children[i]!=null) {
                
                count+=countNodes(root.children[i]);
            }
        }
        return count+1;

    }

    /*
     * question 3-->  Longest word with all prefixes
     * Statement--> words[]= a,banana,app,appl,ap,apply,apple} ....
     * find the longest string in words such that every prefix of it is also in words.
     * ans=apple     is example m apple aur apply dono ho sakte h ..aise case m ans jo lexicographically(alphabetically) smaller hoti h vo hota answer....yaani apple ho jaega ans
     * 
     * trie hume pata h unique prefixes store karta h to hum trie se solve karenge 
     * 
     * words array ka agar hum trie banaenge to kuch aisa banega
     *                                        root
     *                                      /      \
     *                                     a(eow)    b
     *                                    p(eow)      a
     *                                   p(eow)        n
     *                                  l(eow)          a
     *                                 /     \           n
     *                                e(eow)  y(eow)      a 
     * 
     * hume pata h answer apple ya apply m se h....to ej cheez notice kare to hume trie ko vo word chchaiye jaha har node pe eow true ho
     * 
     * to hume aisa word jiske saare prefixes words m ho uske liye ais aword trie m dhoondna h jiske har node pe eow true h....aur ques m longest poocha h to longest vaala dhoondenge
     * 
     * code goes here:-
     */

     static String ans="";
     public static void longestWord(Node root, StringBuilder temp) {
        if(root==null) {
            return;
        }
        for(int i=0; i<26; i++) {
            if(root.children[i]!=null && root.children[i].eow==true) {
                temp.append((char)(i+'a'));  // ith idnex ko type cast kar diya uske corresponding character m aur string builder m store kar diya
                if(temp.length()>ans.length()) {
                    ans=temp.toString();
                }
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length()-1);
            }
        }
     }










            


    public static void main(String args[]) {

        /*for question 1
         String words[]={"i","like", "sam", "samsung","mobile","ice"};
         for(int i=0; i<words.length; i++) {
             insert(words[i]);
         }
         String key="ilikesamsung";
         System.out.println(wordBreak(key));
         */

        
        //   for question 2

         /*  String str="ababa";

          for(int i=0; i<str.length(); i++) {
            insert(str.substring(i));
          }
          System.out.println(countNodes(root));
          */

          //for last ques
          String words[]={"a","banana","app","appl","ap","apply","apple"};
          for(int i=0; i<words.length; i++) {
            insert(words[i]);
          }
          longestWord(root,new StringBuilder(""));
          System.out.println(ans);


        


        
    }
}