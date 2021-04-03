import java.util.*;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int cases = Integer.parseInt(scanner.nextLine());

        while(cases > 0){
            int numTurtles = Integer.parseInt(scanner.nextLine());

            //Original order of the turtles
            HashMap<String, Integer> trurtle2OriginalPosition = new HashMap<>();
            for(int turtleOriPos = 0;turtleOriPos < numTurtles; turtleOriPos++){
                String turtleName = scanner.nextLine();
                trurtle2OriginalPosition.put(turtleName, turtleOriPos);
            }

            //Desired order for the turtles
            String[] desiredOrder= new String[numTurtles];
            for(int i = 0;i < numTurtles; i++){
                desiredOrder[i] = scanner.nextLine();
            }

            //We apply the strategy bottom2Top
            int pos = numTurtles - 1;
            boolean found = false;
            while(pos > 0){
                if(trurtle2OriginalPosition.get(desiredOrder[pos]) < trurtle2OriginalPosition.get(desiredOrder[pos-1])){
                    int k = pos;
                    while(k > 0){
                        System.out.println(desiredOrder[k-1]);
                        k--;
                    }
                    break;
                }
                pos--;
            }

            cases--;
            System.out.println();
        }
    }
}
