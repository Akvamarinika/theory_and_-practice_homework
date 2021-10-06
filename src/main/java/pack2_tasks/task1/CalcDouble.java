package pack2_tasks.task1;

public class CalcDouble implements AbstractCalc<Double> {
    @Override
    public Double convert(String string) {
        return Double.parseDouble(string);
    }

    @Override
    public Double calculate(Double num1, String operator, Double num2) {
        switch(operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0.0;
        }
    }
}
