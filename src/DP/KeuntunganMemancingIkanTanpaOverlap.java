package DP;

import java.util.*;

public class KeuntunganMemancingIkanTanpaOverlap {

    static class Segment {
        int start, end, profit;

        Segment(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int L = sc.nextInt(); // river length
            int C = sc.nextInt(); // cost per fisherman
            int D = sc.nextInt(); // days before river dries
            int[] fish = new int[L];
            for (int i = 0; i < L; i++) {
                fish[i] = sc.nextInt();
            }

            List<Segment> segments = new ArrayList<>();

            for (int i = 0; i < L; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = i; j < Math.min(L, i + D); j++) {
                    temp.add(fish[j]);
                    List<Integer> sorted = new ArrayList<>(temp);
                    sorted.sort(Collections.reverseOrder());
                    int sum = 0;
                    for (int k = 0; k < Math.min(D, sorted.size()); k++) {
                        sum += sorted.get(k);
                    }
                    int profit = sum - C;
                    if (profit > 0) {
                        segments.add(new Segment(i, j, profit));
                    }
                }
            }

            segments.sort(Comparator.comparingInt(s -> s.end));
            int n = segments.size();
            int[] dp = new int[n];
            dp[0] = segments.get(0).profit;

            for (int i = 1; i < n; i++) {
                int inclProfit = segments.get(i).profit;
                int l = binarySearch(segments, i);
                if (l != -1) {
                    inclProfit += dp[l];
                }
                dp[i] = Math.max(dp[i - 1], inclProfit);
            }

            System.out.println(n == 0 ? 0 : dp[n - 1]);
        }
    }

    static int binarySearch(List<Segment> segments, int index) {
        int low = 0, high = index - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (segments.get(mid).end < segments.get(index).start) {
                if (segments.get(mid + 1).end < segments.get(index).start) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
