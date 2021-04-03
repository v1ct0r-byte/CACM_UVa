import java.util.*;
import java.math.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //https://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
        //Compute erathostenes sieve
        int numSieve = 65000;

        // initially we assume all integers are prime
        boolean[] isPrime = new boolean[numSieve+1];
        for (int i = 2; i <= numSieve; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= numSieve; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= numSieve; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }

        while(true){
            int n = Integer.parseInt(scanner.nextLine());
            if(n == 0){
                break;
            }
            
            //If it is in eratosthenes sieve table then it is normal
            if(isPrime[n]){
                System.out.println(n + " is normal.");
            }else{
                //if not we check if it is a carmichael number
                if(isCarmichael(n)){
                    System.out.println("The number " + n + " is a Carmichael number.");
                }else{
                    System.out.println(n + " is normal.");
                }
            }
        }
    }

    private static boolean isCarmichael(int n){
        for(int a = 2; a < n;a++){
            //If a is relative prime to n
            if(gcd(a, n) == 1){
                //we check if fermat primality test is not acomplished
                if(modPow(a, n-1, n) != 1){
                    return false;
                }
            }
        }
        //when all numbers < n pass fermat primality test it is a carmichael number
        return true;
    }

    private static int modPow(int x, int y, int n){
        //this gives TLE in online judge
        /*int ans = 1;
        for(int i = 0; i < y; i++){
            ans *= x;
            ans %= n;
        }
        return ans % n;
        */
        return BigInteger.valueOf(x).modPow(BigInteger.valueOf(y),BigInteger.valueOf(n)).intValue();
    }

    static int gcd(int a, int b) 
    { 
        if (a < b) 
            return gcd(b, a); 
        if (a % b == 0) 
            return b; 
        return gcd(b, a % b); 
    }
}
