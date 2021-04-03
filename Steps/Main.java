//package Steps;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int cases = Integer.parseInt(scanner.nextLine());

        while(cases > 0){
            String line[] = scanner.nextLine().split(" ");
            int x = Integer.parseInt(line[0]), y = Integer.parseInt(line[1]);

            int distance = y - x;

            //Apply strategy
            double root = Math.sqrt(distance);
            int rootInt = (int) Math.ceil(root);
            int steps = rootInt + (rootInt - 1);

            //We ask if n is in the first or second "half" to the perfect square
            if(root < (rootInt - 0.5)){
                steps--;
            }
            
            if(distance == 0){
                System.out.println(distance);
            }else{
                System.out.println(steps);
            }
            cases--;
        }
    }
}
