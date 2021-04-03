import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int counter;
    static int[] regArray = new int[1000];
    static int[] ram = new int[1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String programs = br.readLine();
        int num_programs = Integer.parseInt(programs);
        br.readLine();  //discard empty line

        int num_instr = 0;
        for (int i = 0; i < num_programs; i++){
            init();

            counter = 0;

            if (i !=0 ) System.out.println();

            while (true) {
                String str = br.readLine();
                if (str == null || str.equals("")) break;

                ram[counter++] = Integer.parseInt(str);
            }

            num_instr = 1;
            counter = 0;
            while (decode() == 1) {
                num_instr++;
            }

            System.out.println(num_instr);
        }


    }

    private static int decode() {
        int command = ram[counter] / 100;
        int d = (ram[counter] % 100) / 10;
        int n = ram[counter] % 10;

        switch (command) {
            case 1 :
                return 0;

            // set register d to n
            case 2 :
                regArray[d] = n;
                counter++;
                break;

            // add n to register d
            case 3 :
                regArray[d] = (regArray[d] + n) % 1000;
                counter++;
                break;

            // multiply register d by n
            case 4 :
                regArray[d] = (regArray[d] * n) % 1000;
                counter++;
                break;

            // set register d to the value of register n
            case 5 :
                regArray[d] = regArray[n];
                counter++;
                break;

            // add the value of register n to register d
            case 6 :
                regArray[d] = (regArray[d] + regArray[n]) % 1000;
                counter++;
                break;

            // multiply register d by the value of register n
            case 7 :
                regArray[d] = (regArray[d] * regArray[n]) % 1000;
                counter++;
                break;

            // set register d to the value in RAM whose address is in register n
            case 8 :
                regArray[d] = ram[regArray[n]];
                counter++;
                break;

            // set the value in RAM whose address in in register n to that of register d
            case 9 :
                ram[regArray[n]] = regArray[d];
                counter++;
                break;

            // goto
            case 0 :
                if (regArray[n] != 0) {
                    counter = regArray[d];
                } else {
                    counter++;
                }
                break;

            default:
                break;
        }
        return 1;
    }

    private static void init() {
        int i;
        for (i = 0; i < 1000; i++) {
            regArray[i] = 0;
        }
        for (i = 0; i < 1000; i++) {
            ram[i] = 0;
        }
    }

}
