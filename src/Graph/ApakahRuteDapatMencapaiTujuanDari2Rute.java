package Graph;

import java.util.*;

class ApakahRuteDapatMencapaiTujuanDari2Rute {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // If source and target are the same, no buses needed
        if (source == target) return 0;

        // Map: Bus Stop -> List of Bus Numbers that stop there
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int busStopNo = routes[i][j];
                ArrayList<Integer> busNo = map.getOrDefault(busStopNo, new ArrayList<>());
                busNo.add(i);
                map.put(busStopNo, busNo);
            }
        }

        // If source or target doesn't exist in any route, impossible to travel
        if (!map.containsKey(source) || !map.containsKey(target)) return -1;

        // Queue for BFS (bus stops)
        Queue<Integer> queue = new LinkedList<>();

        // Sets for visited bus stops and buses
        HashSet<Integer> busStopVisited = new HashSet<>();
        HashSet<Integer> busVisited = new HashSet<>();

        int cost = 0;

        // Start BFS from source bus stop
        queue.add(source);
        busStopVisited.add(source);

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int rem = queue.poll();

                // Found target stop
                if (rem == target) return cost;

                // Get all buses from this stop
                ArrayList<Integer> buses = map.get(rem);
                if (buses == null) continue; // ✅ FIX: prevent NullPointerException

                for (int bus : buses) {
                    if (busVisited.contains(bus)) continue;

                    // Explore all stops reachable by this bus
                    for (int busStop : routes[bus]) {
                        if (busStopVisited.contains(busStop)) continue;
                        queue.add(busStop);
                        busStopVisited.add(busStop);
                    }

                    busVisited.add(bus);
                }
            }
            cost++; // Taking a new bus increases cost
        }

        return -1; // Target not reachable
    }

    // You can test this directly:
    public static void main(String[] args) {
        ApakahRuteDapatMencapaiTujuanDari2Rute sol = new ApakahRuteDapatMencapaiTujuanDari2Rute();
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int source = 1, target = 6;
        System.out.println(sol.numBusesToDestination(routes, source, target)); // ✅ Output: 2
    }
}
