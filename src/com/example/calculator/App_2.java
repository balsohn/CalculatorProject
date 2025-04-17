package com.example.calculator;

import java.util.Scanner;

public class App_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();

            System.out.print("사친연산 기호(+, -, *, /)를 입력하세요: ");
            char operator = sc.next().charAt(0);

            int result = 0;
            boolean success = true;

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
                        System.out.println("오류: 나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                        success = false;
                    } else {
                        result = num1 / num2;
                    }
                    break;
                default:
                    System.out.println("오류: 유효하지 않은 연산 기호입니다.");
                    success = false;
                    break;
            }

            if(success) {
                System.out.println("결과: " + result);
            }

            System.out.println("더 계산하시겠습니까? (exit 입력시 종료)");
            String input = sc.next();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }
        }

        System.out.println("계산기를 종료합니다.");
        sc.close();
    }

}
