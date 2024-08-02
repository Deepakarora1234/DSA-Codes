public class HeapSort
{
    /*
     * heap sort:-
     * heap sort also takes O(nlogn);
     * we are given an array to sort 
     * for that we make a max heap of that array to sort in ascending order ..for descending order we have to make a min heap
     * 
     * to make a max heap of an array ..we first assume a binary tree by putting nodes level by level..
     * for eg given array is 6 1 3 2 4 
     * 
     * so binary tree level by level is    
     *             6
     *            / \
     *           1   3
     *          / \ 
     *         2   4     
     * 
     * now to convert this into a max heap ..we will call our heapify function(and this time heapify m logic max heap ke liye hoga taaki heap fix hone ke baad parent > child) function ko callkarenge non-leaf nodes ke liye
     * leaf nodes ke liye bhi kar sakteh call lkn leaf nides ke liye heapify kuch nai karta to unke liye call karne ki jaroorat nai..
     * 
     * non leaf nodes hume original array ke mid index se leke 0 indextak mil jaengi ...using the property that ith index ke liye left child is at 2*i+1th index and right is at 2*i + 2th index
     * 
     * to non leaf nodes ke liye heapify ko call kiya isse hume ana max heap mil jaega..
     * 
     * next step m har baar max element yaani top most node of heap ko last index vaali (yaani bottom of heap) se swap kar denge..aur swap karne ke bad max element last m aajega ..usko a heap se hata denge..
     * aur fir se jo swap ho ke top yaani 0th index pe gai h node uske liye heapify ko call kar ke heap ko fix kar lenge..
     * 
     * fir heap fix hone ke baad hume max heap mil jaega ..fir se max element yaani top most yaani 0th index vaale element ko second last( last pe to purana vaala max element h) index vaale ke saath swap karaenge aur 0 ke liye heapify ko call kar ke fix kar lenge heap ko..
     * aisa karte rahenge aur hume sorted array miljaega in O(nlogn) and without using extra space
     * 
     * upar vaale eg ke liye heapify ko call kar kar ke phle maxheap banaennge vo aisa banega
     * 
     *        6
     *       / \
     *      4   3
     *     / \ 
     *    2   1       yaani 6 4 3 2 1
     * 
     * ab 0th index and last index swap hoga ... 1 4 3 2 6  aur 6 ko hata denge heap se ...yaani ek variable(i) lenge jisko arr.length-1 se satrt karenge ..aur har swap ke baad us variable ko minu minus kar denge isse heap ka size kam hota rhega
     * 0th index ke liye heapify call karenge   aur heap ban jaega   4 2 3 1 | 6   as 6 ab part nai h..
     * 
     * fir se 0th index aur last index yaani i vaala swap hoga ..4 aur 1 swap hoga as i-- ho gy thab....aur swapping ke baad i--
     * array becomes 1 2 3|4 6 aur heapify call kar denge 0th index ke liye..
     * heapify ke baad heap fix hoga and array becomes 3 2 1|4 6
     * and so on and last m sorted array mil jaega 
     * 
     *   
     */
    public static  void heapify(int i, int n,int arr[])
    {
        //ye heapify maxHeap ke hisaab se likhenge 

        int left = 2*i + 1;
        int right = 2*i +2;
        int maxIdx = i;
        if(left < n && arr[left] > arr[maxIdx])
        {
            maxIdx = left;
        }
          if(right < n && arr[right] > arr[maxIdx])
        {
            maxIdx = right;
        }
        if(maxIdx!=i)
        {
            //swap
            int temp = arr[maxIdx];
            arr[maxIdx] = arr[i];
            arr[i] = temp;

            heapify(maxIdx, n,arr);
        }
    }
    public static void heapSort(int arr[])  // O(nlogn)  nlogn aise as heapify finction takes log n time aur ek baar loop m heapify n/2 times call ho rha h aur next loop m n times .. to totl time n/2*logn + nlogn roughly equals nlogn
    {
         int n = arr.length;
        //step 1  building a maxHeap
        for(int i=arr.length/2; i>=0; i--)
        {
           
            heapify(i, n,arr);
        }

        //step 2  swap largest element of heap to last and fix the heap
        for(int i = n-1; i>0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            heapify(0,i,arr);  // 0 ke liye heapify call kiya aur size heap ka i bheja h as is loop m har baar i ek minus hoga aur hume heap ka size bhi ek minus kar ke bhejna h as swap hone ke baad largest element ko heap m include nai karna 

        }

    }
    
    public static void main(String args[])
    {
        int []arr = {1,2,4,5,3};
        heapSort(arr);
        for(int i=0; i<arr.length; i++)
        {
            System.out.println(arr[i]);
        }
    }
}