package com.example.calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        boolean running = true;

        System.out.println("계산기를 시작합니다. ('exit' 입력 시 종료)");

        while (running) {

            try {
                // 계산 할 값 입력
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int num1 = sc.nextInt();

                System.out.print("두 번째 숫자를 입력하세요: ");
                int num2 = sc.nextInt();

                System.out.print("사칙연산 기호를 입력하세요(+, -, *, /)");
                char operator = sc.next().charAt(0);

                int result = calculator.calculate(num1, num2, operator);

                // 결과
                System.out.println("결과: " + result);
                // 지난결과
                System.out.println("지금까지의 계산 결과: " + calculator.getResults());
            } catch (RuntimeException e) {
                System.out.println("오류: " + e.getMessage());
            }

            System.out.println("메뉴: 1.계속 계산 2.첫번째 결과 삭제 3.종료");
            System.out.print("번호 입력: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    calculator.removeResult();
                    System.out.println("현재 저장된 결과: " + calculator.getResults());
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("선택 된 메뉴가 없습니다.");
                    continue;
            }
        }
        System.out.println("계산기를 종료합니다.");
    }
}
