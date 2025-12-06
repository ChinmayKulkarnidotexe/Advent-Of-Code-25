import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution_1 {
    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("6\\input.txt"))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                List<String> row = new ArrayList<>();
                Pattern pattern = Pattern.compile((lineCount <= 4 ? "\\d+" : "\\+|\\*"));
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    row.add(matcher.group());
                }
                input.add(row);
            }
        } catch (IOException e) {
            System.out.println("Error occured while reading file: " + e.getMessage());
        }
        
        // for (List<String> line : input) {
        //     System.out.println(line);
        // }

        long n = input.get(0).size();
        long result = 0;
        for (int i = 0; i < n; i++) {
            long operand1 = Long.parseLong(input.get(0).get(i));
            long operand2 = Long.parseLong(input.get(1).get(i));
            long operand3 = Long.parseLong(input.get(2).get(i));
            long operand4 = Long.parseLong(input.get(3).get(i));
            String operator = input.get(4).get(i);
            if (operator.equals("+")) {
                result += (operand1 + operand2 + operand3 + operand4);
            } else {
                result += (operand1 * operand2 * operand3 * operand4);
            }
        }
        System.out.println("result: " + result);
    }
}
