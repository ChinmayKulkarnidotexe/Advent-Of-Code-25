import java.io.*;
import java.util.*;

public class Solution_2 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("6\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error occured while reading file: " + e.getMessage());
        }
        int n = input.get(0).length();
        List<Integer> nums = new ArrayList<>();
        long result = 0;
        for (int i = n - 1; i >= 0; i--) {
            char op = input.get(4).charAt(i);
            if (op == '+' || op == '*') {
                String currNum = input.get(0).charAt(i) + "" + input.get(1).charAt(i) + input.get(2).charAt(i) + "" + input.get(3).charAt(i) + "";
                if (currNum.length() > 0) {
                    nums.add((Integer)Integer.parseInt(currNum.trim()));
                }

                long temp = 0;
                if (op == '+') {
                    for (int num : nums){ 
                        temp += num;
                    }
                    result += temp;
                } else {
                    temp = 1;
                    for (int num : nums) {
                        temp *= num;
                    }
                    result += temp;
                }
                nums.clear();
                i--;
            } else {
                String currNum = input.get(0).charAt(i) + "" + input.get(1).charAt(i) + input.get(2).charAt(i) + "" + input.get(3).charAt(i) + "";
                if (currNum.length() > 0) {
                    nums.add((Integer)Integer.parseInt(currNum.trim()));
                }
            }
        }
        System.out.println("Result: " + result);
    }
}
