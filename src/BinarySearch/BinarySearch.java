package BinarySearch;

public class BinarySearch {
    public static int binarySearch(int[] arr, int target){
        int min=0,max=arr.length-1;
        int currentGuest=(min+max)/2;
        while(target!=arr[currentGuest]){
            if(max<min){
                return -1;
            }
            currentGuest=(min+max)/2;
            if(arr[currentGuest]<target){
                min = currentGuest+1;
            }else if(arr[currentGuest]>target){
                max = currentGuest-1;
            }
        }
        return currentGuest;
    }
}
