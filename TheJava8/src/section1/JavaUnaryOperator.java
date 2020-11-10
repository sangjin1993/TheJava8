package section1;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/*
 * Function<T, R>의 특수한 형태로 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
 * 
 * UnaryOperator는 Function을 상속받았기 때문에 compose, andThen메소드 사용할 수 있음
 */
public class JavaUnaryOperator {
	
	public static void main(String[] args) {
		// 입력받는 타입과 리턴하는 타입이 같음
		Function<Integer, Integer> functionPlus10  = (i) -> i + 10;
		// 위 Function를 UnaryOperator로 바꾸면 제네릭을 하나만 쓸 수 있음
		UnaryOperator<Integer> unaryPlus10 = (i) -> i + 10;
		
		// 출력
		System.out.println(functionPlus10.apply(1));
		System.out.println(unaryPlus10.apply(1));
	}
}
