package com.example.calculator;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    // 연산 결과를 저장하는 컬렉션 타입 필드 (캡슐화)
    private List<Integer> results;

    // 생성자
    public Calculator() {
        this.results = new ArrayList<>();
    }

    // 계산 메서드
    public int calculate(int num1, int num2, char operator) {

        int result = 0;

        // 연산처리
        switch (operator) {
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
                    throw new RuntimeException("나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                throw new RuntimeException("올바른 연산자가 아닙니다.");
        }

        results.add(result);
        return result;
    }

    // getter
    public List<Integer> getResults() {
        return new ArrayList<>(results);
    }

    public void removeResult() {
        if (results.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
        } else {
            results.remove(0);
            System.out.println("가장 먼저 계산된 결과가 삭제되었습니다.");
        }
    }

}
