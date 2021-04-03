//package Factovisors;
import java.util.*;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while(scanner.hasNext()){
            long n = scanner.nextLong();
            long m = scanner.nextLong();

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
                long mp = m;
                //prime factorization from primeFactorization.cpp based on the book
                Factor f = new Factor(2, 0);
                while (mp % 2 == 0) {
                    mp /= 2;
                    f.c++;
                }
                if (f.c > 0) factors.add(f);

                long p = 3;
                while (p*p <= mp) {
                    f = new Factor(p, 0);
                    while (mp % p == 0) {
                        mp /= p;
                        f.c++;
                    }
                    if (f.c > 0) factors.add(f);

                    p += 2;
                }
                if (mp > 1)
                {
                    factors.add(new Factor(mp, 1));
                }  

                //Check if the all the factors of m are in the range 2 to n
                boolean divides = true;
                for (int i = 0; i < factors.size() && divides; i++) {
                    f = factors.get(i);
                    //we follow the numbers from 2 to n consuming the actual factor
                    for (long j = f.p; j <= n && f.c > 0 && divides; j += f.p) {
                        long actualFactorial = j;
                        while (actualFactorial > 0  &&  actualFactorial % f.p == 0  &&  f.c > 0) {
                            actualFactorial /= f.p;
                            f.c--;
                        }
                    }
                    //if c > 0 then it doesnt fit in the range
                    divides = f.c <= 0;
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
    public long p;
    public int c;

    public Factor(long p, int c){
        this.p = p;
        this.c = c;
    }
}
