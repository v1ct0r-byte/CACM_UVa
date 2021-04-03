import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){

            long lastBulb = Long.parseLong(scanner.nextLine());
            if(lastBulb == 0){ 
                break;
            }
            //the strategy consists in knowing if the tast bulb is a perfect square
            double perfectSquare = Math.sqrt((double) lastBulb);
            double floorSquare = Math.floor(perfectSquare);
            if((perfectSquare - floorSquare) == 0){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }
    }
}
