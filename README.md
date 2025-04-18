# 계산기 프로젝트 - Level 2 구현

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
