# 계산기 프로젝트 - Level 2 구현

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

### 1. Scanner의 nextInt()와 nextLine() 혼용 문제
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

### 2. 컬렉션 방어적 복사 (Defensive Copy)
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

### 3. 예외 처리와 오류 메시지
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
