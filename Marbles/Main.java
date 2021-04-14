//package Marbles;
import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(scanner.hasNextLine()){
            long n = scanner.nextLong();
            if(n == 0){break;}
            scanner.nextLine();
            long c1 = scanner.nextLong();
            long n1 = scanner.nextLong();
            scanner.nextLine();
            long c2 = scanner.nextLong();
            long n2 = scanner.nextLong();

            long gcdCC = gcd(c1, c2);

            //Strategy
            long m1 = 0, m2 = 0;
            while(n1 * x + n2 * y < N){

            }
            if(c1*x < c2 * y){
                //Maximize y
                while((c1 * x + c2 * y) == k * gcdCC){
                    y++;
                }
            }else{
                //Maximize x
                while((c1 * x + c2 * y) == k * gcdCC){
                    x++;
                }
            }
            
            scanner.nextLine();
        }
    }

    static long gcd(long a, long b) 
    { 
        if (a < b) 
            return gcd(b, a); 
        if (a % b == 0) 
            return b; 
        return gcd(b, a % b); 
    }
}
