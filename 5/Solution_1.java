
import java.io.*;
import java.util.*;

public class Solution_1 {
    public static void main(String[] args) {
        Set<String> fresh = new HashSet<>();
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("5\\input.txt"))) {
            String line;
            boolean readingFresh = true;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) { readingFresh = false; continue; }

                if(readingFresh) {
                    fresh.add(line);
                } else {
                    long target = Long.parseLong(line.trim());
                    for (String range : fresh) {
                        String[] parts = range.split("-");
                        long start = Long.parseLong(parts[0]);
                        long end = Long.parseLong(parts[1]);
                        if (target >= start && target <= end) { result++; break; }
                    }
                }
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        System.out.println("result: " + result);
    }
}
