# 계산기 프로젝트

## 계산기 프로젝트 - Level 1 구현


<b>📌 개요</b>

이 문서는 Java를 사용한 콘솔 기반 계산기의 Level 1 구현에 대한 설명입니다. <br>
Level 1에서는 기본적인 사칙연산을 수행하고 사용자 입력을 반복적으로 처리하는 기능을 구현합니다.


<details>
<summary><b>📋 요구사항</b> (클릭하여 펼치기/접기)</summary>
<br>

- 양의 정수(0 포함) 두 개를 입력받을 수 있어야 함
- 사칙연산 기호(+, -, *, /)를 입력받을 수 있어야 함
- 입력받은 값을 사용하여 연산을 수행하고 결과를 출력
- "exit" 입력 시까지 반복적으로 계산 수행
- 나눗셈에서 0으로 나누는 예외 상황 처리
</details>

<details>
<summary><b>🔍 구현 단계</b> (클릭하여 펼치기/접기)</summary>

### 1. 프로젝트 생성
1. IntelliJ IDEA를 실행하고 새 Java 프로젝트 생성
2. JDK 17 선택
3. 프로젝트 이름을 "CalculatorProject"로 설정
4. 패키지 생성: `com.example.calculator`
5. Java 클래스 생성: `App.java`

### 2. 기본 기능 구현
#### 숫자 입력 받기
- Scanner 클래스를 사용하여 양의 정수 두 개를 입력 받기
- 적절한 타입(int)의 변수에 저장

#### 연산자 입력 받기
- Scanner를 사용하여 사칙연산 기호(+, -, *, /) 입력 받기
- char 타입 변수에 저장

#### 계산 수행 및 결과 출력
- switch 또는 if 문을 사용하여 연산자에 따른 계산 수행
- 0으로 나누기와 같은 예외 상황 처리
- 계산 결과를 콘솔에 출력

#### 반복 처리 구현
- while 루프 사용하여 계산을 반복
- "exit" 입력 시 프로그램 종료
- 계속 계산할지 여부 확인
</details>

<details>
<summary><b>📝 주요 클래스 및 메서드</b> (클릭하여 펼치기/접기)</summary>

### App.java
```java
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("계산기를 시작합니다. ('exit' 입력 시 종료)");
        
        while (running) {
            // 첫 번째 숫자 입력
            System.out.print("첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            
            if (input.equals("exit")) {
                running = false;
                break;
            }
            
            int num1;
            try {
                num1 = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 아닙니다. 다시 시도해주세요.");
                continue;
            }
            
            // 두 번째 숫자 입력
            System.out.print("두 번째 숫자를 입력하세요: ");
            input = sc.nextLine();
            
            if (input.equals("exit")) {
                running = false;
                break;
            }
            
            int num2;
            try {
                num2 = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 아닙니다. 다시 시도해주세요.");
                continue;
            }
            
            // 연산자 입력
            System.out.print("사칙연산 기호를 입력하세요(+, -, *, /): ");
            input = sc.nextLine();
            
            if (input.equals("exit")) {
                running = false;
                break;
            }
            
            char operator = input.charAt(0);
            
            // 계산 수행
            int result = 0;
            
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
                        continue;
                    }
                    result = num1 / num2;
                    break;
                default:
                    System.out.println("올바른 연산자가 아닙니다.");
                    continue;
            }
            
            // 결과 출력
            System.out.println("결과: " + result);
            
            // 계속 진행 여부 확인
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            input = sc.nextLine();
            
            if (input.equals("exit")) {
                running = false;
            }
        }
        
        sc.close();
        System.out.println("계산기를 종료합니다.");
    }
}
```
</details>

<details>
<summary><b>⚠️ 구현 시 어려웠던 부분과 해결 방법</b> (클릭하여 펼치기/접기)</summary>

### 1. 무한 반복 루프 문제

**문제점**: "exit"를 입력해도 프로그램이 종료되지 않고 계속 실행되었습니다.

**원인**: 문자열 비교를 `==` 연산자로 했거나, 대소문자 구분 문제가 있었습니다.

**해결책**: `equals()` 메서드를 사용하여 문자열을 정확히 비교했습니다.

```java
// 잘못된 방식
if (choice == "exit") { ... }

// 올바른 방식
if (choice.equals("exit")) { ... }
```

### 2. 0으로 나누기 예외

**문제점**: 나눗셈 시 분모가 0이면 프로그램이 비정상 종료되었습니다.

**해결책**: 나눗셈 전에 분모가 0인지 확인하는 조건 검사를 추가했습니다.

```java
case '/':
    if (num2 == 0) {
        System.out.println("0으로 나눌 수 없습니다.");
        continue;
    }
    result = num1 / num2;
    break;
```

### 3. 잘못된 연산자 처리

**문제점**: 지원하지 않는 연산자 입력 시 적절한 처리가 없었습니다.

**해결책**: switch문에 default 케이스를 추가하여 모든 입력 케이스를 처리했습니다.

```java
default:
    System.out.println("올바른 연산자가 아닙니다.");
    continue;
```
</details>

<details>
<summary><b>▶️ 실행 방법</b> (클릭하여 펼치기/접기)</summary>

1. IntelliJ IDEA에서 Run 버튼(녹색 화살표)을 클릭하거나 Shift+F10 단축키를 누릅니다.
2. 콘솔 창에 첫 번째 숫자를 입력합니다.
3. 두 번째 숫자를 입력합니다.
4. 사칙연산 기호를 입력합니다.
5. 결과를 확인하고 계속할지 여부를 결정합니다.
6. "exit"를 입력하면 프로그램이 종료됩니다.
</details>

<details>
<summary><b>📚 배운 점</b> (클릭하여 펼치기/접기)</summary>

- Java 기본 문법과 구조 이해
- Scanner 클래스를 사용한 사용자 입력 처리
- 조건문(if, switch)과 반복문(while)의 활용
- 예외 상황 처리 방법
- 문자열 비교 시 `equals()` 메서드 사용의 중요성
</details>

<details>
<summary><b>🔮 향후 개선 사항 (Level 2 준비)</b> (클릭하여 펼치기/접기)</summary>

- 객체지향 설계 적용 (Calculator 클래스 분리)
- 계산 결과 저장 기능 추가
- 캡슐화 원칙 적용
- 첫 번째 결과 삭제 기능 구현
</details>

## 계산기 프로젝트 - Level 2 구현

<b>📌 개요</b>

이 문서는 Java를 사용한 콘솔 기반 계산기의 Level 2 구현에 대한 설명입니다. <br>
Level 2에서는 객체지향 설계를 적용하여 계산기 기능을 확장하고, 계산 결과를 저장하는 기능을 추가했습니다.


<details>
<summary><b>📋 요구사항</b> (클릭하여 펼치기/접기)</summary>

- Calculator 클래스 생성 및 계산 기능 구현
- 연산 결과를 저장하는 컬렉션 타입 필드 구현
- 캡슐화 적용 (private 필드와 getter 메서드)
- 저장된 첫 번째 결과를 삭제하는 기능 구현
</details>

<details>
<summary><b>🔍 구현 단계</b> (클릭하여 펼치기/접기)</summary>

### 1. Calculator 클래스 구현
- 연산 결과를 저장하는 컬렉션 타입 필드 선언
- 사칙연산 수행 및 결과값 반환 메서드 구현
- 결과값을 컬렉션에 저장하는 기능 추가
- Getter 메서드와 첫 번째 결과 삭제 메서드 구현

### 2. App 클래스 수정
- Calculator 인스턴스 생성 및 활용
- 사용자 입력 처리 로직 구현
- 메뉴 시스템 구현 (계속 계산, 첫 번째 결과 삭제, 종료)
</details>

<details>
<summary><b>📝 주요 클래스 및 메서드</b> (클릭하여 펼치기/접기)</summary>

### Calculator.java
```java
package com.example.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    // 연산 결과를 저장하는 컬렉션 타입 필드 (캡슐화)
    private List<Integer> results;

    // 생성자
    public Calculator() {
        this.results = new ArrayList<>();
    }

    // 계산 메서드
    public int calculate(int num1, int num2, char operator) {
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
                    throw new RuntimeException("나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                throw new RuntimeException("올바른 연산자가 아닙니다.");
        }

        results.add(result);
        return result;
    }

    // getter
    public List<Integer> getResults() {
        return new ArrayList<>(results); // 방어적 복사
    }

    // 첫 번째 결과 삭제 메서드
    public void removeResult() {
        if (results.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
        } else {
            results.remove(0);
            System.out.println("가장 먼저 계산된 결과가 삭제되었습니다.");
        }
    }
}
```

### App.java
```java
package com.example.calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        boolean running = true;

        System.out.println("계산기를 시작합니다. ('exit' 입력 시 종료)");

        while (running) {
            // 계산 할 값 입력
            System.out.print("첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();

            if (input.equals("exit")) {
                running = false;
                break;
            }

            int num1;
            try {
                num1 = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 아닙니다. 다시 시도해주세요.");
                continue;
            }

            System.out.print("두 번째 숫자를 입력하세요: ");
            input = sc.nextLine();

            if (input.equals("exit")) {
                running = false;
                break;
            }

            int num2;
            try {
                num2 = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 아닙니다. 다시 시도해주세요.");
                continue;
            }

            // 연산자
            System.out.print("사칙연산 기호를 입력하세요(+, -, *, /): ");
            input = sc.nextLine();

            if (input.equals("exit")) {
                running = false;
                break;
            }

            if (input.isEmpty()) {
                System.out.println("연산자를 입력해주세요. 다시 시도합니다.");
                continue;
            }

            char operator = input.charAt(0);

            try {
                int result = calculator.calculate(num1, num2, operator);
                // 결과
                System.out.println("결과: " + result);
                // 저장된 결과
                System.out.println("지금까지의 계산 결과: " + calculator.getResults());
            } catch (RuntimeException e) {
                System.out.println("오류: " + e.getMessage());
                continue;
            }

            // 메뉴 선택
            System.out.println("메뉴: 1.계속 계산 2.첫번째 결과 삭제 3.종료");
            System.out.print("번호 입력: ");
            
            try {
                int choice = Integer.parseInt(sc.nextLine());
                
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
                        System.out.println("선택된 메뉴가 없습니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }

        sc.close();
        System.out.println("계산기를 종료합니다.");
    }
}
```
</details>

<details>
<summary><b>⚠️ 구현 시 어려웠던 부분과 해결 방법</b> (클릭하여 펼치기/접기)</summary>

### 1. Scanner의 nextInt()와 nextLine() 혼용 문제

**문제점**: nextInt() 호출 후 nextLine()을 호출하면, 이전에 입력된 줄바꿈 문자를 nextLine()이 읽어버려 원하는 입력을 받지 못하는 문제가 발생했습니다.

**원인**: nextInt()는 숫자만 읽고 그 뒤의 줄바꿈 문자('\n')는 버퍼에 남겨둡니다. 이후 nextLine()을 호출하면 버퍼에 남아있던 줄바꿈 문자를 읽게 되어 사용자 입력을 제대로 받지 못하는 문제가 발생했습니다.

**해결책**: 모든 입력을 nextLine()으로 통일하고, 필요한 경우 형변환을 사용했습니다.

```java
// 잘못된 방식
int choice = sc.nextInt();

// 올바른 방식
String input = sc.nextLine();
try {
    int choice = Integer.parseInt(input);
    // 처리
} catch (NumberFormatException e) {
    // 예외 처리
}
```

### 2. 컬렉션 방어적 복사 (Defensive Copy)

**문제점**: 컬렉션을 그대로 반환하면 외부에서 내부 데이터를 직접 수정할 수 있어 캡슐화가 깨지는 문제가 있었습니다.

**원인**: 객체의 내부 상태를 외부에 노출시키면 캡슐화가 깨지고, 객체의 무결성을 보장할 수 없게 됩니다.

**해결책**: 필드를 private으로 선언하고, 방어적 복사를 통해 컬렉션의 복사본을 반환하도록 수정했습니다.

```java
private List<Integer> results = new ArrayList<>();

// 잘못된 방식
public List<Integer> getResults() {
    return results; // 원본 참조 반환
}

// 올바른 방식
public List<Integer> getResults() {
    return new ArrayList<>(results); // 복사본 반환
}
```

### 3. 메뉴 선택 시 잘못된 입력 처리

**문제점**: 메뉴 선택 시 사용자가 유효하지 않은 값을 입력했을 때 적절한 처리가 되지 않는 문제가 있었습니다.

**해결책**: try-catch 블록을 활용하여 예외 상황을 처리하고, default 케이스를 추가하여 모든 입력 경우에 대응했습니다.

```java
try {
    int choice = Integer.parseInt(input);
    switch (choice) {
        case 1:
            // 처리
            break;
        case 2:
            // 처리
            break;
        case 3:
            running = false;
            break;
        default:
            System.out.println("선택된 메뉴가 없습니다.");
    }
} catch (NumberFormatException e) {
    System.out.println("숫자를 입력해주세요.");
}
```
</details>

<details>
<summary><b>▶️ 실행 방법</b> (클릭하여 펼치기/접기)</summary>

1. 프로그램을 실행합니다.
2. 첫 번째 숫자를 입력합니다 (또는 'exit' 입력 시 종료).
3. 두 번째 숫자를 입력합니다 (또는 'exit' 입력 시 종료).
4. 사칙연산 기호를 입력합니다 (+, -, *, /).
5. 계산 결과 및 저장된 모든 결과를 확인합니다.
6. 메뉴에서 선택:
   - 1: 계속 계산
   - 2: 첫 번째 결과 삭제
   - 3: 종료
</details>

<details>
<summary><b>📚 배운 점</b> (클릭하여 펼치기/접기)</summary>

- 객체지향 설계 원칙 (캡슐화, 단일 책임 원칙)
- Java 컬렉션 프레임워크 사용법
- 방어적 프로그래밍 기법
- 예외 처리 방법과 중요성
- Scanner 클래스의 올바른 사용법
</details>

<details>
<summary><b>🔮 향후 개선 사항 (Level 3 준비)</b> (클릭하여 펼치기/접기)</summary>

- 정수뿐만 아니라 실수 연산도 지원
- Enum을 활용한 연산자 타입 관리
- 제네릭을 적용한 다양한
 숫자 타입 지원
- 람다와 스트림을 활용한 결과 필터링
</details>

## 계산기 프로젝트 - Level 3 구현

<b>📌 개요</b>

이 문서는 Java를 사용한 콘솔 기반 계산기의 Level 3 구현에 대한 설명입니다. <br>
Level 3에서는 Enum, 제네릭, 람다 & 스트림과 같은 Java의 고급 기능을 적용하여 계산기를 고도화했습니다.


<details>
<summary><b>📋 요구사항</b> (클릭하여 펼치기/접기)</summary>

- Enum 타입을 활용하여 연산자 타입에 대한 정보 관리 및 계산기에 적용
- 제네릭을 사용하여 다양한 숫자 타입(정수, 실수) 지원
- 람다와 스트림을 활용한 결과값 필터링 기능 구현
</details>

<details>
<summary><b>🔍 구현 단계</b> (클릭하여 펼치기/접기)</summary>

### 1. Enum 클래스 구현
- 연산자 타입(ADD, SUB, MUL, DIV)을 Enum으로 정의
- 각 연산자에 해당하는 기호(+, -, *, /) 매핑
- 연산 수행 메서드와 기호로부터 연산자 찾는 메서드 구현

### 2. 제네릭 계산기 클래스 구현
- 다양한 숫자 타입을 처리할 수 있도록 제네릭 타입 매개변수 적용
- 제네릭 제약 조건을 통해 Number 타입과 그 하위 타입만 허용
- 결과값을 정수 또는 실수로 적절히 변환하여 저장

### 3. 람다와 스트림 적용
- 저장된 결과 중 특정 값보다 큰 결과 필터링 기능 구현
- 스트림 API를 활용한 결과 컬렉션 처리
- 메서드 참조를 통한 결과 출력 구현

### 4. 메뉴 시스템 확장
- 다양한 기능을 제공하는 메뉴 인터페이스 구현
- 계산, 결과 조회, 결과 필터링, 결과 삭제 등의 기능 추가
</details>

<details>
<summary><b>📝 주요 클래스 및 메서드</b> (클릭하여 펼치기/접기)</summary>

### OperatorType.java
```java
public enum OperatorType {
    ADD('+'), SUB('-'), MUL('*'), DIV('/');
    
    private final char symbol;
    
    OperatorType(char symbol) {
        this.symbol = symbol;
    }
    
    public char getSymbol() {
        return symbol;
    }
    
    public static OperatorType fromSymbol(char symbol) {
        for (OperatorType type : values()) {
            if (type.symbol == symbol) {
                return type;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 연산 기호입니다. ");
    }
    
    public double apply(double num1, double num2) {
        switch (this) {
            case ADD: return num1 + num2;
            case SUB: return num1 - num2;
            case MUL: return num1 * num2;
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
```

### ArithmeticCalculator.java
```java
public class ArithmeticCalculator<T extends Number> {
    private final ArrayList<Number> results;
    
    public ArithmeticCalculator() {
        this.results = new ArrayList<>();
    }
    
    public List<Number> getResults() {
        return new ArrayList<Number>(this.results);
    }
    
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
    
    public void removeResult() {
        // 가장 오래된 결과 삭제
    }
    
    public void clearResults() {
        // 모든 결과 삭제
    }
    
    public List<Number> getGreaterResult(double threshold) {
        return this.results.stream()
                .filter(result -> result.doubleValue() > threshold)
                .collect(Collectors.toList());
    }
    
    // 추가적인 필터링 메서드들
}
```

### ArithmeticApp.java
```java
public class ArithmeticApp {
    public static void main(String[] args) {
        // 메인 메서드 - 계산기 인스턴스 생성 및 메뉴 처리
    }
    
    // 계산 수행 메서드
    public static void MenuOneCalculator(ArithmeticCalculator<Number> calculator, Scanner sc) {
        // 숫자 두 개와 연산자 입력 받아 계산
    }
    
    // 모든 결과 출력 메서드
    public static void allResults(ArithmeticCalculator<Number> calculator) {
        // 저장된 모든 결과 출력
    }
    
    // 특정 값보다 큰 결과 출력 메서드
    public static void getGreaterResult(ArithmeticCalculator<Number> calculator, Scanner sc) {
        // 기준값 입력 받고 필터링된 결과 출력
    }
}
```
</details>

<details>
<summary><b>⚠️ 구현 시 어려웠던 부분과 해결 방법</b> (클릭하여 펼치기/접기)</summary>

### 1. 제네릭 타입과 기본 타입 변환 문제

**문제점**: 제네릭 타입 매개변수 T로 받은 숫자를 직접 연산할 수 없었습니다.

**원인**: 제네릭 타입 매개변수는 객체 타입만 가능하며, 기본 타입에 대한 연산이 불가능합니다.

**해결책**: Number 클래스의 doubleValue() 메서드를 사용하여 모든 숫자 타입을 double로 변환한 후 연산했습니다.

```java
double doubleNum1 = num1.doubleValue();
double doubleNum2 = num2.doubleValue();
double result = operator.apply(doubleNum1, doubleNum2);
```

### 2. 결과 타입 판별 문제

**문제점**: 계산 결과가 정수인 경우와 실수인 경우를 구분하여 저장하고 싶었습니다.

**원인**: 모든 연산을 double로 수행했기 때문에 정수 결과도 실수 형태로 나옵니다.

**해결책**: 결과값이 정수인지 확인하기 위해 double 값과 int로 변환한 값을 비교했습니다.

```java
if (result == (int)result) {
    this.results.add((int)result);
    return (int)result;
} else {
    this.results.add(result);
    return result;
}
```

### 3. 람다와 스트림의 타입 변환 문제

**문제점**: 스트림 처리 중 Number 객체에 대한 산술 연산이 직접 불가능했습니다.

**원인**: Number는 추상 클래스이며, 직접적인 산술 연산자 사용이 불가능합니다.

**해결책**: filter 메서드 내 람다식에서 doubleValue() 메서드를 호출하여 기본 타입으로 변환 후 연산했습니다.

```java
return this.results.stream()
        .filter(result -> result.doubleValue() > threshold)
        .collect(Collectors.toList());
```
</details>

<details>
<summary><b>▶️ 실행 방법</b> (클릭하여 펼치기/접기)</summary>

1. 프로그램을 실행합니다.
2. 메인 메뉴에서 원하는 기능을 선택합니다:
   - 1: 계산하기 (Enum 사용)
   - 2: 저장된 모든 결과 보기
   - 3: 특정 값보다 큰 결과 보기
   - 4: 가장 오래된 결과 삭제
   - 5: 모든 결과 삭제
   - 6: 종료
3. 선택한 기능에 따라 추가 입력을 제공합니다.
4. 결과를 확인하고 메인 메뉴로 돌아갑니다.
</details>

<details>
<summary><b>📚 배운 점</b> (클릭하여 펼치기/접기)</summary>

- **Enum 활용**: 상수 집합을 타입 안전하게 관리하는 방법과 Enum에 기능을 추가하는 방법
- **제네릭 프로그래밍**: 타입 매개변수를 활용한 유연한 클래스 설계 방법
- **함수형 프로그래밍**: 람다식과 스트림 API를 통한 간결하고 직관적인 코드 작성법
- **Number 클래스 활용**: 다양한 숫자 타입을 통합적으로 처리하는 방법
- **메서드 참조**: 기존 메서드를 함수형 인터페이스의 인스턴스로 변환하는 방법
</details>

<details>
<summary><b>🔮 향후 개선 사항</b> (클릭하여 펼치기/접기)</summary>

- 정수와 실수 외에 분수, 복소수 등 더 다양한 숫자 타입 지원
- 복잡한 수식(괄호, 지수 등) 계산 기능 추가
- 결과 저장 및 불러오기 기능 구현
- GUI 인터페이스 개발
- 단위 테스트 및 예외 처리 강화
</details>


