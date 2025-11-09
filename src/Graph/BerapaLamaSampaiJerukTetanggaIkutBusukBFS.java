package Graph;
import java.util.*;
public class BerapaLamaSampaiJerukTetanggaIkutBusukBFS {
        public int orangesRotting(int[][] grid) {
            int R = grid.length;
            int C = grid[0].length;

            // Queue stores the coordinates (row, col) of rotten oranges
            Queue<int[]> queue = new LinkedList<>();
            int freshCount = 0;

            // --- 1. Initialization: Find all initial rotten oranges and count fresh ones ---
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] == 2) {
                        queue.offer(new int[]{r, c});
                    } else if (grid[r][c] == 1) {
                        freshCount++;
                    }
                }
            }

            // Directions for checking neighbors (Up, Down, Left, Right)
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            int minutes = 0;

            // --- 2. BFS Traversal ---
            // BFS continues as long as there are rotten oranges to spread from,
            // AND there are fresh oranges left to rot.
            while (!queue.isEmpty() && freshCount > 0) {
                minutes++; // A new minute passes for the new layer of rotting
                int layerSize = queue.size(); // Process all oranges that rotted in the LAST minute

                for (int i = 0; i < layerSize; i++) {
                    int[] rotten = queue.poll();
                    int r = rotten[0];
                    int c = rotten[1];

                    // Check all 4 neighbors
                    for (int[] dir : directions) {
                        int nextR = r + dir[0];
                        int nextC = c + dir[1];

                        // Check bounds and if the neighbor is a fresh orange (1)
                        if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && grid[nextR][nextC] == 1) {

                            // 1. Mark as rotten (crucial for visited/state change)
                            grid[nextR][nextC] = 2;

                            // 2. Decrement the fresh count
                            freshCount--;

                            // 3. Add to the queue for the next minute's spreading
                            queue.offer(new int[]{nextR, nextC});
                        }
                    }
                }
            }

            // --- 3. Result Check ---
            // If freshCount is 0, all oranges rotted, return the total time.
            // If freshCount is > 0, some oranges were unreachable, return -1.
            return freshCount == 0 ? minutes : -1;
        }
    }

