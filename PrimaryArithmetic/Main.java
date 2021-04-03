import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            String line[] = scanner.nextLine().split(" ");
            if(line[0].compareTo("0") == 0 && line[1].compareTo("0") == 0){
                break;
            }

            //Usefull variables
            boolean hasCarry = false;
            int carry = 0;
            int count = 0;
            String a = line[0], b = line[1];
            //Number of digits in each number
            int lenA = a.length();
            int lenB = b.length();

            //We apply the computation as the kids know, digit by digit
            while(lenA != 0 || lenB != 0){
                int ai = 0, bi = 0;
                if(lenA > 0){
                    ai = a.charAt(lenA - 1) - '0';
                    lenA--;
                }
                if(lenB > 0){
                    bi = b.charAt(lenB - 1) - '0';
                    lenB--;
                }

                int sum = ai + bi + carry;

                if(sum >= 10){
                    carry = 1;
                    count++;
                }else{
                    carry = 0;
                }
            }

            if(count == 0){
                System.out.println("No carry operation.");
            }else if(count == 1){
                System.out.println(count + " carry operation.");
            }else{
                System.out.println(count + " carry operations.");
            }
        }
    }
    
}
