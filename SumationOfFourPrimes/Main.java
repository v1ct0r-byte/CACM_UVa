//package SumationOfFourPrimes;
import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Compute erathostenes sieve
        int numSieve = 10000000;

        //Initially we assume all integers are prime
        boolean[] isPrime = new boolean[numSieve+1];
        for (int i = 2; i <= numSieve; i++) {
            isPrime[i] = true;
        }

        //Mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= numSieve; factor++) {

            //If factor is prime, then mark multiples of factor as nonprime
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= numSieve; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }
        while(scanner.hasNextLine()){
            int n = scanner.nextInt();

            if(n < 8){
                System.out.println("Impossible.");
            }else{
                //1
                int p1 = 0;
                for(int i = (n/4); i > 0; i--){
                    if(isPrime[i]){
                        p1 = i;
                        break;
                    }
                }

                //2
                int np1 = n - p1;
                int p2 = 3;
                if(np1%2 == 0){
                    p2 = 2;
                }else{
                    /*for(int i = np1 - 1; i > 0; i--){
                        if(isPrime[i]){
                            p2 = i;
                            break;
                        }
                    }*/
                }
                int np2 = np1 - p2;

                //3
                int p3 = 3, p4 = 3;
                for(int i = np2-1; i > 0; i--){
                    if(isPrime[i]){
                        p3 = i;
                        break;
                    }
                }
                p4 = np2 - p3;
                while(!isPrime[p4]){
                    for(int i = p3-1; i > 0; i--){
                        if(isPrime[i]){
                            p3 = i;
                            break;
                        }
                    }
                    p4 = np2 - p3;
                }
                System.out.println(p1 + " " + p2 + " " + p3 + " " + p4);
            }
            scanner.nextLine();
        }
    }
}
