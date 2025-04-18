package com.example.calculator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticCalculator<T extends Number> {

    private final ArrayList<Double> results;

    //생성자
    public ArithmeticCalculator() {
        this.results = new ArrayList<>();
    }

    // getter
    public List<Double> getResults() {
        return new ArrayList<Double>(this.results);
    }

    public double calculate(T num1, T num2, OperatorType operator) {
        double doubleNum1 = num1.doubleValue();
        double doubleNum2 = num2.doubleValue();

        double result = operator.apply(doubleNum1, doubleNum2);

        this.results.add(result);

        return result;
    }

    public void removeFirstResult() {
        if (this.results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        } else {
            this.results.remove(0);
            System.out.println("가장 오래된 결과 1개가 삭제 되었습니다.");
        }
    }

    public void clearResults() {
        this.results.clear();
        System.out.println("저장된 모든 결과가 삭제 되었습니다.");
    }

    public List<Double> getGreaterResult(double threshold) {
        return this.results.stream()
                .filter(result -> result > threshold)
                .collect(Collectors.toList());
    }

    public List<Double> getEvenResults() {
        return this.results.stream()
                .filter(result -> result % 2 == 0)
                .collect(Collectors.toList());
    }

}
