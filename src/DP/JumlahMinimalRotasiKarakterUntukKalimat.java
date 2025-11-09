package DP;

import java.io.*;
import java.util.*;

public class JumlahMinimalRotasiKarakterUntukKalimat {

    // Helper method to find minimum rotation distance using binary search
    private static int minRotation(List<Integer> positions, int qPos, int N) {
        int best = Integer.MAX_VALUE;

        // Binary search to find closest position
        int left = 0, right = positions.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int pos = positions.get(mid);
            int diff = Math.abs(pos - qPos);
            int rot = Math.min(diff, N - diff);
            best = Math.min(best, rot);

            if (pos < qPos) left = mid + 1;
            else right = mid - 1;
        }

        // Check neighbors for wrap-around cases
        int first = positions.get(0);
        int last = positions.get(positions.size() - 1);
        best = Math.min(best, Math.min(Math.abs(first - qPos), N - Math.abs(first - qPos)));
        best = Math.min(best, Math.min(Math.abs(last - qPos), N - Math.abs(last - qPos)));

        return best;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(), M = scan.nextInt();
        scan.nextLine();

        char[][] vert = new char[N][M];
        for (int i = 0; i < N; i++) {
            vert[i] = scan.nextLine().toCharArray();
        }

        // Preprocess: map each column to character positions
        Map<Character, List<Integer>>[] columnMap = new HashMap[M];
        for (int j = 0; j < M; j++) {
            columnMap[j] = new HashMap<>();
            for (int i = 0; i < N; i++) {
                char c = vert[i][j];
                columnMap[j].computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
            // Sort each list for binary search
            for (List<Integer> list : columnMap[j].values()) {
                Collections.sort(list);
            }
        }

        int Q = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < Q; i++) {
            int qPos = scan.nextInt() - 1;
            String temp = scan.next();
            boolean isCan = true;
            int count = 0;

            for (int j = 0; j < M; j++) {
                char target = temp.charAt(j);
                List<Integer> positions = columnMap[j].get(target);

                if (positions == null || positions.isEmpty()) {
                    isCan = false;
                    break;
                }

                count += minRotation(positions, qPos, N);
            }

            System.out.println(isCan ? count : -1);
        }
    }
}

