package pack2_tasks.task1;

public interface AbstractCalc<T extends Number>{
    T convert(String expression);
    T calculate(T num1, String operator, T num2);
}
