import java.util.*;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        while(true){
            String line = scanner.nextLine();
            
            //We read the fraction to encounter
            String[] lineInts = line.split(" ");
            Fraction fraction2Find = new Fraction(Integer.parseInt(lineInts[0]), Integer.parseInt(lineInts[1]));
            
            //If it is the fraction 1/1 we must stop
            if(fraction2Find.compareFraction(new Fraction(1,1) ) == 0){
                break;
            }
            //String of the fraction
            String res = "";

            //We initialize the three fractions(Lup/Ldown)
            Fraction L = new Fraction(0,1);
            Fraction C = new Fraction(1,1);
            Fraction R = new Fraction(1,0);

            //We apply the strategy
            while(C.compareFraction(fraction2Find) != 0){
                Fraction aux = C;
                //we compare again to select a branch to follow in the tree
                //In case the center is grater we move to the left, else to the right and update R or L respectively
                if(C.compareFraction(fraction2Find) > 0){
                    C = new Fraction(L.getNumerator() + aux.getNumerator(), L.getDenominator() + aux.getDenominator());
                    R = aux;
                    res += "L";
                }else{
                    C = new Fraction(R.getNumerator() + aux.getNumerator(), R.getDenominator() + aux.getDenominator());
                    L = aux;
                    res += "R";
                }
            }

            System.out.println(res);
        }
    }

}

class Fraction{
    private int numerator;
    private int denominator;

    public Fraction(int x, int y){
        numerator = x;
        denominator = y;
    }

    /*
    /Returns >1 when this is greater than f, 
    /0 when equal
    /and <1 when this is smaller than f.
    */
    public int compareFraction(Fraction f){
        int nA = this.numerator;
        int dA = this.denominator;
        int nB = f.getNumerator();
        int dB = f.getDenominator();
        if(dA != dB){
            //We apply common denominator method
            nA = nA * f.getDenominator();
            nB = nB * this.denominator;
            dA = dA * f.getDenominator();
            dB = dB * this.denominator;
        }
        return nA - nB;
    }

    //Returns a new Fraction with the same values as this.
    public Fraction copyFraction(){
        return new Fraction(this.numerator, this.denominator);
    }

    public void setNumerator(int x){
        this.numerator = x;
    }

    public void setDenominator(int y){
        this.denominator = y;
    }

    public int getNumerator(){
        return this.numerator;
    }

    public int getDenominator(){
        return this.denominator;
    }
}
