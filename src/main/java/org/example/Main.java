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

    public static long hash1(long n) {
        return primeHash(9181, 39667, 47059, 0, n);
    }

    public static long hash2(long n) {
        return primeHash(10007, 19991, 11113, 1, n);
    }

    public static long primeHash(long p1, long p2, long p3, long shift, long n) {
        return (p2 * n + p3) % p1 + ((n / p1) + shift) * p1;
    }

    public static long greyCode(long n) {
        return n ^ (n >> 1);
    }
}
