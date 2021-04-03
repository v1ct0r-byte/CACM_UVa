
package es.upv.etsinf.strings;

import java.util.*;

public class StringSearch
{
    private final static int ALPHABET_SIZE=128;
    private final static int ALPHABET_MASK=255;

    private static int charToInt( char c )
    {
        return (int)c & ALPHABET_MASK;
    }
    public static int directSearchFirst( String s, String p )
    {
        int ls = s.length(),
            lp = p.length();

        int upper_limit = ls - lp;

        for (int i = 0; i <= upper_limit; i++) {
            int j = 0;
            while (j < lp && s.charAt(i+j) == p.charAt(j)) j++;
            if (j == lp) return i;
        }
        return -1;
    }
    public static ArrayList<Integer> directSearch(String s, String p)
    {
        int ls = s.length(),
            lp = p.length();

        int upper_limit = ls - lp;

        ArrayList<Integer> l = new ArrayList<Integer>();

        for (int i = 0; i <= upper_limit; i++) {
            int j = 0;
            while(j < lp && s.charAt(i+j) == p.charAt(j)) j++;
            if (j == lp) l.add(i);
        }
        return l;
    }
    public static int boyerMooreSearchSimpleFirst( String s, String p )
    {
        int [] d = new int [ALPHABET_SIZE];
        int ls = s.length(),
            lp = p.length();

        for (int i = 0; i < d.length; i++) d[i] = lp;

        for (int i = 0; i < lp-1; i++) d[charToInt(p.charAt(i))] = lp - i - 1;

        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) return k+1;
            i = i + d[charToInt(s.charAt(i))]; // The key is to use s[i] instead of s[k]
        } while(i < ls);

        return -1;
    }
    public static ArrayList<Integer> boyerMooreSearchSimple(String s, String p)
    {
        int [] d = new int [ALPHABET_SIZE];
        int ls = s.length(),
            lp = p.length();

        for (int i = 0; i < d.length; i++) d[i] = lp;

        for (int i = 0; i < lp-1; i++) d[charToInt(p.charAt(i))] = lp - i - 1;

        ArrayList<Integer> l = new ArrayList<Integer>();

        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) {
                l.add(k + 1); // pattern found at position i-lp+1 = k+1 because k = i-lp
                i++;
            } else {
                i = i + d[charToInt(s.charAt(i))]; // The key is to use s[i] instead of s[k]
            }
        } while(i < ls);

        return l;
    }
    public static int [][] badCharShiftRule(String p)
    {
        int [][] d = new int [ALPHABET_SIZE][p.length()];

        for (int i = 0; i < d.length; i++) {
            int k = 1; // a non-matching symbol at first position in the pattern must shift the pattern on the text one position
            for (int j = 0; j < p.length(); ++j) {
                if (charToInt(p.charAt(j)) == i) {
                    // if a non-matching symbol t position j in the pattern exists in another position of the pattern then it must move 
                    d[i][j] = k = 1;
                } else {
                    // a non-matching symbol at position j in the pattern must shift the pattern on the text one position additional to the one so far
                    d[i][j] = k++;
                }
            }
        }

        return d;
    }
    public static int boyerMooreSearchFirst(String s, String p)
    {
        int [][] d = badCharShiftRule(p);
        int ls = s.length(),
            lp = p.length();

        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) return k+1; // pattern found at position i-lp+1 = k+1 because k = i-lp
            i = i + d[charToInt(s.charAt(k))][j];
        } while( i < ls );

        return -1;
    }
    public static ArrayList<Integer> boyerMooreSearch(String s, String p)
    {
        int [][] d = badCharShiftRule(p);
        int ls = s.length(),
            lp = p.length();

        ArrayList<Integer> l = new ArrayList<Integer>();

        // starting at position lp-1 means the pattern is aligned with the
        // first lp symbols of the text at the beggining of the search
        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) {
                l.add(k+1); // pattern found at position i-lp+1 = k+1 because k = i-lp
                i++;
            } else {
                // move forward the maximum number of positions without loosing any occurrence of the pattern
                i = i + d[charToInt(s.charAt(k))][j];
            }
        } while (i < ls);

        return l;
    }
    /*
        s: aebbb
        p: ebb

        Este ejemplo debe funcionar
    */

    public static void main(String [] args)
    {
        for (int i = 1; i < args.length; i++) {
            System.out.printf("%5d %5d %5d\n",  directSearchFirst(args[i], args[0]),
                                                boyerMooreSearchSimpleFirst(args[i], args[0]),
                                                boyerMooreSearchFirst(args[i], args[0]));
        }
    }
}
