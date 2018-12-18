import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

// link to problem:
// https://www.hackerrank.com/challenges/frequency-queries/problem

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<int[]> queries) {

        // hashmap representing the "data structure" from the problem
        // key: element in data structure
        // value: frequency of that element in the data structure
        HashMap<Integer, Integer> dataStructure = new HashMap<>();

        // hashmap representing the "frequency of frequencies" in the data structure
        // key: some frequency
        // value: number of elements in the data structure that have this frequency
        HashMap<Integer, Integer> frequencies = new HashMap<>();

        // list to return
        List<Integer> output = new LinkedList<>();

        for (int[] query : queries) {

            // split query into operation and element
            int operation = query[0];
            int element = query[1];

            switch (operation) {

                // operation 1: insert element into data structure
                case 1:
                    int oldFreq;
                    int newFreq;

                    // update data structure with new frequency
                    if (dataStructure.containsKey(element)) {
                        oldFreq = dataStructure.get(element);
                        newFreq = oldFreq + 1;
                        dataStructure.put(element, newFreq);
                    } else {
                        oldFreq = 0;
                        newFreq = 1;
                        dataStructure.put(element, 1);
                    }

                    // update frequencies map with new frequency of frequency
                    if (frequencies.containsKey(newFreq)) {
                        frequencies.put(newFreq, frequencies.get(newFreq) + 1);
                    } else {
                        frequencies.put(newFreq, 1);
                    }
                    if (frequencies.containsKey(oldFreq)) {
                        frequencies.put(oldFreq, frequencies.get(oldFreq) - 1);
                    }
                    break;

                // operation 2: if present, remove one occurrence of element from data structure
                case 2:
                    if (dataStructure.containsKey(element) && dataStructure.get(element) > 0) {

                        // update data structure with new frequency
                        oldFreq = dataStructure.get(element);
                        newFreq = oldFreq - 1;
                        dataStructure.put(element, newFreq);

                        // update frequencies map with new frequency of frequency
                        if (frequencies.containsKey(newFreq)) {
                            frequencies.put(newFreq, frequencies.get(newFreq) + 1);
                        } else {
                            frequencies.put(newFreq, 1);
                        }
                        if (frequencies.containsKey(oldFreq)) {
                            frequencies.put(oldFreq, frequencies.get(oldFreq) - 1);
                        }

                    }
                    break;

                // operation 3: check if any element in data structure has the exact
                // frequency given by the query, and output accordingly
                case 3:
                    if (frequencies.containsKey(element)) {
                        if (frequencies.get(element) > 0) {
                            output.add(1);
                        }
                        else {
                            output.add(0);
                        }
                    } else {
                        output.add(0);
                    }
                    break;

                // default case unused
                default:
                    break;

            }
        }

        return output;

    }

    // this is not the original boilerplate code, this is a modified version
    // taken from the comments because the original boilerplate code
    // was too slow, even with an efficient solution implementation
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery(queries);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bufferedWriter.write(ans.stream().map(Object::toString).collect(Collectors.joining("\n")) + "\n");
            }
        }
    }
}