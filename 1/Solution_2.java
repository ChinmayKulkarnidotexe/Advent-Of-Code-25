
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
                    for (int i = 0; i < move; i++) {
                        currPos--;
                        if (currPos < 0) {
                            currPos = 99;
                        }
                        if (currPos == 0) {
                            result++;
                        }
                    }
                } else if (dir.equals("R")) {
                    for (int i = 0; i < move; i++) {
                        currPos++;
                        if (currPos > 99) {
                            currPos = 0;
                        }
                        if (currPos == 0) {
                            result++;
                        }
                    }
                }

                currPos = currPos % 100;

            }
            
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        System.out.println(result);
    }
}