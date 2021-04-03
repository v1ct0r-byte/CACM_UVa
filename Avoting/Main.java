import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int MAX_CANDIDATES = 100;
        String[] candedates = new String[MAX_CANDIDATES];
        int[][] ballots = new int[1000][1000];
        int[] active_candidates = new int[MAX_CANDIDATES];
        int[] votes = new int[MAX_CANDIDATES];
        int min_votes;
        int max_votes;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cases = br.readLine();
        int num_cases = Integer.parseInt(cases);
        br.readLine();
        while (--num_cases >= 0) {
            boolean newLine = false;
            int num_candidates = Integer.parseInt(br.readLine());
            for (int i = 0; i < num_candidates; i++) {
                String temp = br.readLine();
                candedates[i] = temp;
            }

            String str;
            int bnum = 0;
            while (true) {
                str = br.readLine();
                if (str == null || str.equals("")) break;

                StringTokenizer st = new StringTokenizer(str);
                for (int n = 0; n < num_candidates; n++) {
                    ballots[bnum][n] = Integer.parseInt(st.nextToken()) - 1;
                }
                bnum++;
            }


            for (int c = 0; c < num_candidates; c++) active_candidates[c] = 1; //not kicked

            boolean concluded = !(num_candidates > 0 && bnum > 0);
            boolean found = false;
            String answer = "";
            while (!concluded) {                                // ez addig megy míg valamelyik nem0 | this goes till 1 is 0
                int c;
                for (c = 0; c < num_candidates; c++) votes[c] = 0;     // kinulláz egy tombot  a jelöltek számával | restablish a ballot with the number of candidates

                for( int b=0; b < bnum; b++ ) {                  //végig megy a szavazatokon | iterate through the votes of ballots
                    found=false;
                    //goes throug candidates and if it is written in the table we emit a vote in favour
                    for( int i=0; i < num_candidates && !found; i++ ) { //ez végig megy a jelolteken és ha a táblázatvan az van írva akk a jelölthöz bead egy szavazatot
                        c = ballots[b][i];
                        if ( active_candidates[c] == 1 ) {
                            votes[c]++;
                            found=true;
                        }
                    }
                }


                min_votes = 1000;
                max_votes = -1;
                //review votes if a candidate is active and write minimum and maximum votes
                for (c = 0; c < num_candidates; c++) {                   // végig megy a szavazatokon megint , ha van ilyen activ jelölt akkor , és átírja a min és a max voteot
                    if (active_candidates[c] == 1) {
                        min_votes = Math.min(votes[c], min_votes);
                        max_votes = Math.max(votes[c], max_votes);
                    }
                }

                //if min and max match they have won
                if (min_votes == max_votes) {                         //ha ez egyenlő megvagyunk
                    for (c = 0; c < num_candidates; c++) {
                        if (active_candidates[c] == 1) {
                            if(candedates[c] != null){
                                System.out.println(candedates[c]);
                            }
                        }
                    }
                    concluded = true;
                } else {
                    for (c = 0; c < num_candidates && !concluded; c++) {
                        if (votes[c] == min_votes) active_candidates[c] = 0;

                        if (2 * votes[c] >= bnum) {
                            if(candedates[c] != null){
                                System.out.println(candedates[c]);
                            }
                            concluded = true;
                        }
                    }
                }
                //System.out.println("\n");
            }

            if (num_cases > 0 && !newLine){
                System.out.println();
                newLine = true;
            }

        }

        System.exit(0);
    }
}
