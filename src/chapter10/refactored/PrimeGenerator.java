package chapter10.refactored;

import java.util.ArrayList;

public class PrimeGenerator {
    private int[] primes;

    private ArrayList<Integer> multiplesOfPrimeFactors;

    public static int[] generate(int n) {
        return new PrimeGenerator(n).generatePrimes();
    }

    private PrimeGenerator(int n) {
        primes = new int[n];
        multiplesOfPrimeFactors = new ArrayList<>();
    }

    private int[] generatePrimes() {
        set2AsFirstPrime();
        checkOddNumbersForSubsequentPrimes();
        return primes;
    }

    private void set2AsFirstPrime() {
        primes[0] = 2;
        multiplesOfPrimeFactors.add(2);
    }

    private void checkOddNumbersForSubsequentPrimes() {
        int primeIndex = 1;
        for (int candidate = 3; primeIndex < primes.length; candidate += 2) {
            if (isPrime(candidate)) primes[primeIndex++] = candidate;
        }
    }

    private boolean isPrime(int candidate) {
        if (isLeastRelevantMultipleOfNextLargerPrimeFactor(candidate)) {
            multiplesOfPrimeFactors.add(candidate);
            return false;
        }
        return isNotMultipleOfAnyPreviousPrimeFactor(candidate);
    }

    private boolean isLeastRelevantMultipleOfNextLargerPrimeFactor(int candidate) {
        int nextLargerPrimeFactor = primes[multiplesOfPrimeFactors.size()];
        int leastRelevantMultiple = nextLargerPrimeFactor * nextLargerPrimeFactor;
        return candidate == leastRelevantMultiple;
    }

    private boolean isNotMultipleOfAnyPreviousPrimeFactor(int candidate) {
        for (int n = 1; n < multiplesOfPrimeFactors.size(); ++n) {
            if (isMultipleOfNthPrimeFactor(candidate, n)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMultipleOfNthPrimeFactor(int candidate, int n) {
        return candidate == smallestOddNthMultipleNotLessThanCandidate(candidate, n);
    }

    private int smallestOddNthMultipleNotLessThanCandidate(int candidate, int n) {
        int multiple = multiplesOfPrimeFactors.get(n);
        while (multiple < candidate) multiple += 2 * primes[n];
        multiplesOfPrimeFactors.set(n, multiple);
        return multiple;
    }

    public static void main(String[] args) {
        int[] hh = new int[6];
        hh[0] = 1;
        System.out.println(hh[1]);
    }
}
