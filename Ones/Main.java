import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) break;
            int n = Integer.parseInt(str);

            int ones = 0;
            int c =0;
            do{
                c = (c*10+1)%n;
                ones ++;
            }
            while(c!=0);
            System.out.println(ones);
        }
    }
}
