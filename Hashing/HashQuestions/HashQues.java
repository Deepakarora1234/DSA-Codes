import java.util.*;
public class HashQues {
    public static void main(String args[]) {
        
        //question 1...given an array of integers..find all elements that appear more than n/3 times where n is the size of array

        //here we will use hashMap...numbers will be our keys and their frquesncies will be our values
        //code for ques 1:
        // int [] arr={1,3,3,5,1,3,1,3,1};
        // HashMap<Integer,Integer> map=new HashMap<>();
        // for(int i=0; i<arr.length; i++) {
        //     if(map.containsKey(arr[i])) {
        //         map.put(arr[i],map.get(arr[i])+1);
        //     }
        //     else {
        //         map.put(arr[i], 1);
        //     }
        // }
        // Set<Integer> keys=map.keySet();         // is step ko aise bhi kar sakte h
        // for(Integer k:keys) {                   // for(int key:map.keySet) {
        //     if(map.get(k) >arr.length/3) {          
        //         System.out.println(k+" ");      // }
        //     }
        // }


        //ques 2: itersection of two Arrays..
        //eg  arr1-{7,3,9}  arr2={6,3,9,2,9,4}...only 3 and 9 are common to both..therefore we print 2 as only 2 elements are common
        // code for ques 2:

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();        // ye bhi kar sakte h ki kisi ek array ke elements ko set m daal liya fir doosre array pe traverse karte gye aur saare elements check karte gye

        int[] arr1={7,3,9};                             // agar element set m h to count ++ ho jaega ..aur jaise hi set m element mil gya aur count increase kar diya to us element ko set m se remove kar do..

        int[] arr2 = {6,3,9,7,9,4};                     // element remove is liye kiya taaki agar array m dobara koi same element aagya to uske liye check karte time count increase na ho jae...

        for(int i=0; i<arr1.length; i++) {              // for eg arr1 ke elements ko set m daal diya...arr2 pe traverse kar ke har element ke liye check kar liya
            set1.add(arr1[i]);                              // 6 ke loye check kiya..6 set m nai h..fir 3 ke liye kiye..e h set m to count increase kar diya aur set se 3 ko remove kar diya taaki agar dobara array m 3 aaye kahi aage to vo dobara count na hp jaaye

        }
        for(int i=0; i<arr2.length; i++) {
            set2.add(arr2[i]);
        }
        int a=0;
        Iterator it=set2.iterator();
        while(it.hasNext()) {
            if(set1.contains(it.next())) {
                a++;
            }
        }
        System.out.println(a);    


        //ques 3: Subarrays sum equal to k ..yaani ek array diya hua h eg [1,2,3] aur ek k diya h eg 3..to no. of subarrays batane h jinke elements ka sum k ke equal h..in this case ans is 2..[1,2] and [3]

        //ek approach to ye h ki nested loop laga lo aur outer loop m ek ek element ko starting point maan ke sum check kar lo usme O(n^3) ka time lagta h..

        // ek approach h prefix array banane ki...prefix array ka size given array ke size jinta hi hota h..usme hum array ke hur index tak ke elements ka sum store karate h..
        // for eg..if given array is [1,2,3,4] to prefix aray hoga[1,3,6,10]...yaani i=0 ke liye 0th index tak ke elements ka sum..i=1 ke liye 1st index tak ke elements tak ka sum and so on..
        // ab hume agar koi se sub array jo index i se j tak h uske elements ka sum nikalna h to hum prefix array ka use karte h and that sum is equal to P[j] - P[i-1]
        //yaani agar [1,2,3,4] m i=1 j=3 yaani 2 se leke 4 tak ka sum nikalna h to index 0 se 3 (yaani j) tak ka sum nikala(which is equal to P[3] ) aur is me se minus kar diya 0 se leke 1-1(yaani i-1) tak ka sum(which is equal to P[0]  (P[i-1]))
        //lkn is approach m bhi loop laga ke alag alag i and j lene parenge to O(n^2) lag jaega....isi liye hum hashmap ka use karenge lk approach kuch kuch prefix array jaisi hi hogii
        
        //hashmap approach...hume basically ye expression given h...  k = sub[j] -sub[i-1] ... yaani i se leke j index tak ka sub array ke elements ka sum k ke equal check karna h to 0 se leke j tak ke elemesnts ke sum m se 0 se leke i-1 index tak ke elements tak ka sum - kar diya..agar vo k ke equa h to i se leke j vaala sub array valid ans h
        // k=sub[j] - sub[i-1]...=> sub[j] - k=sub[i-1]....is expression m k to given hi h yaani constant ..aur agar j constant kar de to i ki fixed value ho jaegi as 3 m se do variables ki value agar fix kar di to bache hue variable ki bhi fixed value hi aayegi

        // yahi karna h hume hashmap vaali approach m....hum i-1 tak ke elements ka sum hashmap m as a key store kara denge phle hi...aur alag alag j ke liye har bar sub[j]-k ki value ko containsKey function se check kar lenge..agar ye value as a kaey mil gai map m..to count ++ kar denge..yaani ye vaala subarray(i se j) valid ans h.

        //ab ye decide karna h ki map m key aur value vaale pair m key aur value m store kya karana h
        // for eg let array be [10,2 -2,-20, 10]..and k =-10 ..iska ans aana chahiye 3..as [10,2,-2, -20] h ek ans (yaani i=0, j=3 vaala sub array), [2,-2,-20,10] (yaani i=1,j=4 aala subarray) and [-20,10] (yaani i=3 j=4 vaala sb array)
        // map m key m to saare i-1 vaale sum store karenge aur value m un sum ki frequency store karenge..
        // ek aur important step is map m shuru m (0,1) store karenge yaani 0 key m aur 1 value(frequency) m..ye empty array ke liye h ...yaani upar vaale ex m agar j 3 h...to 0 se leke e vaala jo sub array h uske saaare elements ka sum k ke equal h..matlab sum[j]-k=0..aur sum[j]-k=sum[i-1]...to is case m sum[i-1] [] empty array hi lena parega
        // ek ans variable le liya =0;..ab dry run karte h is example ke liye....                                 key(sum)    value(frequency)
        //A= [10,2,-2,-20,10] k=-10                                                                                  0            1               ye jo upar bataya h (0,1) vaala khud se daal diya
        // loop chalaeenge j=0 se last index tak...ek sum variable =0; banaya                                        10           1+1
        //                                                                                                           12           1
        // for j=0; sum ho jaega sum+A[j]=10...sum-k ho jaega 20..20 map m nai h to iske corresponding 
        // sum ki value ko frequency 1 ke saath map m add kar diya..yaani 10 key m add ho gya freq 1

        // j hogya 1 sum ho gya sum+A[j] yaani 10+2=12...sum -k = 22..ye 22 bhi map m nai h to sum(12) map m 
        // add kar diya
        
        // j ho gya 2..sum=sum+A[j] yaani 12-2=10..sum-k ho gya 20,,20 map m nai h to sum(10) map m add kiya
        // aur 10 phle se hi tha to uski khaali freq update ho gi

        //j ho gya 3..sum=sum+A[j] yani sum ho gya -10..sum-k=0...aur 0 map m h
        // to humne ans=ans +map.get(sum-k (yaani 0)) kar diya ..yaani ans m sum-k vaali key ki freq add kar di .. 
        // agar 0,1 vaala pair jo khud se map m daala tha vo nain daalte to ek ans m ek sub array kam milta ..is loye vo daalna jaroori hota h

        // j ho gya 4..sum+=A[j] yanai sum 0  sum -k =10..aur 10 bhi h map m ...
        // to ans +=map.get(sum-k (yaani 10)) kar diya ..yaani ans ho gya 1+2=3..

        // loop end ho gya aur hume ans mil gya 3..O(n) time laga most efficient
        //code:

        int[] A = {10,2,-2,-20,10};
        int K=-10;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int sum=0;
        int ans=0;
        for(int j=0; j<A.length; j++) {
            sum+=A[j];
            if(map.containsKey(sum-K)) {
                ans+=map.get(sum-K);
            }
            else {
                if(map.containsKey(sum)) {
                    map.put(sum,map.get(sum)+1);
                }
                else {
                    map.put(sum,1);
                }
            }
        }
        System.out.println(ans);
        


    }
}