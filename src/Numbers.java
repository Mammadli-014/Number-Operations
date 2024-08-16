import java.util.ArrayList;

public class Numbers {
    ArrayList<Integer> primeNumbers;

    // Method to calculate the factorial of a number
    int factorial(int number){
        if (number == 0 || number == 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    // Initialization block to populate the list of prime numbers up to 100
    {
        primeNumbers = new ArrayList<>();
        for (int i = 2; i <= 100; i++) {
            boolean isPrime = true;
            int a = 2;
            while (a <= i) {
                if (i % a == 0 && (a != i)) {
                    isPrime = false;
                    break;
                }
                a++;
            }
            if (isPrime) primeNumbers.add(i);
        }
    }

    // Method to find the prime factors of a given number
    ArrayList<Integer> findPrimeFactors(int number) {
        ArrayList<Integer> primeFactors = new ArrayList<>();
        LOOP:
        while (true) {
            for (int i = 0; ; i++) {
                if (number % primeNumbers.get(i) == 0) {
                    primeFactors.add(primeNumbers.get(i));
                    number /= primeNumbers.get(i);
                    continue LOOP;
                }
                if (number == 1) break LOOP;
            }
        }
        return primeFactors;
    }

    // Method to find the greatest common divisor (GCD) of two numbers
    int findGCD(int firstNumber, int secondNumber) {
        ArrayList<Integer> firstPrimes = findPrimeFactors(firstNumber);
        ArrayList<Integer> secondPrimes = findPrimeFactors(secondNumber);
        ArrayList<Integer> commonFactors = new ArrayList<>();
        int gcd = 1;

        LOOP:
        while (true) {
            if (firstPrimes.isEmpty()) break;
            for (int i : firstPrimes) {
                for (int j : secondPrimes) {
                    if (i == j) {
                        commonFactors.add(i);
                        firstPrimes.remove((Integer) i);
                        secondPrimes.remove((Integer) j);
                        continue LOOP;
                    }
                }
                if (i == firstPrimes.get(firstPrimes.size() - 1)) break LOOP;
            }
        }
        for (int factor : commonFactors) gcd *= factor;
        return gcd;
    }

    // Method to find the least common multiple (LCM) of two numbers
    int findLCM(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber / findGCD(firstNumber, secondNumber);
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        Numbers numbers = new Numbers();
        System.out.println(numbers.findGCD(125, 60)); // Outputs the GCD of 125 and 60
        System.out.println(numbers.findLCM(20, 10));  // Outputs the LCM of 20 and 10
    }
}
