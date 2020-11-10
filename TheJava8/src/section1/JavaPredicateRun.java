package section1;

import java.util.function.Predicate;

/*
 * Predicate<T> T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
 * boolean test(T t)
 * 조합용 메소드
 * And
 * Or
 * Negate
 */
public class JavaPredicateRun {

	public static void main(String[] args) {
		Predicate<String> startsWithSangJin = (s) -> s.startsWith("SangJin");
		Predicate<Integer> isEven = (i) -> i % 2 == 0;
		
		String SangJin = "SangJin";
		int number = 20;
		
		// test메소드로 값들을 비교해서 출력
		System.out.println(startsWithSangJin.test(SangJin));
		System.out.println(isEven.test(number));
		
		// 조합용 메소드는 비교연산자 처럼 사용
		Predicate<Integer> isTen = (i) -> i == 10; 
		// 짝수이면서 10인 값을 찾는 and 연산 (&&)
		System.out.println(isEven.and(isTen).test(number));
		
		// 짝수나 10인 값을 찾는 or 연산 (||)
		System.out.println(isEven.or(isTen).test(number));
		
		// 등가비교 연산자 (!)
		System.out.println(isEven.negate().test(number));
	}
}
