package Graph;

import java.util.*;

class CariUrutanDalamBahasaAlien {

    public String alienOrder(String[] words) {
        // Graph representation: graph[i][j] = true means character i comes before character j
        boolean[][] graph = new boolean[26][26];
        // Track which characters appear in the input
        boolean[] seen = new boolean[26];
        // Count of unique characters
        int uniqueCharCount = 0;
        int numWords = words.length;

        // Process each adjacent pair of words to build the graph
        for (int i = 0; i < numWords - 1; i++) {
            // Mark all characters in current word as seen
            for (char c : words[i].toCharArray()) {
                if (uniqueCharCount == 26) {
                    break;
                }
                int charIndex = c - 'a';
                if (!seen[charIndex]) {
                    uniqueCharCount++;
                    seen[charIndex] = true;
                }
            }

            // Compare current word with next word to find ordering
            int currentWordLength = words[i].length();
            for (int j = 0; j < currentWordLength; j++) {
                // If next word is shorter but matches so far, invalid ordering
                // Example: ["abc", "ab"] is invalid
                if (j >= words[i + 1].length()) {
                    return "";
                }

                char char1 = words[i].charAt(j);
                char char2 = words[i + 1].charAt(j);

                // Skip if characters are the same
                if (char1 == char2) {
                    continue;
                }

                // Check for cycle: if char2 -> char1 already exists, adding char1 -> char2 creates a cycle
                if (graph[char2 - 'a'][char1 - 'a']) {
                    return "";
                }

                // Add edge: char1 comes before char2
                graph[char1 - 'a'][char2 - 'a'] = true;
                break; // Only the first different character determines the order
            }
        }

        // Mark all characters in the last word as seen
        for (char c : words[numWords - 1].toCharArray()) {
            if (uniqueCharCount == 26) {
                break;
            }
            int charIndex = c - 'a';
            if (!seen[charIndex]) {
                uniqueCharCount++;
                seen[charIndex] = true;
            }
        }

        // Calculate indegree for each character (topological sort preparation)
        int[] indegree = new int[26];
        for (int from = 0; from < 26; from++) {
            for (int to = 0; to < 26; to++) {
                if (from != to && seen[from] && seen[to] && graph[from][to]) {
                    indegree[to]++;
                }
            }
        }

        // Initialize queue with all characters that have indegree 0
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (seen[i] && indegree[i] == 0) {
                queue.offerLast(i);
            }
        }

        // Perform topological sort using BFS (Kahn's algorithm)
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            int current = queue.pollFirst();
            result.append((char) (current + 'a'));

            // Reduce indegree for all neighbors
            for (int neighbor = 0; neighbor < 26; neighbor++) {
                if (neighbor != current && seen[neighbor] && graph[current][neighbor]) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.offerLast(neighbor);
                    }
                }
            }
        }

        // If we couldn't process all characters, there's a cycle
        return result.length() < uniqueCharCount ? "" : result.toString();
    }
}