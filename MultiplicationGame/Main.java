import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            long n = scanner.nextLong();
            scanner.nextLine();

            //Strategy

            //Since Stan always starts, he will always chose 9 to reach the condition p>=n as fast as possible.
            //In the other hand, Ollie will always chose 2 to slow down Stan.

            long p = 1;
            boolean turn = true; // turn true is Stan, false Ollie

            while(p < n){
                if(turn){
                    p = p * 9;
                    turn = false;
                }else{
                    p = p * 2;
                    turn = true;
                }
            }

            if(!turn){
                System.out.println("Stan wins.");
            }else{
                System.out.println("Ollie wins.");
            }
        }
    }
}
