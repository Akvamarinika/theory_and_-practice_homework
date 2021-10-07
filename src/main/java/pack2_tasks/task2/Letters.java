package pack2_tasks.task2;

import java.util.*;

public class Letters {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String consonants = "bcdfgjklmnpqstvxzhrwy";
    private static final String vowels = "aeyuoi";

    public static void main(String[] args) {
        int counterVowels = 0;
        int counterConsonants = 0;

        System.out.println("Input word in eng: ");
        String word = scanner.nextLine();

        for (Character symbol : word.toCharArray()){
            if (vowels.contains(symbol.toString().toLowerCase())){
                counterVowels++;
            }

            if (consonants.contains(symbol.toString().toLowerCase())){
                counterConsonants++;
            }
        }

        System.out.println("Consonants count: " + counterConsonants);
        System.out.println("Vowels count: " + counterVowels);
        scanner.close();
    }
}
