
import java.io.*;

public class Solution_1 {
    public static void main(String[] args) {
        long result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("3\\input.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                long maxSeen = 0;
                int n = line.length();
                for (int i = 0; i < n; i++) {
                    String curr = line.charAt(i) + "";
                    for(int j = i + 1; j < n; j++) {
                        String next = line.charAt(j) + "";
                        maxSeen = Math.max(Integer.parseInt(curr + next), maxSeen);
                    }
                }
                result += maxSeen;
            }
        } catch (IOException e) {
            System.out.println("Error occured when reading file: " + e.getMessage());
        }
        System.out.println("Result: " + result);
    }
}