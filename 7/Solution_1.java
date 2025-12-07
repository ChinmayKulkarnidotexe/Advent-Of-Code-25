import java.io.*;
import java.util.*;

public class Solution_1 {
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

        int[] startPos = new int[2];
        int n = map.size();
        int m = map.get(0).size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map.get(i).get(j) == 'S') {
                    startPos[0] = i;
                    startPos[1] = j;
                    break;
                }
            }
        }

        Set<Integer> beams = new HashSet<>();
        beams.add(startPos[1]);
        int depth = 1;
        int result = 0;
        while (depth != n - 1) {
            for (int j = 0; j < m; j++) {
                if (map.get(depth).get(j) == '^' && beams.contains(j)) {
                    beams.remove(j);
                    beams.add(j - 1);
                    beams.add(j + 1);
                    result++;
                }
            }
            depth++;
        }
        System.out.println("result: " + result);
    }
}
