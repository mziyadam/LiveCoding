package BinarySearch;

public class MedianDari2ArrayTerurut {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if ((n1 + n2) % 2 != 0) {
            return helper(nums1, 0, n1 - 1, nums2, 0, n2 - 1, (n1 + n2) / 2);
        } else {
            return (helper(nums1, 0, n1 - 1, nums2, 0, n2 - 1, ((n1 + n2) / 2) - 1) + helper(nums1, 0, n1 - 1, nums2, 0, n2 - 1, (n1 + n2) / 2)) / 2.0;
        }
    }

    private double helper(int[] n1, int s1, int e1, int[] n2, int s2, int e2, int index) {

        if (s1 > e1) {
            return n2[index - s1];
        }

        if (s2 > e2) {
            return n1[index - s2];
        }

        int m1 = (e1 - s1) / 2 + s1;
        int m2 = s2 + (e2 - s2) / 2;

        int m1_num = n1[m1];
        int m2_num = n2[m2];

        if (m1 + m2 < index) {

            if (m1_num < m2_num) {
                return helper(n1, m1 + 1, e1, n2, s2, e2, index);
            } else {
                return helper(n1, 0, e1, n2, m2 + 1, e2, index);
            }

        } else {

            if (m1_num < m2_num) {
                return helper(n1, 0, e1, n2, s2, m2-1, index);
            } else {
                return helper(n1, 0, m1-1, n2, 0, e2, index);
            }

        }

    }
}