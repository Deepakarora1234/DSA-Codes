public class Divide_And_Con  // divbide and conquer algorithm means divide a problem into smaller subproblems and solve them and get the complete solution from those smaller solutions 
                            /*
                             * Merge sort is an example of divide and conquer algo 
                             * in merge sort ..we keep on dividing the array into smaller arrays until we get individual elements and then we sort those individual elements and then 
                             * we merge those elements to get the sorted array
                             * 
                             * merge sort time complexity - O(nlogn)    space complexity O(n)  where n is size of the temporary array ..isi liye jab extra space na ho available tab merge sort nai karte 
                             */

{
    public static void printArr(int arr[])                 
    {
        for(int i=0; i<arr.length; i++)
        {
            System.out.print(arr[i]+" ");
        }
    }
    public static void merge(int arr[], int start, int mid, int end)
    {
        int i=start;
        int j=mid+1;
        int k=0;
        int temp[] = new int[end-start+1];
        while(i<=mid && j<=end)
        {
            if(arr[i]<arr[j])
            {
                temp[k] = arr[i];
                i++;
                k++;
            }
            else
            {
                temp[k++] = arr[j++];

            }
        }
        while(i<=mid)
        {
            temp[k++] = arr[i++];
        }
        while(j<=end)
        {
            temp[k++] = arr[j++];

        }

        for( k = 0, i=start; k<temp.length; k++, i++)                                
        {
            arr[i] = temp[k];
        }



    }
    public static void mergeSort(int arr[], int s, int e)
    {
        if(s>=e)
        {
            return;
        }
        int mid = s+(e-s)/2;
        mergeSort(arr, s, mid);
        mergeSort(arr, mid+1, e);
        merge(arr, s, mid,e);

    }

    /*
     * QUICK SORT:-
     * 
     * quick sort is pivot and partition sorting technique..modification of divide and conquer..kisi ek element ko pivot le lete h ..usualyy last element ko...fir us pivot ke according
     * pivot se chotte elements left m aur bade elements right m aur fir pivot ko shi jagaha pposition kar dete h aur aisa recursively karte h to sort ho jaata h 
     * 
     * quick sort time complexity for average cases is O(nlogn) but in worst case is O(n^2)...space complexity is constant 
     * 
     * to ek partition function hoga jo hume pivot ko shi index pe laake uska pivot index dega aur pivot se left m chotte and right m bare elements dega..us pivot index ko use kar ke
     * hum left vaale array aur right vaale array ke liye quick sort ko call kar denge recursively
     * 
     * IMPORTANT-worst case time complexity is O(n^2)..ye tab aati h jab array already sorted ho(ASCENDING OR DESCENDING) ..to hamara pivot  last element liya humne ..saare elements uske left m aagye.
     * fir left vaale array ke liye pivot sab se right element hoga fir saare elements left m aaajenge..
     * aisa karte karte last baar esirf ek element ke liye callhoga quicksort..
     * aur phle call m loop n size ke array pe chala tha ..next pe n-1 next pe n-2 karte karte 1 time chala loop..
     * to total time complexity add karenge to 1+2+3+..+n-1+n = n(n+1)/2  which is roughly equal to n^2; 
     * 
     */
    //quick sort
    public static int partition(int[]arr, int start, int end)
    {
       
        int pivot = arr[end];
         int i=start-1; // to make place for elements smaller than pivot

        for(int j=start; j<end; j++)  // ye loop 0 se start nai hoga starting index yaani  start se shur hoga 
        {
            if(arr[j] <= pivot)
            {
                i++;
                int temp=arr[j];
                arr[j] = arr[i];
                arr[i] = temp;

            }
        }
        //ab pivot se chotte elements aagye ab hume pivot ko bhi apni sahi jagah rakhna h
        i++;
        int temp = pivot;
        arr[end] = arr[i];
        arr[i] = temp;   // yaani pivot swap hp gya apni shi jagah vaale element se 
        return i;  // aur yahi i jaha pivot h  vo hamara pivot index hoga use return kara diya
    }


    public static void quickSort(int []arr, int start, int end)
    {
        //base case
        if(start >= end)
        {
            return;
        }

        //choosing pivot :- here last element;

        int pivotIndex = partition(arr,start, end);
        quickSort(arr, start, pivotIndex-1);   // jis index pe partition hua h vo pivot index h yaani pivot shi  position pe h aur hume uske left m sort karna h to end index hojaega pivotIndex-1
        quickSort(arr, pivotIndex+1, end);
    }


    public static void main(String args[])
    {
        int arr[] = {6,3,8,9,5};
        // mergeSort(arr, 0, arr.length-1);
        quickSort(arr, 0, arr.length-1);
        printArr(arr);

        
    }
}

// revise SEARCH IN A ROTATED SORTED ARRAY PROBLEM from vide itself or from leetcode....it is modified bianry search...starting index 0 se leke max element of array tak ke elements ko 
// ek line pe maan lenge aur bache hue elements ko doosri line pe aur uske baad mid ((start+end)/2) ki position ko dekhte hue alag alag cases bana lenge ...hence modified binary search

