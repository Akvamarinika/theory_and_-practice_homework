package pack2_tasks.task5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountSymbols {
    private static Map<String, Integer> symbolsCount = new HashMap<>();

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new File("./src/main/resources/lorem.txt"))) {
            while(scanner.hasNext()){
                char[] wordFromChars = scanner.next().toCharArray();

                for (Character symbol : wordFromChars){
                    String letter = symbol.toString();
                    if (symbolsCount.containsKey(letter)){
                        symbolsCount.put(letter, symbolsCount.get(letter) + 1);
                    } else {
                        symbolsCount.put(letter, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }


        System.out.println("Frequency symbols in file:\n" + symbolsCount);
    }
}
