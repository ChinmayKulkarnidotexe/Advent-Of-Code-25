import java.io.*;
import java.util.*;

public class Solution_2 {
    public static void main(String[] args) {
        List<List<Character>> map = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("7\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Character> row = new ArrayList<>();
                for (char ch : line.toCharArray()) {
                    row.add(ch);
                }
                map.add(row);
            }
        } catch (IOException e) {
            System.out.println("Error occured while reading from file: " + e.getMessage());
        }

        long n = map.size();
        long m = map.get(0).size();

        long sRow = -1, sCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map.get(i).get(j) == 'S') {
                    sRow = i;
                    sCol = j;
                    break;
                }
            }
            if (sRow != -1) break;
        }

        long startX = sRow + 1;
        long startY = sCol;

        Map<String, Long> active = new HashMap<>();
        active.put(startX + "," + startY, 1L);

        long result = 0;

        while (!active.isEmpty()) {
            Map<String, Long> next = new HashMap<>();

            for (Map.Entry<String, Long> entry : active.entrySet()) {
                long count = entry.getValue();
                String[] parts = entry.getKey().split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                if (x < 0 || x >= n || y < 0 || y >= m) {
                    result += count;
                    continue;
                }

                char cell = map.get(x).get(y);

                if (cell == '^') {
                    //check left
                    long left = y - 1;
                    if (left >= 0) {
                        String key = x + "," + left;
                        next.put(key, next.getOrDefault(key, 0L) + count);
                    } else {
                        result += count;
                    }

                    //check right
                    long right = y + 1;
                    if (right < m) {
                        String key = x + "," + right;
                        next.put(key, next.getOrDefault(key, 0L) + count);
                    } else {
                        result += count;
                    }

                } else {
                    long nx = x + 1;
                    if (nx < n) {
                        String key = nx + "," + y;
                        next.put(key, next.getOrDefault(key, 0L) + count);
                    } else {
                        result += count;
                    }
                }
            }

            active = next;
        }

        System.out.println("result: " + result);
    }
}
