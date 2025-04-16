# 계산기 프로젝트

<details>
<summary><b>📌 Level 1 구현</b> (클릭하여 펼치기/접기)</summary>

## 개요
이 문서는 Java를 사용한 콘솔 기반 계산기의 Level 1 구현에 대한 설명입니다. Level 1에서는 기본적인 사칙연산을 수행하고 사용자 입력을 반복적으로 처리하는 기능을 구현합니다.

## 요구사항
- 양의 정수(0 포함) 두 개를 입력받을 수 있어야 함
- 사칙연산 기호(+, -, *, /)를 입력받을 수 있어야 함
- 입력받은 값을 사용하여 연산을 수행하고 결과를 출력
- "exit" 입력 시까지 반복적으로 계산 수행
- 나눗셈에서 0으로 나누는 예외 상황 처리

## 구현 단계
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

## 실행 방법
1. IntelliJ IDEA에서 Run 버튼(녹색 화살표)을 클릭하거나 Shift+F10 단축키를 누릅니다.
2. 콘솔 창에 첫 번째 숫자를 입력합니다.
3. 두 번째 숫자를 입력합니다.
4. 사칙연산 기호를 입력합니다.
5. 결과를 확인하고 계속할지 여부를 결정합니다.
6. "exit"를 입력하면 프로그램이 종료됩니다.

## 주요 구현 포인트
1. **사용자 입력 처리**: Scanner 클래스를 사용하여 사용자로부터 두 개의 숫자와 연산자를 입력받습니다.
2. **연산 수행**: switch 문을 사용하여 입력된 연산자에 따라 다른 연산을 수행합니다.
3. **예외 처리**: 0으로 나누기와 같은 예외 상황을 처리합니다.
4. **반복 처리**: while 루프를 사용하여 사용자가 "exit"을 입력할 때까지 계산을 반복합니다.

## 한계점
- 현재는 정수만 처리 가능 (실수 처리 불가)
- 연산 기록이 저장되지 않음
- 한 번에 두 개의 숫자만 처리 가능
- 복잡한 수식 처리 불가

이러한 한계점은 Level 2와 Level 3에서 개선될 예정입니다.
</details>

<details>
<summary><b>📌 Level 2 구현</b> (클릭하여 펼치기/접기)</summary>

## 개요
이 문서는 Java를 사용한 콘솔 기반 계산기의 Level 2 구현에 대한 설명입니다. Level 2에서는 객체지향 설계를 적용하여 계산기 기능을 확장하고, 계산 결과를 저장하는 기능을 추가했습니다.

## 요구사항
- Calculator 클래스 생성 및 계산 기능 구현
- 연산 결과를 저장하는 컬렉션 타입 필드 구현
- 캡슐화 적용 (private 필드와 getter 메서드)
- 저장된 첫 번째 결과를 삭제하는 기능 구현

## 구현 단계
### 1. Calculator 클래스 구현
- 연산 결과를 저장하는 컬렉션 타입 필드 선언
- 사칙연산 수행 및 결과값 반환 메서드 구현
- 결과값을 컬렉션에 저장하는 기능 추가
- Getter 메서드와 첫 번째 결과 삭제 메서드 구현

### 2. App 클래스 수정
- Calculator 인스턴스 생성 및 활용
- 사용자 입력 처리 로직 구현
- 메뉴 시스템 구현 (계속 계산, 첫 번째 결과 삭제, 종료)

## 주요 클래스 및 메서드
### Calculator 클래스
```java
public class Calculator {
    // 연산 결과를 저장하는 컬렉션 타입 필드 (캡슐화)
    private List<Integer> results;
    
    // 생성자
    public Calculator() {
        this.results = new ArrayList<>();
    }
    
    // 계산 메서드
    public int calculate(int num1, int num2, char operator) {
        // 사칙연산 구현 및 결과 저장
    }
    
    // 결과 조회 메서드 (캡슐화)
    public List<Integer> getResults() {
        return new ArrayList<>(results); // 방어적 복사본 반환
    }
    
    // 첫 번째 결과 삭제 메서드
    public void removeResult() {
        // 첫 번째 결과 삭제 구현
    }
}
```

### App 클래스
```java
public class App {
    public static void main(String[] args) {
        // Scanner 및 Calculator 인스턴스 생성
        // 사용자 입력 처리 및 계산 수행
        // 메뉴 시스템 구현
    }
}
```

## 구현 시 어려웠던 부분과 해결 방법
<details>
<summary>1. Scanner의 nextInt()와 nextLine() 혼용 문제</summary>

**문제점**: nextInt() 호출 후 nextLine()을 호출하면, 이전에 입력된 줄바꿈 문자를 nextLine()이 읽어버려 원하는 입력을 받지 못하는 문제가 발생

**해결책**: 모든 사용자 입력을 nextLine()으로 통일하고, 필요할 경우 Integer.parseInt()를 사용하여 정수로 변환

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
</details>

<details>
<summary>2. 컬렉션 방어적 복사 (Defensive Copy)</summary>

**문제점**: 컬렉션을 그대로 반환하면 외부에서 내부 데이터를 직접 수정할 수 있어 캡슐화가 깨짐

**해결책**: 새 컬렉션을 생성하여 복사본을 반환함으로써 원본 데이터를 보호

```java
// 잘못된 방식
public List<Integer> getResults() {
    return results; // 원본 참조 반환
}

// 올바른 방식
public List<Integer> getResults() {
    return new ArrayList<>(results); // 복사본 반환
}
```
</details>

<details>
<summary>3. 예외 처리와 오류 메시지</summary>

**문제점**: 다양한 예외 상황(숫자 형식 오류, 0으로 나누기 등)을 모두 적절히 처리해야 함

**해결책**: try-catch 블록을 사용하여 예외를 포착하고 친절한 오류 메시지 제공

```java
try {
    int result = calculator.calculate(num1, num2, operator);
    // 결과 출력
} catch (RuntimeException e) {
    System.out.println("오류: " + e.getMessage());
    continue;
}
```
</details>

## 실행 방법
1. 계산기 프로그램 실행
2. 첫 번째 숫자 입력 (또는 'exit' 입력 시 종료)
3. 두 번째 숫자 입력 (또는 'exit' 입력 시 종료)
4. 사칙연산 기호 입력 (+, -, *, /)
5. 계산 결과 확인
6. 메뉴에서 선택:
   - 1: 계속 계산
   - 2: 첫 번째 결과 삭제
   - 3: 종료

## 배운 점
- 객체지향 설계 원칙 (캡슐화, 단일 책임 원칙)
- Java 컬렉션 프레임워크 사용법
- 사용자 입력 처리 및 예외 처리 방법
- Scanner 클래스의 올바른 사용법

## 향후 개선 사항 (Level 3 준비)
- 정수뿐만 아니라 실수 연산도 지원
- 특정 값보다 큰 결과값을 조회하는 기능 추가
- 코드 리팩토링 및 사용자 경험 개선
</details>
