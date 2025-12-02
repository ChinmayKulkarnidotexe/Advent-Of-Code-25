
import java.io.*;


public class Solution_2 {
    public static void main(String[] args) {
        int currPos = 50;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("1\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String dir = line.substring(0, 1);
                int move = Integer.parseInt(line.substring(1).trim());
                
                if (dir.equals("L")) {
                    if (currPos - move <= 0) {
                        result += (-(currPos - move) / 100) + (currPos != 0 ? 1 : 0);
                    }
                    currPos = ((currPos - move) % 100 + 100) % 100;
                } else if (dir.equals("R")) {
                    if (currPos + move >= 100) {
                        result += (currPos + move) / 100;
                    }
                    currPos = (currPos + move) % 100;
                }

            }
            
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        System.out.println(result);
    }
}