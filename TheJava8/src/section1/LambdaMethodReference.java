package section1;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/*
 * 메소드 레퍼런스
 * 람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게
 * 표현 할 수 있다.
 * 
 * 메소드 참조한느 방법
 * 스태틱 메소드 참조				타입::스태틱메소드
 * 특정 객체의 인스턴스 메소드 참조		객체 레퍼런스::인스턴스 메소드
 * 임의 객체의 인스턴스 메소드 참조		타입::인스턴스 메소드
 * 생성자 참조						타입::new
 * 
 * 	메소드 또는 생성자의 매개변수로 람다의 입력값을 받는다.
 * 	리턴값 또는 생성한 객체는 람다의 리턴값이다.
 */
public class LambdaMethodReference {
	public static void main(String[] args) {
		
		// 스태틱 메소드 참조
		// UnaryOperator의 구현체로 Greeting의 static메소드를 사용
//		UnaryOperator<String> hi = (s) -> "hi" + s;
		UnaryOperator<String> hi = Greeting::hi;
		
		// 특정 인스턴스 메소드 참조
		Greeting greeting = new Greeting();
		UnaryOperator<String> hello = greeting::hello;
		// hello를 apply해야 무슨일이 일어남
		System.out.println(hello.apply("SangJin"));
		
		// 입력 값이 없는 생성자 참조, 이 자체로 생성되지 않고
		Supplier<Greeting> newGreeting = Greeting::new;
		// get을 해야 생성
		newGreeting.get();
		
		// 입력값을 받는 생성자
		Function<String, Greeting> sangJinGreeting = Greeting::new;
		Greeting sangjin = sangJinGreeting.apply("SangJin");
		System.out.println(sangjin.getName());
		
		// 임의 객체 인스턴스 메소드 참조하는 방법
		String[] names = {"keesun", "whiteship", "tody"};
		// Compartor는 함수형 인터페이스라서 람다 사용 가능
//		Arrays.sort(names, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				
//				return 0;
//			}
//		});
		// 람다로 정렬하는 방법
		Arrays.sort(names, (o1, o2) -> 0);
		// 임의 객체 인스턴스 메소드로 람다 구현제로 사용
		Arrays.sort(names, String::compareToIgnoreCase);
		// 출력
		System.out.println(Arrays.toString(names));
	}
}
