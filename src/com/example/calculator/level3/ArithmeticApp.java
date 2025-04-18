package com.example.calculator.level3;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArithmeticApp {

    public static void main(String[] args) {

        ArithmeticCalculator<Number> arithmeticCalculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("===== 계산기 =====");

        while (running) {
            System.out.println("\n[메뉴 선택]");
            System.out.println("1: 계산하기 (Enum 사용)");
            System.out.println("2: 저장된 모든 결과 보기");
            System.out.println("3: 특정 값보다 큰 결과 보기");
            System.out.println("4: 가장 오래된 결과 삭제");
            System.out.println("5: 모든 결과 삭제");
            System.out.println("6: 종료");
            System.out.print("선택: ");

            // 사용자 입력처리
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 메뉴에 따라 입력해주세요.");
                sc.nextLine();
                continue;
            }

            // 선택에 따른 작업 수행
            switch (choice) {
                case 1:
                    MenuOneCalculator(arithmeticCalculator, sc);
                    break;
                case 2:
                    allResults(arithmeticCalculator);
                    break;
                case 3:
                    getGreaterResult(arithmeticCalculator, sc);
                    break;
                case 4:
                    arithmeticCalculator.removeResult();
                    allResults(arithmeticCalculator);
                    break;
                case 5:
                    arithmeticCalculator.clearResults();
                    allResults(arithmeticCalculator);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 1~6 사이의 숫자를 입력해주세요.");
            }
        }

        sc.close();
        System.out.println("계산기를 종료합니다.");
    }

    public static void MenuOneCalculator(ArithmeticCalculator<Number> calculator, Scanner sc) {
        try {
            System.out.println("첫 번째 숫자를 입력하세요: ");
            double num1 = sc.nextDouble();
            sc.nextLine();

            System.out.println("두 번째 숫자를 입력하세요: ");
            double num2 = sc.nextDouble();
            sc.nextLine();

            System.out.println("사칙연산 기호를 입력하세요. (+, -, *, /)");
            String operatorInput = sc.nextLine();

            if (operatorInput.length() != 1) {
                System.out.println("연산자 기호는 한 글자만 적어주세요.");
                return;
            }

            char symbol = operatorInput.charAt(0);

            OperatorType operator = OperatorType.fromSymbol(symbol);
            Number result = calculator.calculate(num1, num2, operator);
            System.out.println("계산결과: " + result);

        } catch (InputMismatchException e) {
            System.out.println("오류: 잘못된 숫자 형식입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("오류: " + e.getMessage());
        }
    }

    // 모든 결과 출력
    public static void allResults(ArithmeticCalculator<Number> calculator) {
        List<Number> results = calculator.getResults();
        if (results.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
        } else {
            System.out.println("---- 저장된 결과 ----");
            results.forEach(System.out::println);
            System.out.println("-------------------");
        }
    }

    // 특정값보다 큰 결과 출력
    public static void getGreaterResult(ArithmeticCalculator<Number> calculator, Scanner sc) {
        if(calculator.getResults().isEmpty()) {
            System.out.println("조회할 결과가 없습니다. 먼저 계산을 수행해주세요.");
            return;
        }

        System.out.print("기준 값을 입력해주세요: ");
        double threshold = sc.nextDouble();
        sc.nextLine();

        List<Number> filterResults = calculator.getGreaterResult(threshold);

        try {
            if (filterResults.isEmpty()) {
                System.out.println(threshold + "보다 큰 값이 없습니다.");
            } else {
                System.out.println("-----" + threshold + "보다 큰 결과 -----");
                filterResults.forEach(System.out::println);
                System.out.println("------------------------");
            }
        } catch (InputMismatchException e) {
            System.out.println("오류: 잘못된 숫자 형식입니다.");
            sc.nextLine();
        }

    }
}
