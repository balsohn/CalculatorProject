package com.example.calculator.level3;

public enum OperatorType {
    // 속성
    ADD('+'),
    SUB('-'),
    MUL('*'),
    DIV('/');

    private final char symbol;



    // 생성자
    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    // getter
    public char getSymbol() {
        return symbol;
    }

    // 기호로 연산자 찾기
    public static OperatorType fromSymbol(char symbol) {
        for (OperatorType type : values()) {
            if (type.symbol == symbol) {
                return type;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 연산 기호입니다. ");
    }

    // 연산 수행 메서드
    public double apply(double num1, double num2) {
        switch (this) {
            case ADD:
                return num1 + num2;
            case SUB:
                return num1 - num2;
            case MUL:
                return num1 * num2;
            case DIV:
                if (num2 == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("알 수 없는 연산자입니다.");

        }
    }
}
