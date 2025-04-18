package com.example.calculator.level3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticCalculator<T extends Number> {

    // 속성
    private final ArrayList<Number> results;

    // 생성자
    public ArithmeticCalculator() {
        this.results = new ArrayList<>();
    }

    // 기능
    // getter
    public List<Number> getResults() {
        return new ArrayList<Number>(this.results);
    }

    // enum을 이용한 계산
    public Number calculate(T num1, T num2, OperatorType operator) {
        double doubleNum1 = num1.doubleValue();
        double doubleNum2 = num2.doubleValue();

        double result = operator.apply(doubleNum1, doubleNum2);

        System.out.println((int)result);
        // 소수점 이하가 0인지 확인하여 정수면 Integer로 저장
        if (result == (int)result) {
            this.results.add((int)result);
            return (int)result;
        } else {
            this.results.add(result);
            return result;
        }
    }

    // 오래된 결과 삭제
    public void removeResult() {
        if (this.results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        } else {
            this.results.remove(0);
            System.out.println("가장 오래된 결과 1개가 삭제되었습니다.");
        }
    }

    // 모든 결과 삭제
    public void clearResults() {
        if (this.results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        } else {
            this.results.clear();
            System.out.println("모든 결과가 삭제 되었습니다.");
        }
    }

    // 특정값보다 큰 결과 필터링
    public List<Number> getGreaterResult(double threshold) {
        return this.results.stream()
                .filter(result -> result.doubleValue() > threshold)
                .collect(Collectors.toList());
    }

    // 짝수결과 필터링
    public List<Number> getEvenResults() {
        return this.results.stream()
                .filter(result -> result.doubleValue() % 2 == 0)
                .collect(Collectors.toList());
    }
}
