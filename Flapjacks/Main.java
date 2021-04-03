//package Flapjacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) break;

            String[] pancakesStr = str.split(" ");
            List<Integer> pancakes = new ArrayList<>();
            for (String p: pancakesStr){
                pancakes.add(Integer.parseInt(p));
            }

            int ind = 0;
            int n = pancakes.size();
            for (String p: pancakesStr){
                System.out.print(p + " ");
            }
            System.out.println();

            for(int i=n-1;i>=0;i--){

                int max=-1;

                for(int j=0;j<=i;j++)
                    if(max<=pancakes.get(j)){
                        max=pancakes.get(j);
                        ind=j;
                    }

                if(ind != i){
                    if(ind != 0){
                        System.out.print(n-ind + " ");

                        for(int j=0;j<=ind/2;j++) Collections.swap(pancakes, j, ind-j);
                    }

                    System.out.print(n-i + " ");

                    for(int j=0;j<=i/2;j++) Collections.swap(pancakes, j, i-j);
                }
            }

            System.out.println(0);
        }
    }
}
