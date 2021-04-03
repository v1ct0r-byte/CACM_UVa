import java.util.*;

public class Main{

    public static void main(String[] args){
        //We start reading the imput
        Scanner scanner = new Scanner(System.in);
        int numberOfWords = scanner.nextInt();
        scanner.nextLine();

        //We read all non encrypted words
        String[] arrayOfWords = new String[numberOfWords];
        for(int i = 0; i < numberOfWords; i++){
            arrayOfWords[i] = scanner.nextLine();
        }

        //We read a line of encrypted text (composed of encrypted words)
        String encryptedText = scanner.nextLine();
        //And start decrypting all lines
        while(scanner.hasNextLine()){
            String result = decryptLine(encryptedText.split(" "), arrayOfWords);
            System.out.println(result);    
        }
        scanner.close();
    }

    public static String decryptLine(String[] arrayOfEncryptedWords, String[] arrayOfWords){
        String decryptedLine = "";
        for(int s = 0;s < arrayOfEncryptedWords.length; s++){
            decryptedLine += decryptWord(arrayOfEncryptedWords[s], arrayOfWords) + " ";
        }
        return decryptedLine;
    }

    public static String decryptWord(String encryptedWord, String[] arrayOfWords){
        
        return "";
    }
}