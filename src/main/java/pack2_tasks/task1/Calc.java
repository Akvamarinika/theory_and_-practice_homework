package pack2_tasks.task1;

import java.util.Scanner;

public class Calc {
    private static String answer = "";
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (!answer.equals("no")) {
            try {
                String[] arrStrings = input();
                Number result = calculate(arrStrings);
                printResult(arrStrings, result);
            } catch (ArithmeticException ex) {
                System.err.println("Error: " + ex.getMessage());
            }

            System.out.println("\nDo you continue? (yes/no)");
            answer = scan.nextLine();
        }
    }

    public static String[] input(){
        String[] inputStrings;

        try {
            System.out.println("Enter, please, <number1> <operator> <number2>: ");
            String line = scan.nextLine().trim();
            if (line.matches("\\d+\\.?(\\d+)?\\s+[+\\-*/]\\s+\\d+\\.?(\\d+)?")){
                inputStrings = line.split("\\s+");
            } else{
                throw new  IllegalArgumentException("Line is entered incorrectly!");
            }
        } catch (IllegalArgumentException ex){
            System.err.println("Error: " + ex.getMessage());
            Help.help();
            inputStrings = input();
        }
        return inputStrings;
    }

    public static <T> void printResult(String[] inputArgs, T result){
        System.out.printf("%s %s %s = %s",inputArgs[0], inputArgs[1], inputArgs[2], result);
    }

    public static Number calculate(String[] inputStrings) throws ArithmeticException{
        String operator = inputStrings[1];
        String num1Str = inputStrings[0];
        String num2Str = inputStrings[2];
        CalcInt calcInt = new CalcInt();
        CalcDouble calcDouble = new CalcDouble();

        if (CalcInt.isInteger(num1Str) && CalcInt.isInteger(num2Str)){
            int num1 = calcInt.convert(num1Str);
            int num2 = calcInt.convert(num2Str);

            if (operator.equals("/") && (num1 % num2) != 0){
                return calcDouble.calculate((double)num1, operator, (double)num2);
            }

            return calcInt.calculate(num1, operator, num2);
        } else {
            return calcDouble.calculate(calcDouble.convert(num1Str), operator, calcDouble.convert(num2Str));
        }
    }



}
