import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// link to problem:
// https://www.hackerrank.com/challenges/ctci-making-anagrams/problem

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {

        // create two arrays to store the frequency of
        // each letter of the alphabet in each string
        // each index represents a letter in the alphabet
        int[] aFreq = new int[26];
        int[] bFreq = new int[26];

        // count to return
        int count = 0;

        // populate aFreq
        for (int i = 0; i < a.length(); i++) {
            aFreq[(int) a.charAt(i) - 97]++; // the 97 is just to account for ascii
        }

        // populate bFreq
        for (int i = 0; i < b.length(); i++) {
            bFreq[(int) b.charAt(i) - 97]++;
        }

        // calculate the diff between aFreq and bFreq
        // ie. minimum number of deletions necessary to create anagram
        for (int i = 0; i < aFreq.length; i++) {
            count = count + Math.abs(aFreq[i] - bFreq[i]);
        }

        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
