
import java.io.*;
import java.util.*;

public class Solution_1 {
    public static void main(String[] args) {
        List<long[]> input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("9\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                long[] temp = new long[2];
                temp[0] = Long.parseLong(parts[0]);
                temp[1] = Long.parseLong(parts[1]);
                input.add(temp);
            }
        } catch (IOException e) {
            System.out.println("Error occured while reading from file: " + e.getMessage());
        }

        long result = 0;
        int n = input.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long[] coord1 = input.get(i);
                long[] coord2 = input.get(j);
                long dist = (Math.abs(coord1[0] - coord2[0] + 1) * Math.abs(coord1[1] - coord2[1] + 1));
                result = Math.max(result, dist);
            }
        }
        System.out.println("Result: " + result);
    }
}
