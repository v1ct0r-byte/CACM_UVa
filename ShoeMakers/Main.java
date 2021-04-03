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

            while(cases-- != 0){
                String blank = br.readLine();
                int n = Integer.parseInt(br.readLine());
                List<tuple> jobs = new ArrayList<>();
                for(int i=0;i<n;i++){
                    String line[] = br.readLine().split(" ");
                    int time = Integer.parseInt(line[0]);
                    int fine = Integer.parseInt(line[1]);

                    jobs.add(new tuple(time,fine,i+1));
                }

                for (int i = 0; i < jobs.size()-1; i++){
                    for (int j = 0; j < jobs.size()-1; j++){
                        if (jobs.get(j).a * jobs.get(j+1).b > jobs.get(j).b * jobs.get(j+1).a ){
                            tuple t1 = jobs.get(j);
                            tuple t2 = jobs.get(j+1);
                            jobs.set(j, t2);
                            jobs.set(j + 1, t1);
                        }
                    }
                }

                for (int i = 0; i < jobs.size(); i++){
                    System.out.print(jobs.get(i).c);
                    if (i == jobs.size()-1) System.out.print("\n");
                    else System.out.print(" ");
                }

                if(cases != 0) System.out.println();
            }
        }
    }
}

class tuple{
        public int a;
        public int b;
        public int c;

        public tuple(int x, int y, int z){
            a = x;
            b = y;
            c = z;
        }
}