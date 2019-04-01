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

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256;
    private static final int LG_R = 8;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray sa = new CircularSuffixArray(s);
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (sa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        int lastIndexChar = s.length() - 1;
        for (int i = 0; i < n; i++) {
            int index = (lastIndexChar + sa.index(i)) % n;
            BinaryStdOut.write(s.charAt(index));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        char[] t = BinaryStdIn.readString().toCharArray();
        int n = t.length;
        int[] count = new int[R + 1];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            count[t[i] + 1]++;
        }
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }
        for (int i = 0; i < n; i++) {
            next[count[t[i]]++] = i;
        }

        for (int i = 0; i < n; i++) {
            first = next[first];
            BinaryStdOut.write(t[first]);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();

    }
}
