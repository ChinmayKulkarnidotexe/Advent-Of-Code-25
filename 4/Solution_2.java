
import java.io.*;
import java.util.*;

public class Solution_2 {

    public static void main(String[] args) {
        ArrayList<List<Character>> map = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("4\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Character> temp = new ArrayList<>();
                for (char ch : line.toCharArray()) {
                    temp.add(ch);
                }
                map.add(temp);
            }
        } catch (IOException e) {
            System.out.println("An error occured while reading from file: " + e.getMessage());
        }

        int rows = map.size();
        int cols = map.get(0).size();

        int result = 0;
        int temp = 0;
        while (temp <= 9990) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (map.get(i).get(j) == '.') continue;
    
                    int count = countAdjacent(i, j, map);
                    if (count < 4) {
                        result++;
                        map.get(i).set(j, '.');
                    }
                }
            }
            temp++;
        }
        System.out.println("Final Result: " + result);
    }

    private static int countAdjacent(int i, int j, ArrayList<List<Character>> map) {
        int count = 0;
        int[][] directions = new int[][]{
            {1, 0}, //bottom
            {1, 1}, //bottom-right
            {0, 1}, //right
            {-1, 1}, //top-right
            {-1, 0}, //top
            {-1, -1}, //top-left
            {0, -1}, //left
            {1, -1} //bottom-left
        };
        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (isWithinBounds(newX, newY, map.size(), map.get(0).size())
                    && map.get(newX).get(newY) == '@') {
                count++;
            }
        }

        return count;
    }

    private static boolean isWithinBounds(int i, int j, int rows, int cols) {
        return (i >= 0 && i < cols && j >= 0 && j < cols);
    }
}
