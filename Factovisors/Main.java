package Factovisors;
import java.util.*;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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

        List<Integer> primes = new ArrayList<Integer>();
        for(int i = 0;i < isPrime.length;i++){
            if(isPrime[i]){
                primes.add(i);
            } 
        }

        while(scanner.hasNextLine()){
            String line[] = scanner.nextLine().split(" ");

            if(line == null || line[0].equals("")){
                break;
            }
            
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);

            if(n == 0){
                if(m == 1){
                    System.out.println(m + " divides " + n + "!");
                }else{
                    System.out.println(m + " does not divide " + n + "!");
                }
            }else if(m == 0){
                System.out.println(m + " divides " + n + "!");
            }else{
                List<Factor> factors = new ArrayList<Factor>();
                int mp = m;
                //prime factorization
                for(int i = 0; i < primes.size();i++){
                    int c = 0;
                    int p = primes.get(i);
                    while(mp%p == 0){
                        mp = mp/p;
                        c++;
                    }
                    if(c != 0){
                        factors.add(new Factor(p,c));
                    }
                    if(mp == 1){
                        break;
                    }
                }  
                
                //numbers from 2 to n in an array
                int[] factorial = new int[n-1];
                int desc = 2;
                for(int i = 0; i < n-1;i++){
                    factorial[i] = desc;
                    desc++;
                }

                //Check if the all the factors of m are in the range 2 to n
                boolean divides = true;
                for(int i = 0; i < factors.size(); i++){
                    Factor f = factors.get(i);
                    //we follow the numbers from 2 to n
                    for(int j = 0; j < factorial.length; j++){
                        int actualFactorial = factorial[j];
                        if(actualFactorial >= f.p){
                            int rest = actualFactorial%f.p;
                            int div = actualFactorial/f.p;
                            if(rest == 0){
                                f.c = f.c - 1;
                                actualFactorial = div;
                                factorial[j] = div;
                            }
                        }
                    }
                    //if c > 0 then it doesnt fit in the range
                    if(f.c > 0){
                        divides = false;
                        break;
                    }
                }
                
                if(divides){
                    System.out.println(m + " divides " + n + "!");
                }else{
                    System.out.println(m + " does not divide " + n + "!");
                }
            }
        }
    }
}

class Factor{
    public int p;
    public int c;

    public Factor(int p, int c){
        this.p = p;
        this.c = c;
    }
}
