package pack3_task;
import org.mariuszgromada.math.mxparser.Expression;
import java.util.*;

public class Main {
    private static Stack<String> stack = new Stack<>();
    private static Set<String> operators = new HashSet<>();

    public static void main(String[] args) {
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter, please, prefix expression: ");
            input = scanner.nextLine();

            if (input.matches("^([+\\-*/]\\s+)+.+\\d+(\\s+)?$")){
                checkInputCount(input);
                initOperators();
                String exp = prefixToInfix(input);
                double result = calcExpression(exp);
                System.out.println("In infix: " + exp + " = " + result);
             } else{
                System.err.println("Error: Invalid input! " + input + " not a prefix expression.");
            }

        }catch (IllegalArgumentException ex){
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static double calcExpression(String expression) throws IllegalArgumentException{
        Expression exp = new Expression(expression);
        double result = exp.calculate();

        if (Double.isNaN(result)){
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }

        return result;
    }

    public static String prefixToInfix(String input){
        String[] inputArr = input.trim().split("\\s+");

        for (int i = inputArr.length-1; i >= 0; i--){
            String elem = inputArr[i];

            if(elem.matches("\\d+")){
                stack.add(elem);
            } else {
                if (operators.contains(elem)){
                    String expStr1 = stack.pop();
                    String expStr2 = stack.pop();

                    if (!expStr1.matches("\\d+") || !expStr2.matches("\\d+")) {
                        expStr1 = "(" + expStr1 + ")";
                        expStr2 = "(" + expStr2 + ")";
                    }

                    stack.add(expStr1 + elem + expStr2);
                } else {
                    System.err.println("Error: " + elem + "Not a number or operator / * - + Will be skipped!");
                }
            }
        }

       return stack.pop();
    }

    private static void initOperators(){
        operators.add("-");
        operators.add("+");
        operators.add("/");
        operators.add("*");
    }

    private static void checkInputCount(String input) throws IllegalArgumentException{
        String[] inputArr = input.trim().split("\\s+");
        long countNum = Arrays.stream(inputArr)
                .filter(elem -> elem.matches("\\d+"))
                .count();

        if (countNum <= inputArr.length - countNum){
            throw new IllegalArgumentException("Number of operators is greater than or equal to count of numbers!");
        }
    }

}
