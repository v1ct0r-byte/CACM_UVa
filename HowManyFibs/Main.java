import java.util.*;

import java.math.BigInteger;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        while(true){
            String line = scanner.nextLine();

            String[] lineInts = line.split(" ");
            BigInteger a = new BigInteger(lineInts[0]);
            BigInteger b = new BigInteger(lineInts[1]);

            if((a.compareTo(BigInteger.ZERO) == 0) && (b.compareTo(BigInteger.ZERO) == 0)){break;}

            if(a.compareTo(BigInteger.ZERO) == 0 && b.compareTo(BigInteger.ONE) == 0){
                //Special case for 0 1
                System.out.println(1);
            }else if (a.compareTo(BigInteger.ONE) == 0 && b.compareTo(BigInteger.ONE) == 0){
                //Special case for 1 1
                System.out.println(1);
            }else{
                //Precompute all fibo numbers till length 100
                BigInteger f1 = BigInteger.valueOf(1) ,f2 = BigInteger.valueOf(2);
                List<BigInteger> fibbsList = new ArrayList<BigInteger>();
                fibbsList.add(f1);
                fibbsList.add(f2);
                BigInteger aux = f1;
                while(f2.compareTo(BigInteger.valueOf(10).pow(100)) <= 0){
                    aux = f2;
                    f2 = f2.add(f1);
                    fibbsList.add(f2);
                    f1 = aux;
                }
                //We add the fib(n) that is > than 10^100
                fibbsList.add(f2.add(f1));

                //Use modified binary search tree to find up and down boundaries
                int downBoundary = binarySearch(a, fibbsList, false);
                int upBoundary = binarySearch(b, fibbsList, true);

                //Compute and print the result
                System.out.println((upBoundary - downBoundary) + 1);
            }
        }
    }

    public static int binarySearch(BigInteger number2Find, List<BigInteger> fibbsList, boolean up){
        int start = 0, end = fibbsList.size() - 1, middle = 0, comparison = 0;
        //Special case for 1 when it is up boundary
        if(number2Find.compareTo(BigInteger.ONE) == 0 && up){
            return 1;
        }
        while(start <= end){
            middle = (start + end) / 2;
            comparison = fibbsList.get(middle).compareTo(number2Find);
            if(comparison < 0){
                start = middle + 1;
            }else if(comparison > 0){
                end = middle - 1;
            }else{
                //System.out.println("Si esta: " + middle);
                return middle;
            } 
        }
        //Finally, if it is not in the arrayList we must take into account if we are search for up or down boundary
        if(up){
            //If it is up boundary and the last comparison was > 0 it means it is 1 less, 
            //because we are searching for the minimum value within range
            if(comparison > 0){
                middle--;
            }   
        }else{
            //If it is down boundary and the last comparison was < 0 it means it is 1 more, 
            //because we are searching for the maximim value within range
            if(comparison < 0){
                middle++;
            }
        }
        //System.out.println("No esta: " + middle);
        return middle;
    }
}
