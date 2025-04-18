# 계산기 프로젝트 - Level 3 구현

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
