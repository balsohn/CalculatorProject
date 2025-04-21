# 계산기 프로젝트

## 계산기 프로젝트 - Level 3 구현


<b>📌 개요</b>

이 문서는 Java를 사용한 콘솔 기반 계산기의 Level 3 구현에 대한 설명입니다. <br>
Level 3에서는 Enum, 제네릭, 람다 & 스트림과 같은 Java의 고급 기능을 적용하여 계산기를 고도화했습니다.


<details>
<summary><b>📋 요구사항</b> (클릭하여 펼치기/접기)</summary>
<br>

- Enum 타입을 활용하여 연산자 타입에 대한 정보 관리 및 계산기에 적용
- Enum에 추상 메서드를 적용하여 각 연산자별 계산 로직 구현
- 제네릭을 사용하여 다양한 숫자 타입(정수, 실수) 지원
- 람다와 스트림을 활용한 결과값 필터링 기능 구현
- 메뉴 시스템을 통한 사용자 인터페이스 제공
</details>

<details>
<summary><b>🔍 구현 단계</b> (클릭하여 펼치기/접기)</summary>

### 1. Enum 클래스 구현
- 연산자 타입(ADD, SUB, MUL, DIV)을 Enum으로 정의
- 각 연산자에 해당하는 기호(+, -, *, /) 매핑
- 추상 메서드를 사용하여 각 연산자별 계산 로직 구현
- 기호로부터 연산자 Enum을 찾는 메서드 구현

### 2. 제네릭 계산기 클래스 구현
- Number 타입과 그 하위 타입을 지원하는 제네릭 타입 매개변수 적용
- 다양한 숫자 타입(Integer, Double 등)을 처리할 수 있는 계산 메서드 구현
- 결과값을 정수 또는 실수로 적절히 변환하여 저장하는 로직 추가
- 문자 기호를 직접 받아서 처리하는 편의 메서드 구현

### 3. 람다와 스트림 적용
- 저장된 결과 중 특정 값보다 큰 결과 필터링 기능 구현
- 짝수 결과만 필터링하는 기능 구현
- 스트림 API를 활용한 결과 컬렉션 처리
- 메서드 참조를 통한 결과 출력 구현

### 4. 메뉴 시스템 구현
- 다양한 기능을 제공하는 메뉴 인터페이스 구현
- 계산, 결과 조회, 결과 필터링, 결과 삭제 등의 기능 추가
- 사용자 입력 처리 및 예외 상황 대응
</details>

<details>
<summary><b>📝 주요 클래스 및 메서드</b> (클릭하여 펼치기/접기)</summary>

### OperatorType.java
```java
public enum OperatorType {
    // 각 연산자가 자신만의 계산 방법을 구현
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
            if (type.getSymbol() == symbol) {
                return type;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 연산 기호입니다.");
    }

    // 추상 메서드: 각 연산자가 반드시 구현해야 함
    public abstract double apply(double num1, double num2);
}
```

### ArithmeticCalculator.java
```java
public class ArithmeticCalculator<T extends Number> {
    // 속성
    private final ArrayList<Number> results;

    // 생성자
    public ArithmeticCalculator() {
        this.results = new ArrayList<>();
    }

    // getter
    public List<Number> getResults() {
        return new ArrayList<Number>(this.results);
    }

    // enum을 이용한 계산
    public Number calculate(T num1, T num2, OperatorType operator) {
        double doubleNum1 = num1.doubleValue();
        double doubleNum2 = num2.doubleValue();

        double result = operator.apply(doubleNum1, doubleNum2);

        // 소수점 이하가 0인지 확인하여 정수면 Integer로 저장
        if (result == (int)result) {
            this.results.add((int)result);
            return (int)result;
        } else {
            this.results.add(result);
            return result;
        }
    }
    
    // 문자 기호를 통한 계산 (편의 메서드)
    public Number calculate(T num1, T num2, char symbol) {
        OperatorType operator = OperatorType.fromSymbol(symbol);
        return calculate(num1, num2, operator);
    }

    // 오래된 결과 삭제
    public void removeResult() {
        if (this.results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        } else {
            this.results.remove(0);
            System.out.println("가장 오래된 결과 1개가 삭제되었습니다.");
        }
    }

    // 모든 결과 삭제
    public void clearResults() {
        if (this.results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        } else {
            this.results.clear();
            System.out.println("모든 결과가 삭제 되었습니다.");
        }
    }

    // 특정값보다 큰 결과 필터링
    public List<Number> getGreaterResult(double threshold) {
        return this.results.stream()
                .filter(result -> result.doubleValue() > threshold)
                .collect(Collectors.toList());
    }

    // 짝수결과 필터링
    public List<Number> getEvenResults() {
        return this.results.stream()
                .filter(result -> result.doubleValue() % 2 == 0)
                .collect(Collectors.toList());
    }
}
```

### ArithmeticApp.java
```java
public class ArithmeticApp {
    public static void main(String[] args) {
        ArithmeticCalculator<Number> calculator = new ArithmeticCalculator<>();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("===== 계산기 =====");

        while (running) {
            System.out.println("\n[메뉴 선택]");
            System.out.println("1: 계산하기 (Enum 추상 메서드 사용)");
            System.out.println("2: 저장된 모든 결과 보기");
            System.out.println("3: 특정 값보다 큰 결과 보기");
            System.out.println("4: 가장 오래된 결과 삭제");
            System.out.println("5: 모든 결과 삭제");
            System.out.println("6: 짝수 결과만 보기");
            System.out.println("7: 종료");
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
                    performCalculation(calculator, sc);
                    break;
                case 2:
                    displayResults(calculator);
                    break;
                case 3:
                    displayGreaterResults(calculator, sc);
                    break;
                case 4:
                    calculator.removeResult();
                    displayResults(calculator);
                    break;
                case 5:
                    calculator.clearResults();
                    break;
                case 6:
                    displayEvenResults(calculator);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 1~7 사이의 숫자를 입력해주세요.");
            }
        }

        sc.close();
        System.out.println("계산기를 종료합니다.");
    }
    
    // 구현 메서드들...
}
```
</details>

<details>
<summary><b>⚠️ 구현 시 어려웠던 부분과 해결 방법</b> (클릭하여 펼치기/접기)</summary>

### 1. Enum에 추상 메서드 적용하기

**문제점**: 연산자별로 다른 계산 로직을 어떻게 Enum에 적용할지 어려웠습니다.

**원인**: 일반적으로 Enum은 상수 집합으로만 생각하기 쉬우나, Java에서는 Enum도 클래스의 일종으로 메서드를 가질 수 있습니다.

**해결책**: Enum에 추상 메서드를 선언하고 각 상수에서 이를 구현하는 방식을 사용했습니다. 이를 통해 각 연산자의 로직을 캡슐화할 수 있었습니다.

```java
public abstract double apply(double num1, double num2);

ADD('+') {
    @Override
    public double apply(double num1, double num2) {
        return num1 + num2;
    }
}
```

### 2. 제네릭과 Number 타입 변환

**문제점**: 제네릭으로 받은 숫자 타입을 어떻게 연산에 사용할지 어려웠습니다.

**원인**: 제네릭 타입 매개변수는 객체 타입만 가능하며, 기본 타입에 대한 연산이 불가능합니다.

**해결책**: Number 클래스의 doubleValue() 메서드를 활용해 모든 숫자를 double로 변환한 후 연산하고, 결과가 정수인 경우는 Integer로 저장하는 방식을 사용했습니다.

```java
double doubleNum1 = num1.doubleValue();
double doubleNum2 = num2.doubleValue();
double result = operator.apply(doubleNum1, doubleNum2);

// 정수 여부 확인
if (result == (int)result) {
    this.results.add((int)result);
    return (int)result;
}
```

### 3. 람다와 스트림을 활용한 결과 필터링

**문제점**: Number 타입의 컬렉션에서 특정 조건을 만족하는 요소를 필터링하는 방법이 어려웠습니다.

**원인**: Number는 추상 클래스이며, 직접적인 산술 연산자 사용이 불가능합니다.

**해결책**: Stream API와 람다식을 활용해 필터링 로직을 간결하게 구현했습니다. doubleValue() 메서드를 사용해 타입 변환 문제를 해결했습니다.

```java
return this.results.stream()
        .filter(result -> result.doubleValue() > threshold)
        .collect(Collectors.toList());
```
</details>

<details>
<summary><b>▶️ 실행 방법</b> (클릭하여 펼치기/접기)</summary>

1. 프로젝트를 클론합니다.
2. Java 컴파일러로 소스 코드를 컴파일합니다.
3. ArithmeticApp 클래스를 실행합니다.
4. 메뉴에서 원하는 기능을 선택하여 사용합니다:
   - 1: 계산하기 (Enum 추상 메서드 사용)
   - 2: 저장된 모든 결과 보기
   - 3: 특정 값보다 큰 결과 보기
   - 4: 가장 오래된 결과 삭제
   - 5: 모든 결과 삭제
   - 6: 짝수 결과만 보기
   - 7: 종료
5. 선택한 기능에 따라 추가 입력을 제공합니다.
6. 결과를 확인하고 메인 메뉴로 돌아갑니다.
</details>

<details>
<summary><b>📚 배운 점</b> (클릭하여 펼치기/접기)</summary>

- **Enum의 고급 활용**: 단순한 상수 집합이 아닌, 기능을 가진 타입으로 Enum을 활용하는 방법을 배웠습니다.
- **추상 메서드를 통한 다형성**: Enum 상수별로 다른 동작을 정의하여 객체지향적 설계를 적용하는 방법을 익혔습니다.
- **제네릭 프로그래밍**: 타입 매개변수를 활용해 다양한 숫자 타입을 유연하게 처리하는 방법을 배웠습니다.
- **스트림 API**: 함수형 프로그래밍 방식으로 컬렉션 데이터를 효율적으로 처리하는 방법을 익혔습니다.
- **Number 클래스 활용**: 다양한 숫자 타입을 통합적으로 처리하는 방법을 배웠습니다.
- **메서드 참조**: 기존 메서드를 함수형 인터페이스의 인스턴스로 변환하는 방법을 익혔습니다.
</details>

<details>
<summary><b>🔮 향후 개선 사항</b> (클릭하여 펼치기/접기)</summary>

- 정수와 실수 외에 분수, 복소수 등 더 다양한 숫자 타입 지원
- 복잡한 수식(괄호, 지수 등) 계산 기능 추가
- 결과 저장 및 불러오기 기능 구현
- GUI 인터페이스 개발
- 단위 테스트 및 예외 처리 강화
- 더 많은 결과 필터링 옵션 추가 (홀수 결과, 범위 내 결과 등)
</details>
