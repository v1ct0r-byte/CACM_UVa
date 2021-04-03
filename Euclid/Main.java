import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static Euclid result = new Euclid();

    public static void main(String[] args) {
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            if(line == null || line.equals("")){
                break;
            }

            String lines[] = line.split(" ");
            long a = Long.parseLong(lines[0]);
            long b = Long.parseLong(lines[1]);

            //We apply the algorithm
            result = Euclid.euclid(a, b);
            
            System.out.println(result.x + " " + result.y + " " + result.d);
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