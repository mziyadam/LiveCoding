package Graph;

import java.util.*;

class JalanKudaMinimumKeLokasiBFS {

    // The 8 possible moves a knight can make (dx, dy)
    private final int[][] DIRS = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };

    /**
     * Finds the minimum number of moves for a knight to reach (x, y) from (0, 0).
     * @param targetX The target X coordinate.
     * @param targetY The target Y coordinate.
     * @return The minimum number of moves.
     */
    public int minKnightMoves(int targetX, int targetY) {

        // 1. Exploit Symmetry: Work only in the first quadrant
        int x = Math.abs(targetX);
        int y = Math.abs(targetY);

        // Use a Queue for BFS: Stores {current_x, current_y}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        // Use a Set to track visited positions to prevent cycles and redundant work
        Set<String> visited = new HashSet<>();
        visited.add("0,0"); // Store coordinates as a string for use in the Set

        int moves = 0;

        // 2. BFS Traversal
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Process all positions reachable in 'moves'
            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int currX = current[0];
                int currY = current[1];

                // Check for Target
                if (currX == x && currY == y) {
                    return moves;
                }

                // Explore all 8 possible moves
                for (int[] dir : DIRS) {
                    int nextX = currX + dir[0];
                    int nextY = currY + dir[1];

                    String key = nextX + "," + nextY;

                    // Optimization: We only need to visit coordinates that are
                    // not too far into the negative quadrant, which simplifies the infinite board.
                    // Since we're searching for (x, y) with x,y >= 0, we can safely limit
                    // the search to positions where x >= -2 and y >= -2.
                    if (nextX >= -2 && nextY >= -2 && !visited.contains(key)) {
                        visited.add(key);
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
            }

            moves++; // Increment moves after processing the entire level
        }

        // The problem guarantees a solution exists, so this line should not be reached.
        return -1;
    }
}
