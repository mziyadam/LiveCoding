package DSA;

import java.util.*;
import java.io.*;

public class MencariSiapaPelangganYangKehilanganDompet{
    static class Customer {
        int id;
        int receptionNo;
        int maintenanceNo;
        public Customer(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken()); // lost wallet reception window
            int B = Integer.parseInt(st.nextToken()); // lost wallet maintenance window

            int[] a = new int[N + 1]; // reception times
            int[] b = new int[M + 1]; // maintenance times
            int[] t = new int[K + 1]; // arrival times

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) b[j] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int k = 1; k <= K; k++) t[k] = Integer.parseInt(st.nextToken());

            int[] receptionEnd = new int[N + 1];
            int[] maintenanceEnd = new int[M + 1];
            int[] receptionUsing = new int[N + 1];
            int[] maintenanceUsing = new int[M + 1];
            int[] receptionResult = new int[K + 1];
            int[] maintenanceResult = new int[K + 1];

            Queue<Integer> receptionWait = new LinkedList<>();
            Queue<int[]> maintenanceWait = new LinkedList<>(); // [receptionNo, custId]

            int time = 0, idx = 1, done = 0;

            while (done < K) {
                // maintenance completion
                for (int j = 1; j <= M; j++) {
                    if (maintenanceUsing[j] != 0 && maintenanceEnd[j] == time) {
                        maintenanceUsing[j] = 0;
                        done++;
                    }
                }

                //  reception completion -> maintenance waiting queue
                List<int[]> temp = new ArrayList<>();
                for (int i = 1; i <= N; i++) {
                    if (receptionUsing[i] != 0 && receptionEnd[i] == time) {
                        int cust = receptionUsing[i];
                        receptionUsing[i] = 0;
                        temp.add(new int[]{i, cust});
                    }
                }
                // Sort by reception number priority
                temp.sort(Comparator.comparingInt(x -> x[0]));
                for (int[] pair : temp) maintenanceWait.offer(pair);

                // new arrivals -> reception wait queue
                while (idx <= K && t[idx] == time) {
                    receptionWait.offer(idx);
                    idx++;
                }

                // assign reception
                for (int i = 1; i <= N; i++) {
                    if (receptionUsing[i] == 0 && !receptionWait.isEmpty()) {
                        int cust = receptionWait.poll();
                        receptionUsing[i] = cust;
                        receptionResult[cust] = i;
                        receptionEnd[i] = time + a[i];
                    }
                }

                //assign maintenance
                for (int j = 1; j <= M; j++) {
                    if (maintenanceUsing[j] == 0 && !maintenanceWait.isEmpty()) {
                        int[] pair = maintenanceWait.poll();
                        int rno = pair[0];
                        int cust = pair[1];
                        maintenanceUsing[j] = cust;
                        maintenanceResult[cust] = j;
                        maintenanceEnd[j] = time + b[j];
                    }
                }

                time++;
            }

            int ans = 0;
            for (int i = 1; i <= K; i++) {
                if (receptionResult[i] == A && maintenanceResult[i] == B) ans += i;
            }
            if (ans == 0) ans = -1;

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb.toString());
    }
}
