package com.example.calculator.level3;

public enum OperatorType {
    // 속성
    ADD('+') {
        @Override
        public double apply(double num1, double num2) {
            return num1 + num2;
        }
    },
    SUB('-') {
        @Override
        public double apply(double num1, double num2) {
            return num1 - num2;
        }
    },
    MUL('*') {
        @Override
        public double apply(double num1, double num2) {
            return num1 * num2;
        }
    },
    DIV('/') {
        @Override
        public double apply(double num1, double num2) {
            if (num2 == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다.");
            }
            return num1 / num2;
        }
    };

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

    // 추상메서드
    public abstract double apply(double num1, double num2);
}
