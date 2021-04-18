package Marbles;
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

            Euclid gcdEuclid = euclid(c1, c2);
            long d = gcdEuclid.d;
            //Strategy
            
            /*
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
            */

            scanner.nextLine();
        }
    }
}

class Euclid{

    public long d = 0, x = 1, y = 0;

    public Euclid(){

    }

    public Euclid(long a, long x, long y){
        this.d = a;
        this.x = x;
        this.y = y;
    }

    //This algorithm is the one seen in the academic subject Cryptography at the first semester of 4º
    public static Euclid euclid(long a, long b){
        if(b == 0){
            return new Euclid(a, (long)1, (long)0);
        }else{
            Euclid aux = euclid(b, a%b);
            Euclid res = new Euclid(aux.d, aux.y, aux.x - Math.floorDiv(a, b)*aux.y);
            return res;
        }
    }
}
