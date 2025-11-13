package Graph;

import java.util.*;

class CariRuteKeSatuTitikDenganObstacleBFS {
    // Direction vectors for moving up, right, down, left (4-directional movement)
    private int[] directions = {-1, 0, 1, 0, -1};

    public int getFood(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Queue for BFS traversal, storing coordinates as int arrays
        Deque<int[]> queue = new ArrayDeque<>();

        // Find the starting position marked with '*'
        boolean startFound = false;
        for (int row = 0; row < rows && !startFound; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '*') {
                    queue.offer(new int[] {row, col});
                    startFound = true;
                    break;
                }
            }
        }

        // Track the number of steps taken
        int steps = 0;

        // BFS to find shortest path to food
        while (!queue.isEmpty()) {
            steps++;

            // Process all cells at current distance level
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                int[] currentPosition = queue.poll();

                // Explore all 4 adjacent directions
                for (int dir = 0; dir < 4; dir++) {
                    int nextRow = currentPosition[0] + directions[dir];
                    int nextCol = currentPosition[1] + directions[dir + 1];

                    // Check if the next position is within grid boundaries
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                        // Found food, return the number of steps
                        if (grid[nextRow][nextCol] == '#') {
                            return steps;
                        }

                        // If it's an open space, mark as visited and add to queue
                        if (grid[nextRow][nextCol] == 'O') {
                            grid[nextRow][nextCol] = 'X';  // Mark as visited
                            queue.offer(new int[] {nextRow, nextCol});
                        }
                    }
                }
            }
        }

        // No path to food found
        return -1;
    }
}