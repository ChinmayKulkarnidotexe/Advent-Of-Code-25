
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution_1 {

    public static void main(String[] args) {
        List<List<Long>> input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("8\\test.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                List<Long> nums = Arrays.stream(parts)
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
                input.add(nums);
            }
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }

        int n = input.size();
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> Long.compare(b[0], a[0]));
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<Long> nums1 = input.get(i);
                List<Long> nums2 = input.get(j);

                long diff1 = nums1.get(0) - nums2.get(0);
                long diff2 = nums1.get(1) - nums2.get(1);
                long diff3 = nums1.get(2) - nums2.get(2);

                long diff = diff1*diff1 + diff2*diff2 + diff3*diff3;;

                if (pq.size() < n) {
                    pq.offer(new long[]{diff, i, j});
                } else {
                    if (diff < pq.peek()[0]) {
                        pq.poll();
                        pq.offer(new long[]{diff, i, j});
                    }
                }
            }
        }

        List<long[]> pairs = new ArrayList<>();
        for (long[] p : pq) {
            pairs.add(p);
        }

        Collections.sort(pairs, (a, b) -> Long.compare(a[0], b[0]));

        int[] parents = new int[n];
        List<Set<Integer>> circuits = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> temp = new HashSet<>();
            temp.add(i);
            circuits.add(temp);

            parents[i] = i;
        }

        for (long[] pair : pairs) {
            union((int)pair[1], (int)pair[2], parents, circuits);
        }

        Map<Integer, Integer> sizes = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = find(i, parents);
            sizes.put(root, sizes.getOrDefault(root, 0) + 1);
        }

        List<Integer> values = new ArrayList<>(sizes.values());
        Collections.sort(values, Comparator.reverseOrder());

        
        int first = values.get(0);
        int second = values.get(1);
        int third = values.get(2);
        int result = first * second * third;
        System.out.println("Top 3: " + first + " , " + second + " , " + third + " , result: " + result);

        
    }

    private static int find(int x, int[] parents)  {
        while (parents[x] != x) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    private static void union(int x, int y, int[] parents, List<Set<Integer>> circuits) {
        int rx = find(x, parents);
        int ry = find(y, parents);
        if (rx == ry) return;

        if(circuits.get(rx).size() < circuits.get(ry).size()) {
            int temp = rx;
            rx = ry;
            ry = temp;
        }

        parents[ry] = rx;

        circuits.get(rx).addAll(circuits.get(ry));
        circuits.get(ry).clear();
    }
}

// the below could is supposed to be left untouched
// its broken code which was written by my sacred hands
// :)

// for(List<Long> row : input) {
        //     System.out.println(row);
        // }
    //     TreeMap<Long, List<List<Long>>> diffs = new TreeMap<>();
    //     int n = input.size();
    //     for (int i = 0; i < n; i++) {
    //         for (int j = i + 1; j < n; j++) {
    //             List<Long> nums1 = input.get(i);
    //             List<Long> nums2 = input.get(j);
    //             long diff1 = nums1.get(0) - nums2.get(0);
    //             long diff2 = nums1.get(1) - nums2.get(1);
    //             long diff3 = nums1.get(2) - nums2.get(2);
    //             long dist = (long) Math.sqrt(Math.pow(diff1, 2) + Math.pow(diff2, 2) + Math.pow(diff3, 2));

    //             List<List<Long>> temp = diffs.getOrDefault(dist, new ArrayList<>());
    //             temp.add(nums1);
    //             temp.add(nums2);
    //             diffs.put(dist, temp);
    //         }
    //     }

    //     // while (!diffs.isEmpty()) {
    //     //     System.err.println(diffs.pollFirstEntry());
    //     // }
    //     // we have processed all pairs distances, now to create circuits and find top 3
    //     //initialize parents[i] = i
    //     Map<String, String> parents = new HashMap<>();
    //     for (List<Long> coord : input) {
    //         String str = coord.get(0) + "," + coord.get(1) + "," + coord.get(2);
    //         parents.put(str, str);
    //     }

    //     Set<String> seen = new HashSet<>();
    //     Map<String, Set<String>> graph = new HashMap<>();

    //     for (int k = 0; k < 20; k++) {
    //         Map.Entry<Long, List<List<Long>>> entry = diffs.pollFirstEntry();
    //         List<List<Long>> pairs = entry.getValue();
    //         System.out.println("\n--------------------------\ncurr entry from diffs:" + entry);
    //         int len = pairs.size();
    //         if (len % 2 != 0) {
    //             System.out.println("invalid pairs length found for entry: " + entry + " , exiting...");
    //             System.exit(0);
    //         }

    //         for (int i = 0; i < len; i += 2) {
    //             List<Long> nums1 = pairs.get(i);
    //             List<Long> nums2 = pairs.get(i + 1);
    //             String str1 = nums1.get(0) + "," + nums1.get(1) + "," + nums1.get(2);
    //             String str2 = nums2.get(0) + "," + nums2.get(1) + "," + nums2.get(2);

    //             System.out.println("str1: " + str1 + " , str2: " + str2);
    //             if (seen.contains(str1) && seen.contains(str2)) {
    //                 System.out.println("both str1 and str2 already in seen");
    //                 continue;
    //             }

    //             if (seen.contains(str1)) {
    //                 union(str1, str2, parents);
    //                 seen.add(str2);
    //                 System.out.println("calling union(str1,str2) and adding str2 to seen");
    //             } else if (seen.contains(str2)) {
    //                 union(str2, str1, parents);
    //                 seen.add(str1);
    //                 System.out.println("calling union(str2,str1) and adding str1 to seen");
    //             } else {
    //                 union(str1, str2, parents);
    //                 seen.add(str1);
    //                 seen.add(str2);
    //                 System.out.println("calling union(str1,str2) and both to seen");
    //             }

    //             constructGraph(parents, graph);

    //             // System.err.println("Parents:");
    //             // for (Map.Entry<String, String> temp : parents.entrySet()) {
    //             //     System.out.println(temp);
    //             // }
    //             System.out.println("----------------");
    //             System.out.println("graph for entry: " + entry + " i: " + i);
    //             for (Map.Entry<String, Set<String>> temp : graph.entrySet()) {
    //                 System.out.println(temp);
    //             }

    //             // if (getCircuitCount(graph) >= 10) {
    //             //     System.out.println("Graph size reached 10, exiting...");
    //             //     System.exit(1);
    //             // }
    //         }
    //     }
    //     // Map<Integer, List<String>> nums = new HashMap<>();
    //     // Map<String, Integer> existingNums = new HashMap<>();
    //     // Set<Integer> assignedCircuits = new HashSet<>();
    //     // while (!diffs.isEmpty()) {
    //     //     Map.Entry<Long, List<Long>> entry = diffs.pollFirstEntry();
    //     //     List<Long> n1 = entry.getValue().subList(0, 3);
    //     //     List<Long> n2 = entry.getValue().subList(3, 6);
    //     //     String s1 = n1.get(0) + "," + n1.get(1) + "," + n1.get(2);
    //     //     String s2 = n2.get(0) + "," + n2.get(1) + "," + n2.get(2);
    //     //     if (!(existingNums.containsKey(s1) && existingNums.containsKey(s2))) {
    //     //         if (existingNums.containsKey(s1)) {
    //     //             int circuit = existingNums.get(s1);
    //     //             List<String> list = nums.get(circuit);
    //     //             if (!list.contains(s2)) {
    //     //                 list.add(s2);
    //     //                 existingNums.put(s2, circuit);
    //     //             }
    //     //             System.out.println("Only adding 2nd pair for entry: " + entry
    //     //                     + ", nums for circuit: " + circuit + " : " + nums.get(circuit));
    //     //         } else if (existingNums.containsKey(s2)) {
    //     //             int circuit = existingNums.get(s2);
    //     //             List<String> list = nums.get(circuit);
    //     //             if (!list.contains(s1)) {
    //     //                 list.add(s1);
    //     //                 existingNums.put(s1, circuit);
    //     //             }
    //     //             System.out.println("Only adding 1st pair for entry: " + entry
    //     //                     + ", nums for circuit: " + circuit + " : " + nums.get(circuit));
    //     //         } else {
    //     //             int circuit = getNextCircuit(assignedCircuits, n);
    //     //             assignedCircuits.add(circuit);
    //     //             List<String> list = new ArrayList<>();
    //     //             list.add(s1);
    //     //             list.add(s2);
    //     //             nums.put(circuit, list);
    //     //             existingNums.put(s1, circuit);
    //     //             existingNums.put(s2, circuit);
    //     //             System.out.println("Adding both pairs from entry: " + entry
    //     //                     + " , nums for circuit: " + circuit + " : " + nums.get(circuit));
    //     //         }
    //     //     } else {
    //     //         System.err.println("both pairs already exist in nums from entry:" + entry);
    //     //     }
    //     // }
    //     // System.out.println("Final nums:");
    //     // for (Map.Entry<Integer, List<String>> entry : nums.entrySet()) {
    //     //     System.out.println(entry);
    //     // }



    

    // // private static int getNextCircuit(Set<Integer> assignedCircuits, int max) {
    // //     for (int i = 1; i <= max; i++) {
    // //         if (!assignedCircuits.contains(i)) {
    // //             System.out.println("returning next circuit as: " + i);
    // //             return i;
    // //         }
    // //     }
    // //     return -1;
    // // }

    // // private static int getCircuitCount(Map<String, List<String>> graph) {
    // //     int count = 0;
    // //     for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
    // //         if (entry.getValue().size() >= 2) count++;
    // //     }
    // //     return count;
    // // }

    // private static void constructGraph(Map<String, String> parents, Map<String, Set<String>> graph) {
    //     for (Map.Entry<String, String> entry : parents.entrySet()) {
    //         String key = entry.getKey();
    //         String val = entry.getValue();
    //         if (key.equals(val)) continue;
    //         Set<String> temp = graph.getOrDefault(val, new HashSet<>());
    //         temp.add(val);
    //         temp.add(key);
    //         graph.put(val, temp);
    //     }
    // }

    // private static String find(String x, Map<String, String> parents) {
    //     if (parents.get(x).equals(x)) {
    //         return x; 
    //     }else {
    //         return find(parents.get(x), parents);
    //     }
    // }

    // private static void union(String x, String y, Map<String, String> parents) {
    //     parents.put(find(y, parents), find(x, parents));
    // }