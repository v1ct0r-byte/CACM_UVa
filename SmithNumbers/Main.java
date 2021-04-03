//package SmithNumbers;
import java.util.*;

public class Main{

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int c = scanner.nextInt();
        scanner.nextLine();

        while(c > 0){
            long n = Long.parseLong(scanner.nextLine());
            
            long sumN = 1, sumF = 0;
            while(sumN != sumF){
                n++;
                sumN = sumFigures(n);
                sumF = 0;
                List<Long> factors = factorization(n);
                //be carefull with primes
                if(factors.size() > 1){
                    for(int i = 0; i < factors.size(); i++){
                        sumF += sumFigures(factors.get(i));
                    }
                }
            }
            System.out.println(n);
            c--;
        }
    }

    public static long sumFigures(long n){
        long res = 0;
        while(n != 0){
            res += n%10;
            n = n/10;
        }
        return res;
    }

    //Prime factorization aproach from primeFactorization.cpp based on the book
    public static List<Long> factorization(long n){
        List<Long> factors = new ArrayList<Long>();
        Long i, ii;

        while ((n % 2) == 0) {
            factors.add(Long.parseLong("2"));
            n /= 2;
        }

        i = Long.parseLong("3");
        ii = i * i;
        while (ii <= n) {

            if ((n % i) == 0) {
                factors.add(i);
                n /= i;
            } else {
                ii += (i << 2) + 4;
                i += 2;
            }
        }

        if (n > 1) factors.add(n);

        return factors;
    }
}