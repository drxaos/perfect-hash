package org.example;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        final HashSet<Long> hashes = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            final long hash = hash2(greyCode(hash1(greyCode(i))));
            if (!hashes.add(hash)) {
                System.out.println("non unique hash: " + hash);
            }
            System.out.println("" + i + ": " + hash);
        }
        System.out.println("count: " + hashes.size());
    }

    public static long primeHash(long p1, long p2, long p3, long n) {
        return (p2 * n + p3) % p1 + ((n / p1) + 1) * p1;
    }

    public static long hash1(long n) {
        final long p1 = 9181;
        final long p2 = 39667;
        final long p3 = 47059;
        return primeHash(p1, p2, p3, n);
    }

    public static long hash2(long n) {
        final long p1 = 10007;
        final long p2 = 19991;
        final long p3 = 11113;
        return primeHash(p1, p2, p3, n);
    }

    public static long greyCode(long n) {
        return n ^ (n >> 1);
    }
}
