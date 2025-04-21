package com.example.calculator.review;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CalculatorMessy {

    public static void main(String[] args) {
        // 결과 저장을 위한 리스트도 main 안에 바로 선언
        ArrayList<Double> results = new ArrayList<>();
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
                        continue;
                    }
                    char symbol = operatorInput.charAt(0);

                    try {
                        double result = performCalculation(num1, num2, symbol);
                        System.out.println("계산 결과: " + result);
                        results.add(result);
                    } catch (ArithmeticException | IllegalArgumentException e) {
                        System.out.println("오류: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("오류: 잘못된 숫자 형식입니다.");
                    }

                    // 계산방식 performCalculation 으로 이동
//                    double result;
//                    switch (symbol) {
//                        case '+':
//                            result = num1 + num2;
//                            break;
//                        case '-':
//                            result = num1 - num2;
//                            break;
//                        case '*':
//                            result = num1 * num2;
//                            break;
//                        case '/':
//                            if (num2 == 0) {
//                                System.out.println("오류: 0으로 나눌 수 없습니다.");
//                                continue;
//                            }
//                            result = num1 / num2;
//                            break;
//                        default:
//                            System.out.println("오류: 유효하지 않은 연산 기호입니다.");
//                            continue;
//                    }
//
//                    System.out.println("계산된 결과: " + result);
//                    results.add(result);
//                    break;

                case 2:
                    // 모든 결과 로직 보기
                    if (results.isEmpty()) {
                        System.out.println("저장된 결과가 없습니다.");
                    } else {
                        System.out.println("---- 저장된 결과 ----");
                        results.forEach(System.out::println);
                        System.out.println("--------------------");
                    }
                    break;

                case 3:
                    // 특정 값보다 큰 결과 보기 로직
                    if (results.isEmpty()) {
                        System.out.println("조회할 결과가 없습니다.");
                        continue;
                    }
                    System.out.print("기준 값을 입력해주세요: ");

                    double threshold = sc.nextDouble();
                    sc.nextLine();
                    List<Double> filterResults = results.stream()
                            .filter(r -> r > threshold)
                            .collect(Collectors.toList());

                    if (filterResults.isEmpty()) {
                        System.out.println(threshold + " 보다 큰 값이 없습니다.");
                    } else {
                        System.out.println("----- " + threshold + "보다 큰 값 ----");
                        filterResults.forEach(System.out::println);
                        System.out.println("-------------------------------");
                    }
                    break;

                case 4:
                    // 가장 오래된 결과 삭제 로직
                    if (results.isEmpty()) {
                        System.out.println("삭제할 결과가 없습니다.");
                        continue;
                    } else {
                        results.remove(0);
                        System.out.println("가장 오래된 결과값 1개가 삭제되었습니다.");
                        System.out.println("---- 저장된 결과 ----");
                        results.forEach(System.out::println);
                        System.out.println("--------------------");
                    }
                    break;

                case 5:
                    // 모든 결과 삭제 로직
                    if (results.isEmpty()) {
                        System.out.println("삭제할 결과가 없습니다.");
                    } else {
                        results.clear();
                        System.out.println("모든 결과가 삭제되었습니다.");
                    }

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

}
