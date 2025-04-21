package com.example.calculator.review;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator {

    // 속성
    private final ArrayList<Double> results;

    // 생성자
    public ArithmeticCalculator() {
        this.results = new ArrayList<>();
    }

    // 계산 수행 및 결과 저장
    public double caclulate(double num1, double num2, char symbol) {
        double result = performCalculation(num1, num2, symbol);

        // 결과저장
        this.results.add(result);
        return result;
    }

    private static double performCalculation(double num1, double num2, char symbol) {
        double result;
        switch (symbol) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 연산 기호입니다.");
        }
        return result;
    }

    // 결과 조회 메서드
    public List<Double> getResults() {
        return new ArrayList<>(this.results); // 방어적 복사본 반환
    }

    // 특정 값보다 큰 결과 조회
    public List<Double> getGreaterResults(double threshold) {
        return this.results.stream()
                .filter(r -> r > threshold)
                .toList();
    }

    // 결과 삭제 메서드
    public void removeResult() {
        if (this.results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        } else {
            this.results.remove(0);
            System.out.println("가장 오래된 결과 1개가 삭제되었습니다.");
        }
    }

    // 전체 결과 삭제 메서드
    public void clearResults() {
        if (this.results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        } else {
            this.results.clear();
            System.out.println("모든 결과가 삭제 되었습니다.");
        }
    }
}
