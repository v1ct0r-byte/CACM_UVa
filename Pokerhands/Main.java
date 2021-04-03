import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) break;

            StringTokenizer st = new StringTokenizer(str);
            List<String> whiteHand = new ArrayList<>();
            List<String> blackHand = new ArrayList<>();

            for (int i = 0; i < 5; i++) whiteHand.add(st.nextToken());
            for (int i = 0; i < 5; i++) blackHand.add(st.nextToken());

            List<List<String>> whiteList = makeList(whiteHand);
            List<List<String>> blackList = makeList(blackHand);

            String High1 = high(whiteList);  // highest card
            String High2 = high(blackList);

            List<String> pair1 = pair(whiteList);  //highest pair
            List<String> pair2 = pair(blackList);

            List<String> twoPair1 = twoPair(whiteList);  //empty if not exist
            List<String> twoPair2 = twoPair(blackList);

            List<String> threeSame1 = threeSame(whiteList);
            List<String> threeSame2 = threeSame(blackList);

            String highestStraight1 = straight(whiteList);
            String highestStraight2 = straight(blackList);

            Boolean flush1 = flush(whiteList);
            Boolean flush2 = flush(blackList);

            List<String> foulHouse1 = fullHouse(whiteList, threeSame1.get(0));
            List<String> foulHouse2 = fullHouse(blackList, threeSame2.get(0));

            List<String> poker1 = poker(whiteList, threeSame1.get(0));
            List <String> poker2 = poker(blackList, threeSame2.get(0));

            String sFlush1 = sFlush(highestStraight1, flush1);
            String sFlush2 = sFlush(highestStraight2, flush2);

            List<Boolean> whiteHave = whatHave(High1, pair1.get(0), twoPair1, threeSame1.get(0), highestStraight1, flush1, foulHouse1.get(0), poker1.get(0), sFlush1);
            List<Boolean> blackHave = whatHave(High2, pair2.get(0), twoPair2, threeSame2.get(0), highestStraight2, flush2, foulHouse2.get(0), poker2.get(0), sFlush2);

            if (whiteHave.get(8) && blackHave.get(8)){
                if (sFlush1.equals(sFlush2)){
                    System.out.println("Tie.");
                    continue;
                }
                if(Integer.parseInt(sFlush1) > Integer.parseInt(sFlush2)) System.out.println("Black wins.");
                else System.out.println("White wins.");

                continue;
            } else {
                if (whiteHave.get(8)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(8)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            if (whiteHave.get(7) && blackHave.get(7)){
                if (poker1.get(0).equals(poker2.get(0))){
                    if (poker1.get(1).equals(poker2.get(1))) System.out.println("Tie.");
                    if (Integer.parseInt(poker1.get(1)) > Integer.parseInt(poker2.get(1))) System.out.println("Black wins.");
                    if (Integer.parseInt(poker1.get(1)) < Integer.parseInt(poker2.get(1))) System.out.println("White wins.");
                    continue;
                }
                if(Integer.parseInt(poker1.get(0)) > Integer.parseInt(poker2.get(0))) System.out.println("Black wins.");
                else System.out.println("White wins.");
                continue;
            } else {
                if (whiteHave.get(7)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(7)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            if (whiteHave.get(6) && blackHave.get(6)){
                if (foulHouse1.get(0).equals(foulHouse2.get(0))){
                    if (foulHouse1.get(1).equals(foulHouse2.get(1))) System.out.println("Tie.");
                    if(Integer.parseInt(foulHouse1.get(1)) > Integer.parseInt(foulHouse2.get(1))) System.out.println("Black wins.");
                    if(Integer.parseInt(foulHouse1.get(1)) < Integer.parseInt(foulHouse2.get(1))) System.out.println("White wins.");
                    continue;
                }
                if(Integer.parseInt(foulHouse1.get(0)) > Integer.parseInt(foulHouse2.get(0))) System.out.println("Black wins.");
                else System.out.println("White wins.");

                continue;
            } else {
                if (whiteHave.get(6)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(6)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            if (whiteHave.get(5) && blackHave.get(5)){
                if (flush1.equals(flush2)) {
                    int t = Highest2(whiteList, blackList);
                    if (t == 0) System.out.println("Black wins.");
                    if (t == 1) System.out.println("White wins.");
                    if (t == -1) System.out.println("Tie.");
                    continue;
                }
                if(Integer.parseInt(High1) > Integer.parseInt(High2)) System.out.println("Black wins.");
                else System.out.println("White wins.");

                continue;
            } else {
                if (whiteHave.get(5)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(5)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            if (whiteHave.get(4) && blackHave.get(4)){
                if (highestStraight1.equals(highestStraight2)){
                    System.out.println("Tie.");
                    continue;
                }
                if(Integer.parseInt(highestStraight1) > Integer.parseInt(highestStraight2)) System.out.println("Black wins.");
                else System.out.println("White wins.");

                continue;
            } else {
                if (whiteHave.get(4)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(4)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            if (whiteHave.get(3) && blackHave.get(3)){
                if (threeSame1.get(0).equals(threeSame2.get(0))) {
                    int t = Highest3(threeSame1, threeSame2);
                    if (t == 0) System.out.println("Black wins.");
                    if (t == 1) System.out.println("White wins.");
                    if (t == -1) System.out.println("Tie.");
                    continue;
                }
                if(Integer.parseInt(threeSame1.get(0)) > Integer.parseInt(threeSame2.get(0))) System.out.println("Black wins.");
                else System.out.println("White wins.");

                continue;
            } else {
                if (whiteHave.get(3)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(3)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            if (whiteHave.get(2) && blackHave.get(2)){
                List<String> t1 = new ArrayList<>();
                List<String> t2 = new ArrayList<>();
                t1.add(twoPair1.get(0));
                t1.add(twoPair1.get(1));
                t2.add(twoPair2.get(0));
                t2.add(twoPair2.get(1));
                Collections.sort(t1);
                Collections.sort(t2);

                if (t1.get(1).equals(t2.get(1))){
                    if (t1.get(0).equals(t2.get(0))){
                        if (Integer.parseInt(twoPair1.get(2)) > Integer.parseInt(twoPair2.get(2))) System.out.println("Black wins.");
                        if (Integer.parseInt(twoPair1.get(2)) < Integer.parseInt(twoPair2.get(2))) System.out.println("White wins.");
                        if (Integer.parseInt(twoPair1.get(2)) == Integer.parseInt(twoPair2.get(2))) System.out.println("Tie.");
                    }
                    else{
                        if (Integer.parseInt(t1.get(0)) > Integer.parseInt(t2.get(0))) System.out.println("Black wins.");
                        else System.out.println("White wins.");
                    }
                } else {
                    if (Integer.parseInt(t1.get(1)) > Integer.parseInt(t2.get(1))) System.out.println("Black win.");
                    else System.out.println("White wins.");
                }
                continue;
            } else {
                if (whiteHave.get(2)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(2)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            if (whiteHave.get(1) && blackHave.get(1)){
                if (pair1.get(0).equals(pair2.get(0))){
                    int t = pairLeftHigh(pair1, pair2);
                    if (t == 0) System.out.println("Black wins.");
                    if (t == 1) System.out.println("White wins.");
                    if (t == -1 ) System.out.println("Tie.");
                    continue;
                }
                if(Integer.parseInt(pair1.get(0)) > Integer.parseInt(pair2.get(0))) System.out.println("Black wins.");
                else System.out.println("White wins.");

                continue;
            } else {
                if (whiteHave.get(1)){
                    System.out.println("Black wins.");
                    continue;
                }
                if(blackHave.get(1)){
                    System.out.println("White wins.");
                    continue;
                }
            }

            int h = Highest(whiteList, blackList);
            if(h == 1 ) System.out.println("Black wins.");
            if(h == 0 ) System.out.println("White wins.");
            if(h == -1 ) System.out.println("Tie.");
        }

    }

    private static int pairLeftHigh(List<String> pair1, List<String> pair2) {
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();
        for (int i = 1; i < 4; i++){
            num1.add(Integer.parseInt(pair1.get(i)));
            num2.add(Integer.parseInt(pair2.get(i)));
        }

        Collections.sort(num1);
        Collections.sort(num2);

        if (num1.get(2) > num2.get(2)) return 0;
        if (num1.get(2) < num2.get(2)) return 1;

        if (num1.get(1) > num2.get(1)) return 0;
        if (num1.get(1) < num2.get(1)) return 1;

        if (num1.get(0) > num2.get(0)) return 0;
        if (num1.get(0) < num2.get(0)) return 1;

        return -1;
    }

    public static List<Boolean> whatHave(String high, String pair, List<String> twoPair1, String threeSame, String highestStraight, Boolean flush, String foulHouse, String poker, String sFlush) {
        List<Boolean> result = new ArrayList<>();
        result.add(true);

        if (pair.equals("-1")){
            result.add(false);
        } else {
            result.add(true);
        }

        if (twoPair1.size() == 1 || twoPair1.size()==0){
            result.add(false);
        }else {
            if (twoPair1.get(0).equals("-1") && twoPair1.get(1).equals("-1")){
                result.add(false);
            } else {
                result.add(true);
            }
        }

        if (threeSame.equals("-1")){
            result.add(false);
        } else {
            result.add(true);
        }

        if (highestStraight.equals("-1")){
            result.add(false);
        } else {
            result.add(true);
        }

        result.add(flush);

        if (foulHouse.equals("-1")){
            result.add(false);
        } else {
            result.add(true);
        }

        if (poker.equals("-1")){
            result.add(false);
        } else {
            result.add(true);
        }

        if (sFlush.equals("-1")){
            result.add(false);
        } else {
            result.add(true);
        }

        return result;
    }

    private static String sFlush(String highestStraight, Boolean flush) {
        if (flush && !highestStraight.equals("-1")){
            return highestStraight;
        }
        return "-1";
    }

    private static List<String> poker(List<List<String>> list, String threeSame) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get(0).equals(threeSame)){
                count++;
            }
        }
        List<String> result = new ArrayList<>();
        if (count == 4){
            for (int i = 0; i < list.size(); i++) {
                if(!list.get(i).get(0).equals(threeSame)){
                    result.add(threeSame);
                    result.add(list.get(i).get(0));
                    return result;
                }
            }
        }
        result.add("-1");
        result.add("-1");
        return result;
    }

    private static List<String> fullHouse(List<List<String>> list, String threeSame) {
        String r = "-1";
        String pair = "";
        List<String> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).get(0).equals(threeSame)){
                pair = list.get(i).get(0);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get(0).equals(pair)){
                count++;
            }
        }

        if (count == 2){
            result.add(threeSame);
            result.add(pair);
            return result;
        }
        result.add("-1");
        result.add("-1");
        return result;
    }

    private static Boolean flush(List<List<String>> list) {
        String color = list.get(0).get(1);
        boolean result = true;
        for (int i = 1; i < list.size(); i++) {
            if (!list.get(i).get(1).equals(color)){
                result = false;
                break;
            }
        }
        return result;
    }

    private static String straight(List<List<String>> list) {
        String result = "-1";
        List<Integer> nums= new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nums.add(Integer.parseInt(list.get(i).get(0)));
        }
        Collections.sort(nums);

        boolean good = true;
        for (int i = 0; i <nums.size()-1; i++){
            if(!nums.get(i).equals(nums.get(i + 1) - 1)){
                good = false;
                break;
            }
        }
        if (good) result = nums.get(4).toString();

        return result;
    }

    private static List<String> threeSame(List<List<String>> list) {
        String result = "-1";
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                for (int k = 0; k < list.size(); k++) {
                    if(list.get(i).get(0).equals(list.get(j).get(0)) && list.get(i).get(0).equals(list.get(k).get(0)) && i!=k && i != j && j!=k ){
                        result = list.get(i).get(0);
                        break;

                    }
                }
            }
        }
        List<String> resultList = new ArrayList<>();
        resultList.add(result);
        if (result.equals("-1")){
            resultList.add("-1");
            resultList.add("-1");
            return resultList;
        }

        String oneLeft = "-1", twoLeft = "-1";
        boolean ok = false;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).get(0).equals(result)){
                if(!ok){
                    oneLeft = list.get(i).get(0);
                }

                if (ok){
                    twoLeft = list.get(i).get(0);
                }
                ok = true;
            }
        }

        if (Integer.parseInt(oneLeft) > Integer.parseInt(twoLeft)){
            resultList.add(oneLeft);
            resultList.add(twoLeft);
        }else{
            resultList.add(twoLeft);
            resultList.add(oneLeft);
        }


        return resultList;
    }

    private static List<String> twoPair(List<List<String>> list) {
        List<String> result = new ArrayList<>();
        String onePair = "-1";
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(list.get(i).get(0).equals(list.get(j).get(0)) && i != j && !onePair.equals(list.get(j).get(0))){
                    onePair = list.get(j).get(0);
                    result.add(onePair);
                }
            }
        }

        if (result.size() == 0) {
            result.add("-1");
            result.add("-1");
        }

        if (result.size() == 2){
            for (int i = 0; i < list.size(); i++) {
                if(!list.get(i).get(0).equals(result.get(0)) && !list.get(i).get(0).equals(result.get(1)))
                    result.add(list.get(i).get(0));
            }
        }

        return result;
    }

    private static List<String> pair(List<List<String>> list) {
        String maxPair = "-1";
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(list.get(i).get(0).equals(list.get(j).get(0)) && i != j){
                    if (Integer.parseInt(list.get(i).get(0)) > Integer.parseInt(maxPair)) {
                        maxPair = list.get(i).get(0);
                        result.add(maxPair);
                    }
                }
            }
        }

        if (result.size() == 0) result.add(maxPair);

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).get(0).equals(result.get(0))){
                result.add(list.get(i).get(0));
            }
        }

        while (result.size() == 5){
            result.add(maxPair);
        }
        return result;
    }

    private static String high(List<List<String>> list) {
        String max = "-1";
        for (int i = 0; i < list.size(); i++) {
            if(Integer.parseInt(list.get(i).get(0)) > Integer.parseInt(max)){
                max = list.get(i).get(0);
            }
        }
        return max;
    }

    private static List<List<String>> makeList(List<String> whiteHand) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < whiteHand.size(); i++){
            String suit = whiteHand.get(i).substring(0, 1);
            String value = whiteHand.get(i).substring(1, 2);
            switch (suit) {
                case "T":
                    suit = "10";
                    break;
                case "J":
                    suit = "11";
                    break;
                case "Q":
                    suit = "12";
                    break;
                case "K":
                    suit = "13";
                    break;
                case "A":
                    suit = "14";
                    break;
            }
            List<String> oneCard = new ArrayList<>();
            oneCard.add(suit);
            oneCard.add(value);
            result.add(oneCard);
        }
        return result;
    }

    private static int Highest(List<List<String>> list1, List<List<String>> list2){
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++){
            num1.add(Integer.parseInt(list1.get(i).get(0)));
            num2.add(Integer.parseInt(list2.get(i).get(0)));
        }

        Collections.sort(num1);
        Collections.sort(num2);


        if (num1.get(4) > num2.get(4)) return 1;
        if (num1.get(4) < num2.get(4)) return 0;

        if (num1.get(3) > num2.get(3)) return 1;
        if (num1.get(3) < num2.get(3)) return 0;

        if (num1.get(2) > num2.get(2)) return 1;
        if (num1.get(2) < num2.get(2)) return 0;

        if (num1.get(1) > num2.get(1)) return 1;
        if (num1.get(1) < num2.get(1)) return 0;

        if (num1.get(0) > num2.get(0)) return 1;
        if (num1.get(0) < num2.get(0)) return 0;

        return -1;
    }

    private static int Highest2(List<List<String>> list1, List<List<String>> list2){
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++){
            num1.add(Integer.parseInt(list1.get(i).get(0)));
            num2.add(Integer.parseInt(list2.get(i).get(0)));
        }

        Collections.sort(num1);
        Collections.sort(num2);
        Collections.reverse(num1);
        Collections.reverse(num2);

        for (int i=0; i < num1.size(); i++){
            if (!num1.get(i).equals(num2.get(i))){
                if (num1.get(i) > num2.get(i)) return 0;
                else return 1;
            }
        }
        return -1;
    }

    private static int Highest3(List<String> list1, List<String> list2) {
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();

        for (int i = 1; i < list1.size(); i++) {
            num1.add(Integer.parseInt(list1.get(i)));
            num2.add(Integer.parseInt(list2.get(i)));
        }

        Collections.sort(num1);
        Collections.sort(num2);
        Collections.reverse(num1);
        Collections.reverse(num2);

        for (int i = 0; i < num1.size(); i++) {
            if (!num1.get(i).equals(num2.get(i))) {
                if (num1.get(i) > num2.get(i)) return 0;
                else return 1;
            }
        }
        return -1;
    }
}
