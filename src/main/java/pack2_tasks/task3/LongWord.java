package pack2_tasks.task3;
import java.io.*;

public class LongWord {
    private static String longWord = "";
    public static void main(String[] args) {
        String line;

        try(BufferedReader buffReader = new BufferedReader(new FileReader("./src/main/resources/words.txt"))) {
            while ((line = buffReader.readLine()) != null){
                if (line.trim().length() > longWord.length()){
                    longWord = line;
                }
            }
        } catch (IOException ex){
            System.err.println(ex.getMessage());
        }

        System.out.println("Long word in file: " + longWord);
    }
}
