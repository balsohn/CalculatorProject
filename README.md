# 계산기 프로젝트 - Level 1 구현

<details>
<summary><b>📌 개요</b> (클릭하여 펼치기/접기)</summary>

이 문서는 Java를 사용한 콘솔 기반 계산기의 Level 1 구현에 대한 설명입니다. Level 1에서는 기본적인 사칙연산을 수행하고 사용자 입력을 반복적으로 처리하는 기능을 구현합니다.
</details>

<details>
<summary><b>📋 요구사항</b> (클릭하여 펼치기/접기)</summary>

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
