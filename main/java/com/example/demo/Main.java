package com.example.demo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Number 1: Print Digits of a Number");
        printDigits(5481);

        System.out.println("\n Number 2: Average of Elements");
        int[] arr2 = {3, 2, 4, 1};
        System.out.println("Output: " + getAverage(arr2, 4));

        System.out.println("\n Number 3: Prime Number Check");
        System.out.println("Output: " + (isPrime(7, 2) ? "Prime" : "Composite"));

        System.out.println("\n Number 4: Factorial");
        System.out.println("Output: " + factorial(5));

        System.out.println("\n Number 5: Fibonacci Number");
        System.out.println("Output: " + fibonacci(17));

        System.out.println("\n Number 6: Power Function");
        System.out.println("Output: " + power(2, 10));

        System.out.println("\n Number 8: Check Digits in String");
        System.out.println("Output: " + isAllDigits("123a12"));

        System.out.println("\n Number 9: Count Characters in a String");
        System.out.println("Output: " + countChars("recursion"));

        System.out.println("\n Number 10: Greatest Common Divisor");
        System.out.println("Output: " + gcd(32, 48));

        System.out.println("\n Number 7: Reverse Output");
        reverse(scanner, 4);
    }

    public static void printDigits(int n) {
        if (n < 10) { System.out.println(n); return; }
        printDigits(n / 10);
        System.out.println(n % 10);
    }

    public static double getAverage(int[] arr, int n) {
        if (n == 0) return 0;
        return (double) sum(arr, n) / n;
    }
    private static int sum(int[] arr, int n) {
        if (n <= 0) return 0;
        return sum(arr, n - 1) + arr[n - 1];
    }

    public static boolean isPrime(int n, int i) {
        if (n <= 2) return n == 2;
        if (n % i == 0) return false;
        if (i * i > n) return true;
        return isPrime(n, i + 1);
    }

    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int power(int a, int n) {
        if (n == 0) return 1;
        return a * power(a, n - 1);
    }

    public static void reverse(Scanner sc, int n) {
        if (n == 0) return;
        int x = sc.nextInt();
        reverse(sc, n - 1);
        System.out.print(x + " ");
    }

    public static String isAllDigits(String s) {
        if (s.isEmpty()) return "Yes";
        if (!Character.isDigit(s.charAt(0))) return "No";
        return isAllDigits(s.substring(1));
    }

    public static int countChars(String s) {
        if (s.isEmpty()) return 0;
        return 1 + countChars(s.substring(1));
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}