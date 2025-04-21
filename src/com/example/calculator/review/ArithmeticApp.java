package com.example.calculator.review;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArithmeticApp {

    public static void main(String[] args) {

        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("===== 계산기 =====");

        while (running) {
            System.out.println("\n[메뉴 선택]");
            System.out.println("1: 계산하기");
            System.out.println("2: 저장된 모든 결과 보기");
            System.out.println("3: 특정 값보다 큰 결과 보기");
            System.out.println("4: 가장 오래된 결과 삭제");
            System.out.println("5: 모든 결과 삭제");
            System.out.println("6: 종료");
            System.out.print("선택: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 메뉴에 따라 입력해주세요.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
//                    System.out.println("1: 계산하기");

                    double num1;
                    try {
                        System.out.print("첫번째 숫자를 입력해주세요: ");
                        num1 = sc.nextDouble();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("숫자를 올바르게 입력해주세요.");
                        continue;
                    }

                    double num2;
                    try {
                        System.out.print("두번째 숫자를 입력해주세요: ");
                        num2 = sc.nextDouble();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("숫자를 올바르게 입력해주세요.");
                        continue;
                    }


                    System.out.print("사칙연산 기호를 입력하세요. (+, -, *, /) : ");
                    String operatorInput = sc.nextLine();
                    if (operatorInput.length() != 1) {
                        System.out.println("연산자 기호는 한 글자만 적어주세요.");
                        continue;
                    }
                    char symbol = operatorInput.charAt(0);

                    double result = arithmeticCalculator.caclulate(num1, num2, symbol);
                    System.out.println("계산 결과: " + result);

                    break;
                case 2:
//                    System.out.println("2: 저장된 모든 결과 보기");
                    List<Double> allResults = arithmeticCalculator.getResults();
                    allResults(allResults);

                    break;
                case 3:
//                    System.out.println("3: 특정 값보다 큰 결과 보기");
                    System.out.print("특정 값을 입력해주세요: ");
                    double threshold = sc.nextDouble();
                    sc.nextLine();

                    List<Double> greater = arithmeticCalculator.getGreaterResults(threshold);
                    if (greater.isEmpty()) {
                        System.out.println("비교할 값이 없습니다.");
                    } else {
                        System.out.println("===== " + threshold + "보다 큰값 =====");
                        greater.forEach(System.out::println);
                        System.out.println("==========================");
                    }

                    break;
                case 4:
//                    System.out.println("4: 가장 오래된 결과 삭제");
                    arithmeticCalculator.removeResult();
                    List<Double> results = arithmeticCalculator.getResults();
                    allResults(results);
                    break;
                case 5:
//                    System.out.println("5: 모든 결과 삭제");
                    arithmeticCalculator.clearResults();
                    break;
                case 6:
//                    System.out.println("6: 종료");
                    running = false;
                    break;
                default:
                    System.out.println("올바른 메뉴를 입력해주세요. 1~6사이의 숫자를 입력해주세요.");
                    break;
            }
        }

        sc.close();
        System.out.println("계산기를 종료합니다.");
    }

    // 결과 출력 로직
    private static void allResults(List<Double> results) {
        if (results.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
        } else {
            System.out.println("===== 모든 결과 =====");
            results.forEach(System.out::println);
            System.out.println("====================");
        }
    }
}
