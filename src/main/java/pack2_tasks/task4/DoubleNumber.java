package pack2_tasks.task4;
import java.util.ArrayList;
import java.util.List;

public class DoubleNumber {
    private static List<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            Double num = (Math.random() * (200 + 1)) - 100;
            numbers.add(num);
        }

        System.out.println("Before sort: \n" + numbers + "\n");

        numbers.sort((num1, num2) -> {
            double fractionalPart1 = Math.abs(num1 % 1);
            double fractionalPart2 = Math.abs(num2 % 1);
            double eps = 0.000000001;
            if (fractionalPart1 > fractionalPart2){
                return 1;
            } else if (Math.abs(fractionalPart1 - fractionalPart2) <= eps){
                return 0;
            }
            return -1;
        });

        System.out.println("After sort (sort by increasing fractional part): \n" + numbers);
    }
}
