
import java.io.*;


public class Solution_1 {
    public static void main(String[] args) {
        int currPos = 50;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("1\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String dir = line.substring(0, 1);
                int move = Integer.parseInt(line.substring(1).trim());
                
                if (dir.equals("L")) {
                    if (currPos - move < 0) {
                        currPos = 100 + (currPos - move);
                    } else {
                        currPos -= move;
                    }
                } else if (dir.equals("R")) {
                    currPos += move;
                }

                currPos = currPos % 100;
                if (currPos == 0) {
                    result++;
                }
            }
            
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        System.out.println(result);
    }
}