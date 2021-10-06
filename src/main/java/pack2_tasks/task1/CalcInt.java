package pack2_tasks.task1;

public class CalcInt implements AbstractCalc<Integer> {
    @Override
    public Integer convert(String string) {
        return Integer.parseInt(string);
    }

    @Override
    public Integer calculate(Integer num1, String operator, Integer num2) {
        switch(operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        return 0;
    }

    public static boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
