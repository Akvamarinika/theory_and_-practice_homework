package pack4_task;

import java.util.Scanner;

public class Input {
    public static final String INPUT_NUMBER = "Введите число для текущей ячейки памяти: ";
    public static final String INPUT_CHAR = "Введите символ для текущей ячейки памяти: ";
    public static final String MSG_ERR_TYPE = "Некорректный ввод. Пожалуйста, введите целое число.";

    public static int numberInput(){
        int input;
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println(INPUT_NUMBER);

            if (scanner.hasNextInt()) {
                input = scanner.nextInt() ;
            } else {
                System.err.println(MSG_ERR_TYPE);
                input = numberInput();
            }

        }
        return input;
    }

    public static char charInput(){
        char symbol;
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println(INPUT_CHAR);
            String str = scanner.nextLine();
            symbol = str.charAt(0);
        }
        return symbol;
    }
}
