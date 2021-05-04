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
            String table[][] = String[n][m];
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
            String[] words = String[w];
            for(int i = 0; i < w; i++){
                words[i] = scanner.nextLine().toLowerCase();
            }

            //Strategy
            for(int i = 0; i < w; i++){
                String[][] word = String[1][words[i].length()];
                String[] wordSplited = words[i].split("");
                for(int j = 0; j < words[i].length(); j++){
                    word[0][j] = wordSplited[j];
                }

                //lines
                for(int j = 0; j < n; j++){
                    //columns
                    for(int q = 0; q < m; q++){
                        if(table[j][k].compareTo(word[0][0])){
                            if(contains(table, word, j, k)){
                                System.out.println((j+1) + " " + (k+1));
                                break;
                            }
                        }
                    }
                }
            }

            scanner.nextLine();
            cases--;
        }
    }

    public static boolean contains(String[][] table, String[][] word, int j, int k, int n, int m){
        //check the 8 adjacent letters
        
        return true;
    }
}
