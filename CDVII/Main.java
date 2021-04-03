import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        
        br.readLine();
        int cases = Integer.parseInt(str);
        while (cases-- != 0){
            String[] pricesStr = br.readLine().split(" ");
            int[] prices = new int[24];
            for (int i = 0; i < pricesStr.length; i++){
                prices[i] = Integer.parseInt(pricesStr[i]);
            }

            //Map to store relation between plate and records
            Map<String, List<Record>> recordList = new HashMap<String, List<Record>>();
            String plate = "";
            List<Record> records = new ArrayList<>();
            //Read vehicle records
            while (true){
                String oneLine = br.readLine();
                if (oneLine == null || oneLine.equals("")) break;

                String[] lineArray = oneLine.split(" ");
                plate = lineArray[0];
                Record r = new Record();
                r.month = Integer.parseInt(lineArray[1].split(":")[0]);
                r.day = Integer.parseInt(lineArray[1].split(":")[1]);
                r.hour = Integer.parseInt(lineArray[1].split(":")[2]);
                r.mins = Integer.parseInt(lineArray[1].split(":")[3]);

                r.isExit = lineArray[2].equals("exit");
                r.loc = Integer.parseInt(lineArray[3]);
                if(recordList.get(plate) == null){
                    List<Record> l = new ArrayList<>();
                    l.add(r);
                    recordList.put(plate, l);
                } else {
                    recordList.get(plate).add(r);
                }
            }

            List<String> result = new ArrayList<>();
            for (Map.Entry<String, List<Record>> entry : recordList.entrySet()) {

                //We go through the mapping updating its entries
                for (int k = 0; k < entry.getValue().size() - 1; k++){
                    for (int j = 0; j < entry.getValue().size() - 1; j++){
                        if (entry.getValue().get(j).getTime() > entry.getValue().get(j + 1).getTime()){
                            Record t1 = entry.getValue().get(j);
                            Record t2 = entry.getValue().get(j + 1);
                            entry.getValue().set(j, t2);
                            entry.getValue().set(j + 1 , t1);
                        }
                    }
                }

                //Base fine
                int totalCents = 200;

                //Compute fine cost in cents using updated records
                for(int i=0;i<entry.getValue().size();i++)
                    if(!entry.getValue().get(i).isExit && i+1 < entry.getValue().size() && entry.getValue().get(i + 1).isExit){
                        int dist = Math.abs(entry.getValue().get(i).loc - entry.getValue().get(i + 1).loc);
                        totalCents += dist*prices[entry.getValue().get(i).hour];
                        totalCents += 100;
                    }
                if(totalCents != 200){
                    String formato = String.format("%.2f", totalCents/100.0);

                    String s = entry.getKey() + " $" + formato;
                    result.add(s);
                }

            }
            Collections.sort(result);
            for (String s: result){
                String n = s.replace(',', '.');
                System.out.println(n);
            }

            if (cases != 0) System.out.println();
        }
    
    }
}

class Record{
    public int month, day, hour, mins, loc;
    public boolean isExit;
    public int getTime(){
        return mins + hour*60 + day*24*60;
    }
}
