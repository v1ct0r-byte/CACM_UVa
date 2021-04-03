import java.util.*;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int cases = Integer.parseInt(scanner.nextLine());

        while(cases > 0){
            long number = Long.parseLong(scanner.nextLine());
            boolean hasPalindrome = false;
            int iteration = 0;
            //We apply the strategy
            while(number != reverse(number)){
                //we do an iteration
                number = number + reverse(number);
                iteration++;
            }
            
            System.out.println(iteration + " " + number);

            cases--;
        }
    }

    private static long reverse(long number){
        long reverse = 0; 
        while(number != 0){
            reverse *= 10;
            reverse = reverse + (number%10);
            number = number/10;
        }

        return reverse;
    }

}
