package section1;

public class PureFunctionRun {
	public static void main(String[] args) {
		
//		PureFunction runPureFunction1 = (number) -> {
//			return number + 10; 
//		};
		
		// 간추려서 사용하면
		PureFunction runPureFunction1 = (number) -> number + 10;
		// 1을 입력 받았으면 계속해서 11이 나와야 함
		// 수학적 함수에서 가장 중요한 것은 입력받은 값이 동일한 경우 결과가 같아야 한다.
		// 이 결과를 보장하지 못한다면 함수형 프로그램이라고 보기 어려움
		System.out.println(runPureFunction1.doIt(1));
		System.out.println(runPureFunction1.doIt(1));
		System.out.println(runPureFunction1.doIt(1));
		
		// 함수의 밖 있는 값
		int baseNumber = 10;
		
		// 이런 경우는 상태값을 가지고 있다 어떤 값에 의존한다라고 해서 퓨어한 함수가 아님
		PureFunction runPureFunction2 = new PureFunction() {
			// 여기도 함수의 밖에 있는 값
			int baseNumber = 10;
			
			@Override
			public int doIt(int number) {
				return number + baseNumber;
			}
		};
		
		// 또다른 케이스는 외부에 있는 ㄱ밧을 변경할려는 경우
		// 여기에 선언된 변수를 사용하면 문법적으로 안됌
//		int baseNumber1 = 10;
		PureFunction runPureFunction3 = new PureFunction() {
			// 이 경우는 문법적으로 가능
			// 가능하지만 퓨어한 함수가 아님
			int baseNumber1 = 10;
			
			@Override
			public int doIt(int number) {
				baseNumber1++;
				return number + baseNumber1;
			}
		};
		
		final int baseNumber2 = 10;
		
		PureFunction runPureFunction4 = new PureFunction() {
			
			@Override
			public int doIt(int number) {
				return number + baseNumber2;
			}
		};
		
		// 밖에서 변경할려고 하면 오류 -> 이유는 baseNumber2를 파이널이라고 가정하고 쓰는 것!!
//		baseNumber2++;
		
	}
	 
}
