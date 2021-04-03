import java.util.*;

import java.math.*;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int instances = Integer.parseInt(scanner.nextLine());

        BigInteger v24 = BigInteger.valueOf(24);

        while(instances > 0){
            BigInteger numPoints = new BigInteger(scanner.nextLine());
            
            BigInteger nPow2 = numPoints.pow(2);
            BigInteger nPow3 = numPoints.pow(3);
            BigInteger nPow4 = numPoints.pow(4);
            
            //http://oeis.org/A001591
            //a(n) = (n^4 - 6*n^3 + 23*n^2 - 18*n + 24)/24
            BigInteger result = nPow4;
            nPow3 = nPow3.multiply(BigInteger.valueOf(6));
            result = result.subtract(nPow3);
            nPow2 = nPow2.multiply(BigInteger.valueOf(23));
            result = result.add(nPow2);
            numPoints= numPoints.multiply(BigInteger.valueOf(18));
            result = result.subtract(numPoints);
            result = result.add(v24);
            result = result.divide(v24);

            System.out.println(result);

            instances--;
        }
    }
}
