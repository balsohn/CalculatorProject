package com.example.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator_2 {

    // private로 캡슐화
    private final ArrayList<Integer> results;

    // 생성자
//    public Calculator_2(ArrayList<Integer> results) {
//        this.results = results;
//    }

    // 생성자(새로 생성해서 내보내기 위해 new ArrayList<>();
    public Calculator_2() {
        this.results = new ArrayList<>();
    }

    /**
     * 사칙연산을 수행하고 결과를 반환하며, 결과를 내부 리스트에 저장합니다.
     *
     * @param num1     첫 번째 양의 정수
     * @param num2     두 번째 양의 정수
     * @param operator 사칙연산 기호 (+, -, *, /)
     * @return 연산 결과
     * @throws IllegalArgumentException 유효하지 않은 연산자 또는 0으로 나누려고 할 때 발생시킵니다.
     */

    public int calculate(int num1, int num2, char operator) throws IllegalArgumentException {
        int result;
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
                    throw new IllegalArgumentException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 사칙연산 기호입니다.");
        }

        this.results.add(result);
        return result;
    }

    public List<Integer> getResults() {
        return new ArrayList<>(this.results);
    }

    public void setResults(ArrayList<Integer> newResults) {
        if (newResults != null) {
            // 기존 리스트를 지우고 새 요소들을 추가합니다.
            this.results.clear(); // 기존 내용 삭제
            this.results.addAll(newResults); // 새 리스트의 모든 요소 추가
            // this.results = newResults; // 참조 자체를 바꾸는 방법 (이렇게 하려면 필드에서 final 키워드 제거 필요)
        } else {
            this.results.clear(); // null이 들어오면 리스트를 비우는 것으로 처리
        }
    }

    public void removeResult() {
        if (!this.results.isEmpty()) { // 리스트가 비어있지 않다면
            this.results.remove(0); // ArrayList에서 인덱스 0번이 가장 먼저 추가된 요소입니다.
            System.out.println("가장 오래된 저장 결과 1개가 삭제되었습니다.");
        } else {
            System.out.println("삭제할 결과가 없습니다.");
        }
    }

    public void inquiryResults() {
        if (this.results.isEmpty()) { // 리스트가 비어있다면
            System.out.println("저장된 연산 결과가 없습니다.");
            return; // 메서드 종료
        }
        System.out.println("저장된 연산 결과 목록:");
        // 향상된 for문(enhanced for loop)을 사용하여 리스트의 모든 요소를 순회하며 출력
        for (int res : this.results) {
            System.out.print(res + " ");
        }
        System.out.println(); // 결과를 다 출력한 후 줄바꿈
    }

}
