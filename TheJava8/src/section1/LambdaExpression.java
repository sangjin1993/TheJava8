package section1;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
/*
 * 람다 (인자) -> { 바디 }
 * 인자 리스트
 * 	인자가 없을때 : ()
 * 	인자가 한개일 때 : (one) 또는 one
 * 	인자가 여러개 일 때 : (one, two)
 * 	인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. 
 * 바디
 * 	화살표 오른쪽에 함수 본문을 정의한다.
 * 	여러 줄인 경우에 {}를 사용해서 묶는다.
 * 	한 줄인 경우 생략 가능, return도 생략 가능
 */
public class LambdaExpression {
	public static void main(String[] args) {
		// 인자가 없고 return 도 생략가능
		Supplier<Integer> get10 = () -> 10;

		// 인자를 두개 받을 떄 a + b를 계산해서 리턴
		// 선언한 제네릭 타입으로 컴파일러가 추론할 수 있음 타입 생략 가능
		BinaryOperator<Integer> sum = (a, b) -> a + b;
		
		// 변수 캡처(Variable Capture)
		LambdaExpression lambda = new LambdaExpression();
		lambda.run();
	}
	
	/*
	 * 로컬 클래스와 익명 클래스는 쉐도잉이 가능 하지만 람다는 불가능
	 */
	private void run() {
		// 로컬 변수 참조
		final int baseNumber = 10;
		
		// 로컬 클래스
		class LocalClass {
			void printBaseNumber () {
				int baseNumber = 11;
				System.out.println(baseNumber);	// 11이 출력 로컬 변수를 가려짐
			}
		}
		
		// 익명 클래스
		Consumer<Integer> integerConsumer = new Consumer<Integer>() {
			// 넘겨진 파라미터 그대로 쓰고 로컬 변수를 가려짐
			@Override
			public void accept(Integer baseNumber) {
				System.out.println(baseNumber);
			}
		};
		
		// 람다
		// 람다의 스코프는 감싸고 있는 메소드까지
		// 인자 i를 baseNumber로 바꾸면 컴파일에러발생
		// 이유는 같은 스코프 내에 변수명이 같아서!!
		// Lambda expression's parameter baseNumber cannot redeclare another local variable defined in an enclosing scope. 
		IntConsumer printInt = (i) -> {
			System.out.println(i + baseNumber);
		};
		
		printInt.accept(10);
	}
}
