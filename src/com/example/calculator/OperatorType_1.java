package com.example.calculator;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum OperatorType {
    ADD('+', (num1, num2) -> num1 + num2),
    SUB('-', (num1, num2) -> num1 - num2),
    MUL('*', (num1, num2) -> num1 * num2),
    DIV('/', (num1, num2) -> {
        if (num2 == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return num1 / num2;
    });

    private final char symbol;
    private final BiFunction<Double, Double, Double> operation;

    // 생성자
    OperatorType(char symbol, BiFunction<Double, Double, Double> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    // getter
    public char getSymbol() {
        return symbol;
    }

    // public static method: char 기호를 받아서 해당하는 OpertorType Enum상수를 반환
    public static OperatorType fromSymbol(char symbol) {
        // 모든 OperatorType 상수를 순회
        for (OperatorType type : values()) {
            if (type.symbol == symbol) {
                return type;
            }
        }

        throw new IllegalArgumentException("유효하지 않은 연산 기호입니다." + symbol);
    }


    public double apply(double doubleNum1, double doubleNum2) {
        return this.operation.apply(doubleNum1, doubleNum2);
    }
}
