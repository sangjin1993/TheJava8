package section1;

import java.util.function.Consumer;

/*
 * Consumer<T> T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
 * void Accept(T t)
 * 조합용 메소드 andThen이 있음
 */
public class JavaConsumerRun {

	public static void main(String[] args) {
		// 람다 표현식으로 Consumer 구현
		// i를 받아서 i를 출력
		Consumer<Integer> printT = (i) -> System.out.println(i);
		// accept 메소드로 출력
		printT.accept(10);
	}
}
