import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution_2 {
    public static void main(String[] args) {
        long result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("2\\input.txt"))) {
            String line;
            if ((line = reader.readLine()) != null) {
                String[] ranges = line.split(",");
                for (String range : ranges) {
                    String[] parts = range.split("-");
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    
                    for(long num = start; num <= end; num++) {
                        String numStr = String.valueOf(num);

                        int n = numStr.length();
                        for (int idx = 1; idx <= n / 2; idx++) {
                            String currString = numStr.substring(0, idx);
                            
                            if (n % idx != 0) continue;

                            int numRepeat = n / idx;
                            if (currString.repeat(numRepeat).equals(numStr) && numRepeat >= 2) {
                                result += num;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }

        System.out.println("Result: " + result);
    }    
}
