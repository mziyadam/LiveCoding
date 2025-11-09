package BinarySearch;

public class ElemenTerkecilDiArrayTerurutYangDiputar {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;  // invalid input
        }

        int left = 0;
        int right = nums.length - 1;
        int target = Integer.MAX_VALUE;  // track smallest value found

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the subarray is already sorted
            if (nums[left] <= nums[right]) {
                target = Math.min(target, nums[left]);
                break;  // we already found the smallest
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                // update minimum with the leftmost value
                target = Math.min(target, nums[left]);
                left = mid + 1;  // move to the right half
            }
            // Right half is unsorted (contains rotation point)
            else {
                target = Math.min(target, nums[mid]);
                right = mid - 1;  // move to the left half
            }
        }

        return target;

    }

}


