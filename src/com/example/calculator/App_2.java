package com.example.calculator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator_2 calculator2 = new Calculator_2();

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = 0;
            boolean numInput = false;

            while(!numInput) {
                try {
                    num1 = sc.nextInt();
                    numInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("오류: 숫자를 입력해야 합니다. 다시 입력해주세요.");
                    sc.next();
                }
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = 0;
            numInput = false;

            while(!numInput) {
                try {
                    num2 = sc.nextInt();
                    numInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("오류: 숫자를 입력해야 합니다. 다시 입력해주세요.");
                    sc.next();
                }
            }

            System.out.print("사친연산 기호(+, -, *, /)를 입력하세요: ");
            char operator = sc.next().charAt(0);

            try {
                int result = calculator2.calculate(num1, num2, operator); // 예외 발생 가능성 있음
                System.out.println("결과: " + result);
            } catch (IllegalArgumentException e) { // calculate에서 던진 예외 잡기
                System.out.println(e.getMessage()); // 예외 메시지 출력
            }

            // 추가 기능 사용 여부 확인 (결과 조회, 삭제)
            System.out.print("저장된 결과 조회(1), 가장 오래된 결과 삭제(2), 계속 계산(아무 키나 입력), 종료(exit): ");
            String command = sc.next(); // 사용자 명령어 입력 받기

            if (command.equalsIgnoreCase("1")) {
                // "inquiry" 입력 시, Calculator 객체의 inquiryResults 메서드 호출
                calculator2.inquiryResults();
                // 아래는 Getter 사용 예시 (결과를 받아와 직접 출력할 수도 있음)
//                List<Integer> storedResults = calculator2.getResults(); // Getter 호출
//                System.out.println("현재까지 저장된 결과: " + storedResults);
            } else if (command.equalsIgnoreCase("2")) {
                // "remove" 입력 시, Calculator 객체의 removeResult 메서드 호출
                calculator2.removeResult();
                calculator2.inquiryResults();
            } else if (command.equalsIgnoreCase("exit")) {
                break;
            }

        }

        System.out.println("계산기를 종료합니다.");
        sc.close();
    }

}
