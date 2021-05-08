//package WhereIsWaldorf;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();

        while(cases > 0){
            //Size n rows x m columns of the table
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            scanner.nextLine();

            //Table of letters
            String table[][] = new String[n][m];
            for(int i = 0; i < n; i++){
                String line[] = scanner.nextLine().toLowerCase().split("");
                for(int j = 0;j < m; j++){
                    table[i][j] = line[j];
                }
            }

            //Numeber of words
            int w = scanner.nextInt();
            scanner.nextLine();

            //Words to find
            String[] words = new String[w];
            for(int i = 0; i < w; i++){
                words[i] = scanner.nextLine().toLowerCase();
            }

            //Strategy
            for(int i = 0; i < w; i++){
                String[] word = words[i].split("");
                boolean found = false;
                //lines
                for(int j = 0; j < n && !found; j++){
                    //columns
                    for(int q = 0; q < m && !found; q++){
                        if(table[j][q].compareTo(word[0]) == 0){
                            if(word.length == 1){
                                System.out.println((j+1) + " " + (q+1));
                                found = true;
                            }else if(contains(table, word, j, q, n, m)){
                                System.out.println((j+1) + " " + (q+1));
                                found = true;
                            }
                        }
                    }
                }
            }

            if(cases > 1){
                System.out.println();
                scanner.nextLine();
            }
            cases--;
        }

        scanner.close();
    }

    public static boolean contains(String[][] table, String[] word, int j, int q, int n, int m){
        //check the 8 adjacent letters
        boolean foundWord = false;
        int len = word.length;
        if(!foundWord) foundWord = checkDirection(1, 0, table, word, j, q, n, m, len);//right
        if(!foundWord) foundWord = checkDirection(1, -1, table, word, j, q, n, m, len);//up right
        if(!foundWord) foundWord = checkDirection(0, -1, table, word, j, q, n, m, len);//up
        if(!foundWord) foundWord = checkDirection(-1, -1, table, word, j, q, n, m, len);//up left
        if(!foundWord) foundWord = checkDirection(-1, 0, table, word, j, q, n, m, len);//left
        if(!foundWord) foundWord = checkDirection(-1, 1, table, word, j, q, n, m, len);//down left
        if(!foundWord) foundWord = checkDirection(0, 1, table, word, j, q, n, m, len);//down
        if(!foundWord) foundWord = checkDirection(1, 1, table, word, j, q, n, m, len);//down right
        return foundWord;
    }

    public static boolean checkDirection(int directionX, int directionY, String[][] table, String[] word, int j, int q, int n, int m, int len){
        //current length
        int cLen = 1;
        //check if matches
        boolean end = false;
        for(int py = j + directionY, px = q + directionX; px >= 0 && px < m && py >= 0 && py < n && !end;  px = px + directionX, py = py + directionY){
            if(table[py][px].compareTo(word[cLen]) == 0){
                cLen++;
                if(cLen == len){
                    return true;
                }
            }else{
                end = true;
            }
        }
        return false;
    }
}
