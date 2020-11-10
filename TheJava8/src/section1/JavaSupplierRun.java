package section1;

import java.util.function.Supplier;

/*
 * Supplier<T> T 타입의 값을 제공하는 함수 인터페이스
 * T get()
 */
public class JavaSupplierRun {

	public static void main(String[] args) {
		// 인자를 받지 않고 받아올 타입을 정의
		Supplier<Integer> get10 = () -> 10;
		
		// 10을 리턴하는 함수
		System.out.println(get10.get());
	}
}
