import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        // hashmap to keep track of numbers wanted for the third number in a triplet
        // key: number wanted
        // value: how many are wanted
        HashMap<Long, Long> thirdNeeded = new HashMap<>();

        // hashmap to keep track of numbers wanted for the second number in a triplet
        // key: number wanted
        // value: how many are wanted
        HashMap<Long, Long> secondNeeded = new HashMap<>();

        // triplet count to increment and return
        long count = 0;

        // iterate through the list
        for (long current : arr) {

            // if the current number is wanted for the third number of a triplet,
            // add the amount of the current number wanted to the triplet count
            if (thirdNeeded.containsKey(current)) {
                count += thirdNeeded.get(current);
            }

            // if the current number is wanted for the second number of a triplet:
            if (secondNeeded.containsKey(current)) {

                // we know that we have at least 1 pair that could form a potential triplet
                // so we calculate the number we want for forming a triplet (third number), and
                // place it into the hashmap as the key with value being the number of pairs we have
                if (thirdNeeded.containsKey(current * r)) {
                    thirdNeeded.put(current * r, thirdNeeded.get(current * r) + secondNeeded.get(current));
                } else {
                    thirdNeeded.put(current * r, secondNeeded.get(current));
                }
            }

            // now we treat the current number as the first number in a potential triplet
            // we calculate the number we want as the second number, and then
            // increment the value in the secondNeeded hashmap accordingly
            if (secondNeeded.containsKey(current * r)) {
                secondNeeded.put(current * r, secondNeeded.get(current * r) + 1);
            } else {
                secondNeeded.put(current * r, (long) 1);
            }
        }

        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr.add(arrItem);
        }

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
