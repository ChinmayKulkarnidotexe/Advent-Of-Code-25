
import java.io.*;
import java.util.*;

public class Solution_2 {

    public static void main(String[] args) {
        Set<String> fresh = new HashSet<>();
        long result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("5\\input.txt"))) {
            String line;
            boolean readingFresh = true;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    readingFresh = false;
                    continue;
                }

                if (readingFresh) {
                    fresh.add(line);
                }
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        long[][] ranges = new long[fresh.size()][2];
        int i = 0;
        for (String range : fresh) {
            String[] parts = range.split("-");
            ranges[i][0] = Long.parseLong(parts[0]);
            ranges[i++][1] = Long.parseLong(parts[1]);
        }

        Arrays.sort(ranges, (long[] row1, long[] row2) -> Long.compare(row1[0], row2[0]));

        List<long[]> merged = new LinkedList<>();
        merged.add(ranges[0]);
        for (int j = 1; j < ranges.length; j++) {
            long[] curr = ranges[j];
            long[] prev = merged.getLast();

            if (curr[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                merged.add(curr);
            }
        }

        for (long[] range : merged) {
            long start = range[0];
            long end = range[1];
            result += (end - start + 1);
        }
        System.out.println("result: " + result);
    }
}
