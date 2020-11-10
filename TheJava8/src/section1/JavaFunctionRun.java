package section1;

import java.util.function.Function;

public class JavaFunctionRun {
	
	public static void main(String[] args) {
		// 인스턴스 생성해서 사용하는 방법
		JavaFunction plus10 = new JavaFunction();
		
		// apply 사용 10을 더해 11이 출력
		System.out.println(plus10.apply(1));
		
		
		// 람다 표현식으로 인터페이스와 인스턴스 생성하지 않고 바로 구현 할 수 있음
//		Function<Integer, Integer> plusTen = (i) -> {
//			return i + 10;
//		};
		// return을 생략할 수 있음
		Function<Integer, Integer> plusTen = (i) -> i + 10;
		
		System.out.println(plusTen.apply(1));
		
		// 함수를 조합할 수 있는 default메소드로 andThen, compose 제공 
		
		// compose 사용 방법
		// 곱하기 2를 해주는 또다른 함수 생성
		Function<Integer, Integer> multiply2 = (i) -> i * 2;
		
		// 고차함수의 성격으로 함수를 리턴한 값을 파라미터로 다시 계산
		plusTen.compose(multiply2);
		
		// 보기 쉽게 변수에 할당
		Function<Integer, Integer> multiply2AndPlus10 = plusTen.compose(multiply2);
		// 2를 입력값으로 주면 (2 * 2) + 10 = 14 출력
		System.out.println(multiply2AndPlus10.apply(2));
		
		// andThen 사용 방법
		// 입력 값이 들어가면 플러스 10을 하고 그 다음 곱하기 2를 함
		// 2를 입력 값으로 주면 (2 + 10) * 2 = 24 출력
		System.out.println(plusTen.andThen(multiply2).apply(2));
		
	}
}
 