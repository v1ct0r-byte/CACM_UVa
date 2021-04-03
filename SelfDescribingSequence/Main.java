import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            int result = 0;
            if(n == 0){break;}

            //We store in a table the maximum value of n for f(n)
            HashMap<Integer, Integer> golombSequenceMap = new HashMap<Integer, Integer>();
            //Initial values
            golombSequenceMap.put(1, 1);
            golombSequenceMap.put(2, 3);

            int delta = 2;
            int fn = 2;
            int nx = 3;

            //We apply the strategy for precomputing
            while(nx <= n){
                fn++;

                nx = nx + delta;
                golombSequenceMap.put(fn, nx);

                if(fn == golombSequenceMap.get(delta)){
                    delta++;
                }
            }

            //for(int i = 1;i <= 10; i++){
            //    System.out.println(golombSequenceMap.get(i));
            //}
            
            //Binary search of n
            int start = 1, end = golombSequenceMap.size(), middle = 0, comparison = 0;
            while(start <= end){
                middle = (start + end)/2;
                comparison = (int) golombSequenceMap.get(middle);
                if(comparison < n){
                    start = middle + 1;
                }else if(comparison > n){
                    end = middle - 1;
                }else{
                    break;
                }
            }

            if(comparison < n){
                result = middle + 1;
            }else{
                result = middle;
            }
            
            System.out.println(result);
        }
    }
}
