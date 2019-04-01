/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Partner Name:    Ada Lovelace
 *  Partner NetID:   alovelace
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import java.util.Arrays;

public class CircularSuffixArray {
    // circular suffix array of s
    private String s;
    private CircularSuffix[] suffixes;

    private class CircularSuffix implements Comparable<CircularSuffix> {
        private String s;
        private int n; // length of string s
        private int offset; // offset of string
        private int index; // index of this suffix in the original array

        public CircularSuffix(String s, int offset, int index) {
            if (offset >= s.length())
                throw new IllegalArgumentException("offset should be less than string length");
            this.s = s;
            this.n = s.length();
            this.offset = offset;
            this.index = index;
        }

        public int compareTo(CircularSuffix that) {
            // assume this and that has same s
            int thisIndex = this.offset;
            int thatIndex = that.offset;
            int cmp = 0;
            for (int i = 0; i < n; i++) {
                cmp = s.charAt(thisIndex++) - s.charAt(thatIndex++);
                if (cmp != 0) return cmp;
                if (thisIndex >= n) thisIndex = 0;
                if (thatIndex >= n) thatIndex = 0;
            }
            return cmp;

        }

        public String toString() {
            // for testing only. it's not efficent
            String res = "";
            for (int i = 0, j = offset; i < n; i++, j = (j + 1) % n) {
                res += s.charAt(j);
            }
            return res;
        }
    }

    public CircularSuffixArray(String s) {
        this.s = s;
        suffixes = new CircularSuffix[s.length()];
        for (int i = 0; i < s.length(); i++) {
            suffixes[i] = new CircularSuffix(s, i, i);
        }
        Arrays.sort(suffixes);
    }

    // length of s
    public int length() {
        return s.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        return this.suffixes[i].index;
    }

    public String toString() {
        // for testing only. it's not efficent
        String res = "";
        for (CircularSuffix suffix : suffixes) {
            res += suffix.toString() + "\n";
        }
        return res;
    }

    public static void main(String[] args) {
        // String s = "ABRACADABRA!";
        // CircularSuffixArray arr = new CircularSuffixArray(s);
        // System.out.println(arr);
        // for (int i = 0; i < s.length(); i++) {
        //     System.out.println(arr.index(i));
        // }
    }
}
