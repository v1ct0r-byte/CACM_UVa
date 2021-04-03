import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) break;

            int cases = Integer.parseInt(str);

            while (cases-- != 0){
                int days = Integer.parseInt(br.readLine());
                int parties = Integer.parseInt(br.readLine());
                List<Integer> dayoffs = new ArrayList<>();
                for(int j = 0; j < parties; j++){
                    int d = Integer.parseInt(br.readLine());
                    dayoffs.add(d);
                }

                int strikes = 0;

                for (int i = 1; i < days + 1; i++){
                    if(i%7==6 || i%7==0) continue;

                    boolean found = false;

                    for(int j = 0; j < parties; j++){
                        if (i % dayoffs.get(j) == 0) {
                            found = true;
                        }
                    }

                    if (found) ++strikes;
                }

                System.out.println(strikes);
            }

        }
    }
}