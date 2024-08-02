class abc {//quicksort- pivot and partition method
    public static int partition(int arr[], int low, int high) { //partiton function gives the correct position of pivot and arranges elements before pivot in ascending order
        int pivot = arr[high];
        int i = low-1;
        for(int j= low; j<high; j++) {
          if(arr[j] < pivot) {
            i++;
            //swap
            int temp = arr[i]; 
            arr[i] = arr[j];
            arr[j] = temp;
       }
       }
       //before pivot elements are arranged now we take pivot to its true position
       i++;
        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
        return i; //this i is the index of pivot element.
    
      }
      public static void quickSort(int arr[], int low, int high) {
        if(low<high) {
          int pidx = partition( arr, low, high);
          quickSort(arr, low, pidx-1);
          quickSort(arr, pidx+1, high);
        }
    
      }
    public static void main(String[] args) {   //2 3 5 6 8 9
      int[] arr = {6,3,9,5,2,8};
      int n = arr.length;
      quickSort(arr, 0, n-1);
      //print arr
      for(int i = 0; i <n; i++) {
        System.out.print(arr[i] + " ");
      }
      System.out.println();
    }
}
//time complexity 
// worst: O(n^2) worst case occurs when pivot is always the smallest or largest element of the array.(exmple: for a soted array in ascending order pivot is largest index element,
// and for a sorted array in descending order, pivot is smallest index element( this happens in the case where we first assume last element of array to be pivot))

//average: O(nlogn)