package pack2_tasks.task6;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CoefficientTanimoto {
    private  static Set<Character> intersectSymbols = new HashSet<>();

    public static void main(String[] args) {
        Set<Character> setWord1;
        Set<Character> setWord2;
        Double coeff;

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter word 1: ");
            String word1 = scanner.nextLine().trim().toUpperCase();

            System.out.println("Enter word 2: ");
            String word2 = scanner.nextLine().trim().toUpperCase();

            setWord1 = fillingSet(word1);
            setWord2 = fillingSet(word2);

            intersectSymbols.addAll(setWord1);
            intersectSymbols.retainAll(setWord2);

            coeff = calculateCoeffTanimoto(setWord1.size(), setWord2.size(), intersectSymbols.size());

            System.out.println("Set word 1: " + setWord1);
            System.out.println("Set word 2: " + setWord2);
            System.out.println("Intersection word1 AND word2: " + intersectSymbols);
            System.out.println("Intersection count symbols: " + intersectSymbols.size());
            System.out.println("Coefficient Tanimoto: " + coeff);
        }
    }

    public static Set<Character> fillingSet(String word){
        return word
               .chars()
               .mapToObj(symbol -> (char) symbol)
               .filter(symbol -> !symbol.equals(' '))
               .collect(Collectors.toSet());
    }

    public static Double calculateCoeffTanimoto(int countSymbolsWord1, int countSymbolsWord2, int countIntersect){
        return (double) countIntersect / (countSymbolsWord1 + countSymbolsWord2 - countIntersect);
    }
}
