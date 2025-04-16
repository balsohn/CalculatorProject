package com.example.calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("계산기를 시작합니다. ('exit' 입력 시 종료)");

        while (running) {

            // 계산 할 값 입력
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();

            System.out.print("사칙연산 기호를 입력하세요(+, -, *, /)");
            char operator = sc.next().charAt(0);

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
                        System.out.println("나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                        System.out.println("더 계산하시겠습니까? ('exit' 입력시 종료)");
                        String choice = sc.next();
                        if (choice.equals("exit")) {
                            running = false;
                        }
                        continue;
                    }
                    result = num1 / num2;
                    break;
                default:
                    System.out.println("올바른 연산자가 아닙니다.");
                    System.out.println("더 계산하시겠습니까? ('exit' 입력시 종료)");
                    String choice = sc.next();
                    if (choice.equals("exit")) {
                        running = false;
                    }
                    continue;
            }

            System.out.println("결과: " + result);

            System.out.println("더 계산하시겠습니까? ('exit' 입력시 종료)");
            String choice = sc.next();
            if (choice.equals("exit")) {
                running = false;
            }
        }
        System.out.println("계산기를 종료합니다.");
    }
}
