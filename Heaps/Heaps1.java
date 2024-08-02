import java.util.*;

public class Heaps1
{
    /*heaps are binary trees ...we implement these tres using array or arraylist and using this we get priority queues
     * Heaps are of 2 tpes   maxHeaps and min heaps  .. max heaps when implemented in the form of priority queue means max element has the highest priority and min heaps ke case m minimum element has the highest priority
     * heaps have 3 properties..
     * 1) they are binary trees .. that is each node has at most 2 children
     * 
     * 2) binary tree should be complete binary tree (CBT)..in CBT ..each level of tree  is complete except possibly  for the last level...yaani last level complete ho bhi sakta h aur nai bhi ho sakta..but last se phle vaale saare complete hone chahiye
     * and last level must be filled from left to right...
     * any violations to above 2 rules means that tree is not CBT
     * 
     * 3) heap order property
     * if children >= parent --> min heap
     * if children <= parent --> max hep....means if parent bara h children se to max heaps
     * 
     * Note:- why is heap implemented as array or arraylist and not using a node class such as we do in binary trees???
     * because of tiime complexity.. if we use a node class than for insert operation we create a node and then have to traverse the tree to get the empty postion ..then we insert our node..it will take O(n) (n- number of nodes) time in worst case where we might have to go to each level and each node of tree to get the empty position
     * uske bad abhi aur time lagega as node insert karne ke baad hamare heap ki property bigar gyi hogi max heap ya min heap vaali to hume poora heap fix karna h baar baar naye node ko apne parent node se compare kar ke usko uski shi jagah leke jaane ke liye 
     * 
     * if we use arraylist ,..to we can insert node in the end simply by using list.add()and then compare the node with its parent simply by using the relation that in a 0 indexed array list used for heap if
     * there is a node at ith index..then left child of this node will lie on 2*i + 1th index and right child of this node will lie on 2*i+2th index..to hume koi node di hogi kisi index pe to ye relation use karke hum uska parent access kar sakte h aur fir compare kar ke swapping kara sakte h jab tak inserted node apni shi jagah nai pohonch jaegoi,..
     * to aisa karle log(n) m implement ho jaega insert function that is why we use arraylist for heaps
     *  
     */
    static class heap{
        ArrayList<Integer> list = new ArrayList<>();
        
        //add
        public void add(int data) // logn
        {
            //first step is to add in the end of list
            list.add(data);
            int x = list.size()-1;  // ye hamara vo index h jispe ye nai node jo insert karni thi vo aai h ..yaani child index..ab is child ka parent ke liye (x-1)/2 kar denge..ye relation humne upar samagh liya h ki kaise aayega as ith index ki node ke liye left child is on 2*i+1 th index and right child is on 2*1+2th index....lkn agar child se parent nikalna h to left ya right vaala koi sa bhi relation use kar sakte h reverse m..parent ki value same nikal ke aayegi
            int par = (x-1)/2; // parent index
            //ab while loop laga ke hepa ko fix kar lenge ..hume min heap banana h to jab tak parent ki value choti nai ho jaati child se tab tak swap karte rhenge

            while(list.get(x) < list.get(par))   //ye loop logn times chalega ...isisliye is function ki time complexity O(logn) hoti hai kyu ki hum baar kisi node se uske parent vaali node tak jaaye jaa rhe h  aur har parent ek eke level upar h  to kisi node se max upar kitne level jaa sakte h uspe depend karta h  aur humne para h ki agar n nodes h to max logn  levels hote h 
            {   //swap
                int temp = list.get(x);
                list.set(x,list.get(par));
                list.set(par, temp);

                //update x and par
                x= par;
                par = (x-1)/2;

            }

        }

        //delete in heap
        /*
         * heap m delete ka matlab vo element delete hota h jo hum peek karte h yaani agar min heap h to minimum element ho jaega vo 
         * agar heap diya h koi aisa...
         *      2
         *     /  \
         *    3    4
         *   / \   /
         *  5   10 6        to iski  arraylist representation hogi ye  2 3 4 5 10 6
         * 
         * ab jab hume is m delete karna h yaani 2 ko delete karna h to kuch specific steps m karte h 
         * sab se phle 0th index aur last index vaalo ko swap kara dete h .. to ye ho jaega 6 3 4 5 10 2..
         * ab arraylist ka list.remove function jo hota h vo contstant time m remove kar dega.. to list.remove(list.size()-1) kar ke 2 hamara delete ho gya..
         * 
         * lkn ab heap bigar gya as min heap tha lkn 6 parent ban gya 3 and 4 ..
         * to hum ek heapify function likhenge khud se ..iska logic same rahega har baar aur iski help se heap ko theek karenge hum
         * 
         * heapify:-  heapify ke liye hum har index pe jaake uska left and right child nikalenge hamre relation se 2*i+1 and 2*i+2 ..
         * fir in dono m compare karenge ki min konsa h ..aur jo bhi min h left and right m ..uski swapping kara denge parent se agar parent jaada hoga ..to isse heap theek ho jaega..
         * aur ye recursively poore heap ke liye call katenge  heapify function ko 
         * 
         * 
         * 
         */
        public int  peek()
        {
            return list.get(0);
        }
        //heapify private function babaenge jise bahar ka use nai kar paega koi lkn heap class ke functions use kar paenge
        private void heapify(int i)
        {
            int left = 2*i+1;
            int right = 2*i + 2;
            int minIdx = i;  // minIdx abhi ke liye i vaala hi le liya h jise heapify karna h ..ab phle check karenge ki is index ke liye left exist karta h kahi leaf node to nai h..aur agar karta h to dekh lenge ki minIdx aur left m se konse pe min value h to min ko uska dx bana denge..vahi cheez right ke liye..usse hume vo idx mil jaega i left and right m se jispe value min h..aur agar ye i hi aaya to kuch nai karenge yaani heap fix h already aur agar i nai aaya to swap kar denge i ko minIdx vaale element se 

            if(left < list.size() && list.get(left) < list.get(minIdx) )
            {
                minIdx = left;
            }
              if(right < list.size() && list.get(right) < list.get(minIdx) )
            {
                minIdx = right;
            }

            //ab minIndex vo index h jisme i left and right in teeno m se min element h ..ab agar minIdx i hi h to kuch karne ki jaroorat nai h ..otheerwise swapping is required in order to fix this heap

            if(minIdx != i)
            {
                int temp = list.get(minIdx);
                list.set(minIdx, list.get(i));
                list.set(i, temp);
                //ab swapping to kar di ..ab left ya right child m se jo bhi swap hua h  uske liye heapify ko call karna parega taki agar uske liye heap bigar gya ho to fix ho jae 
                heapify(minIdx);

            }


        }
        public int  remove()    // O(logn)  vo aise ki phle do steps m constant time lagta  h bas heapify m hum worst case m top se leke saare levels neeche aayenge that means agar n nodes h to logn levels travel karenge isi liye O(logn) 
        {
            int data = list.get(0);

            //step 1 -  swap first and last
            int temp = list.get(0);
            list.set(0, list.get(list.size()-1));
            list.set(list.size()-1, temp);

            //step2 remove last
            list.remove(list.size()-1);

            //step 3 heapify
            heapify(0);

            return data; // jo remove kiya h use return kara diya 
         
        }

        public boolean isEmpty()
        {
            return list.size()==0;
        }
    }
    public static void main(String args[])
    {
        heap h = new heap();
        h.add(3);
        h.add(4);
        h.add(2);
        h.add(5);
        while(!h.isEmpty())
        {
            System.out.println(h.peek());
            h.remove();
        }
        // ab agar h ki jagah naam de de PQ to hum ek similairty dekhenge ki ye saare kaam vahi ho rhe h  jo collections framework vaali priority queue karti h..
        // yaani collections frameweork vaali priority queue jp hoti h vo is heap data structure ki form m implement hoti h jise humne directly use kiya tha 
        // aur ye min heap h max heap ke liye same rahega bas logic change ho jaega add aur remove m less than ki jagah greater than aajaega 
        
    }
}