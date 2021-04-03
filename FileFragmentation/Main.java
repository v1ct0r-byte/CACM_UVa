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

            for(int i = 0; i < cases; i++){
                List<String> list = new ArrayList<>();
                String blank = br.readLine();
                while(true){
                    String str2 = br.readLine();
                    if (str2 == null || str2.equals("")){
                        break;
                    }

                    list.add(str2);
                }

                String result = "";
                for (int j = 0; j < list.size(); j++){
                    if(check(j, list,list.get(0) + list.get(j)))
                        result = list.get(0) + list.get(j);
                    if (check(j, list,list.get(j) + list.get(0)))
                        result = list.get(j) + list.get(0);
                }
                System.out.println(result);
                if (i < cases-1) System.out.println();
            }

        }
    }

    private static boolean check(int c, List<String> list, String s) {
        List<Boolean> b = new ArrayList<>();
        int n = list.size();
        b.add(true);

        for (int i = 1; i < n; i++){
            b.add(false);
        }

        b.set(c, true);
        int cnt = 0;
        for (int i = 1; i < n; i++)
            if (!b.get(i))
                for (int j = i + 1; j < n; j++)
                    if (!b.get(j))
                        if ((list.get(i) + list.get(j)).equals(s) || (list.get(j) + list.get(i)).equals(s)) {
                                    b.set(i, true);
                                    b.set(j, true);
                                    cnt++;
                        }
        return cnt == n / 2 - 1;
    }
}
