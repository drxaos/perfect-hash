package org.example;

import java.util.HashSet;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        final HashSet<Long> hashes = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            final long hash = hash2(greyEncode(hash1(greyEncode(i))));
            final long unhash = greyDecode(unhash1(greyDecode(unhash2(hash))));
            if (!hashes.add(hash)) {
                System.out.println("non unique hash: " + hash);
            }
            if (i != unhash) {
                System.out.println("wrong unhash: " + i + ", " + hash + ", " + unhash);
            }
            System.out.println("" + i + ": " + hash + " - " + unhash);
        }
        System.out.println("count: " + hashes.size());
    }

    public static long hash1(long n) {
        return primeHash(9181, 39667, 47059, 0, n);
    }

    public static long unhash1(long n) {
        return primeUnhash(9181, 39667, 47059, 0, n);
    }

    public static long hash2(long n) {
        return primeHash(10007, 19991, 11113, 1, n);
    }

    public static long unhash2(long n) {
        return primeUnhash(10007, 19991, 11113, 1, n);
    }

    public static long primeHash(long p1, long p2, long p3, long shift, long n) {
        final long lower = (p2 * n + p3) % p1;
        final long higher = ((n / p1) + shift) * p1;
        return lower + higher;
    }

    public static long primeUnhash(long p1, long p2, long p3, long shift, long n) {
        final long higher = (n / p1 - shift) * p1;
        final long lower = LongStream.range(0, p1).filter(l -> (p2 * l + p3) % p1 == n % p1).findAny().getAsLong();
        return lower + higher;
    }

    public static long greyEncode(long n) {
        return n ^ (n >> 1);
    }

    public static long greyDecode(long n) {
        long n2 = n;
        while ((n /= 2) > 0) {
            n2 ^= n;
        }
        return n2;
    }
}
