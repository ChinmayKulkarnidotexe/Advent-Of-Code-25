import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution_1 {
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
                        // Map<Long, Integer> map = new HashMap<>();
                        // long temp = num;
                        // while(temp != 0) {
                        //     long rem = temp % 10;
                        //     map.put(rem, map.getOrDefault(rem, 0) + 1);
                        //     temp /= 10;
                        // }

                        // boolean isInvalid = true;
                        // for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                        //     if (entry.getValue() % 2 != 0) {
                        //         isInvalid = false;
                        //         break;
                        //     }
                        // }

                        // if (isInvalid) {
                        //     System.err.println("Counting as invalid: " + num + " , result: " + result);
                        //     result += num;
                        // }

                        String numStr = String.valueOf(num);
                        if (numStr.substring(0, numStr.length() / 2).equals(numStr.substring(numStr.length() / 2))) {
                            result += num;
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
