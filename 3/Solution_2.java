
import java.io.*;

public class Solution_2 {
    public static void main(String[] args) {
        long result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("3\\input.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                int n = line.length();
                StringBuilder sb = new StringBuilder();

                int currLen = 12;
                int start = 0;
                while (currLen > 0) {
                    int end = n -  currLen + 1;
                    int maxVal = 0;
                    int maxIdx = start;
                    for(int i = start; i < end; i++) {
                        int currVal = Integer.parseInt(line.charAt(i) + "");
                        if (currVal > maxVal) {
                            maxVal = currVal;
                            maxIdx = i;
                        }
                    }

                    sb.append(maxVal);
                    start = maxIdx + 1;
                    currLen--;

                }
                result += Long.parseLong(sb.toString());
            }
        } catch (IOException e) {
            System.out.println("Error occured when reading file: " + e.getMessage());
        }
        System.out.println("Result: " + result);
    }
}


// import java.io.*;

// public class Solution_2 {
//     public static void main(String[] args) {
//         long result = 0;
//         try (BufferedReader reader = new BufferedReader(new FileReader("3\\test.txt"))) {
//             String line;
//             while((line = reader.readLine()) != null) {
//                 long maxSeen = 0;
//                 int n = line.length();
//                 for (int i = 0; i < n; i++) {
//                     String curr = line.charAt(i) + "";
//                     for(int j = i + 1; j < n; j++) {
//                         String next1 = line.charAt(j) + "";
//                         for(int a = j + 1; a < n; a++) {
//                             String next2 = line.charAt(a) + "";
//                             for(int b = a + 1; b < n; b++) {
//                                 String next3 = line.charAt(b) + "";
//                                 for(int c = b + 1; c < n; c++) {
//                                     String next4 = line.charAt(c) + "";
//                                     for(int d = c + 1; d < n; d++) {
//                                         String next5 = line.charAt(d) + "";
//                                         for(int e = d + 1; e < n; e++) {
//                                             String next6 = line.charAt(e) + "";
//                                             for(int f = e + 1; f < n; f++) {
//                                                 String next7 = line.charAt(f) + "";
//                                                 for(int g = f + 1; g < n; g++) {
//                                                     String next8 = line.charAt(g) + "";
//                                                     for(int h = g + 1; h < n; h++) {
//                                                         String next9 = line.charAt(h) + "";
//                                                         for(int k = h + 1; k < n; k++) {
//                                                             String next10 = line.charAt(k) + "";
//                                                             for(int m = k + 1; m < n; m++) {
//                                                                 String next11 = line.charAt(m) + "";
//                                                                 maxSeen = Math.max(Long.parseLong(curr + next1 + next2 + next3 + next4 + next5 + next6 + next7 + next8 + next9 + next10 + next11), maxSeen);
//                                                             }
//                                                         }
//                                                     }
//                                                 }
//                                             }
//                                         }
//                                     }
//                                 }
//                             }
//                         }
//                     }
//                 }
//                 System.out.println("maxSeen: "+ maxSeen);
//                 result += maxSeen;
//                 System.out.println("result: " + result);
//             }
//         } catch (IOException e) {
//             System.out.println("Error occured when reading file: " + e.getMessage());
//         }
//         System.out.println("Final Result: " + result);
//     }
// }