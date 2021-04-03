import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int cases = scanner.nextInt();

        scanner.nextLine();

        while(cases > 0){
            int numPeople = scanner.nextInt();
            int time = 0;
            int[] peopleCrossingTimes = new int[numPeople]; 

            for(int i = 0;i < numPeople;i++){
                peopleCrossingTimes[i] = scanner.nextInt();
            }

            //We use counting sort to sort the array 
            //in order to get the fastest people at the beggining
            countingSort(peopleCrossingTimes);

            //Now we apply our strategy
            int[] peopleAtBeginningSide = peopleCrossingTimes;
            int[] peopleAtFinalSide = new int[numPeople];
            int[][] crossingTrace = new int[numPeople*2][2];

            //We fill crossingTrace with -1s
            for(int j = 0; j < numPeople*2; j++){
                crossingTrace[j][0] = -1;
                crossingTrace[j][1] = -1;
            }

            //while there is people in the side 1
            int peopleAtBeginning = numPeople;
            int step = 0;
            while(peopleAtBeginning != 0){
                if(peopleAtBeginning == 1){
                    int a = peopleAtBeginningSide[peopleAtBeginning - 1];
                    
                    crossingTrace[step][0] = a;

                    time += a;
                    step++;

                    peopleAtBeginning--;
                }else if(peopleAtBeginning == 2){
                    int a = peopleAtBeginningSide[peopleAtBeginning - 2];
                    int b = peopleAtBeginningSide[peopleAtBeginning - 1];
                    
                    crossingTrace[step][0]= a;
                    crossingTrace[step][1]= b;

                    time += b;
                    step++;

                    peopleAtBeginning -= 2;
                }else if(peopleAtBeginning == 3){
                    int a = peopleAtBeginningSide[peopleAtBeginning - 3];
                    int b = peopleAtBeginningSide[peopleAtBeginning - 2];
                    int c = peopleAtBeginningSide[peopleAtBeginning - 1];
                    
                    crossingTrace[step][0]= a;
                    crossingTrace[step][1]= b;

                    time += b;
                    step++;

                    crossingTrace[step][0] = a;

                    time += a;
                    step++;

                    crossingTrace[step][0]= a;
                    crossingTrace[step][1]= c;

                    time += c;
                    step++;

                    peopleAtBeginning -= 3;
                }else{
                    int a = peopleAtBeginningSide[0];
                    int b = peopleAtBeginningSide[1];
                    int c = peopleAtBeginningSide[peopleAtBeginning - 2];
                    int d = peopleAtBeginningSide[peopleAtBeginning - 1];

                    int aproach1 = a + (3 * b) + d;
                    int aproach2 = (2 * a) + b + c + d;

                    //We must do the less expensive aproach
                    if(aproach1 <= aproach2){
                        crossingTrace[step][0]= a;
                        crossingTrace[step][1]= b;
    
                        time += b;
                        step++;
    
                        crossingTrace[step][0]= a;
    
                        time += a;
                        step++;
    
                        crossingTrace[step][0]= c;
                        crossingTrace[step][1]= d;
    
                        time += d;
                        step++;
    
                        crossingTrace[step][0]= b;
    
                        time += b;
                        step++;
                    }else{
                        crossingTrace[step][0]= a;
                        crossingTrace[step][1]= d;
    
                        time += d;
                        step++;
    
                        crossingTrace[step][0]= a;
    
                        time += a;
                        step++;
    
                        crossingTrace[step][0]= a;
                        crossingTrace[step][1]= c;
    
                        time += c;
                        step++;
    
                        crossingTrace[step][0]= a;
    
                        time += a;
                        step++;
                    }
                    
                    peopleAtBeginning -= 2;
                }
            }

            //Show the time
            System.out.println(time);

            //Show the trace
            for(int x = 0; x < step; x++){
                String s = crossingTrace[x][0] + "";
                if(crossingTrace[x][1] != -1){
                    s += " " + crossingTrace[x][1];
                }
                System.out.println(s);
            }

            cases = cases - 1;
            if(cases != 0){
                System.out.println();
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    // A particular case of the Bucket Sort algorithm, O(n+k)
    public static void countingSort( int a[] )
    {
        int min=a[0], max=a[0];
        for( int i=1; i < a.length; i++ ) {
            min = Math.min( min, a[i] );
            max = Math.max( max, a[i] );
        }

        if ( max - min <= 10000000 ) { // To use 40MB of RAM at most

            int counter[] = new int [ max - min + 1 ];

            for( int i=0; i < a.length; i++ ) counter[ a[i]-min ]++;

            int k=0;
            for( int i=0; i < counter.length; i++ ) {
                while( counter[ i ] > 0 ) {
                    a[k++] = i+min;
                    counter[i]--;
                }
            }

        } else {
            Arrays.sort( a );
        }
    }
}
