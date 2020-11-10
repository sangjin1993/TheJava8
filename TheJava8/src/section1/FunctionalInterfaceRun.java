package section1;

public class FunctionalInterfaceRun {
	
	public static void main(String[] args) {
		// Java8 이전에는 익명 내부 클래스 anonymous inner class
		FunctionalInterfaceExam runSomething1 = new FunctionalInterfaceExam() {
			
			@Override
			public void doIt() {
				System.out.println("Hello anonymous inner class");
			}
		};
		// 실행
		runSomething1.doIt();
		
		// 위의 코드를 람다로 표현하면
		FunctionalInterfaceExam runSomething2 = () -> System.out.println("Hello Lambda");
		
		// 실행
		runSomething2.doIt();
		
		// 실행할 코드가 두 줄 이상일 때
		FunctionalInterfaceExam runSomething3 = () -> {
			System.out.println("Hello");
			System.out.println("Lambda");
		};
		// 실행
		runSomething3.doIt();
		
		/*
		 *  람다 표현식을 쓰면 어떤 함수를 정의한거 처럼 보이지만
		 *  자바에서는 특수한 형태의 오브젝트임 
		 *  함수형 인터페이스를 인라인으로 구현한 오브젝트로 볼 수있음
		 *  이러한 형태를 함수처럼 보이지만 사실상 자바는 객체지향 언어라
		 *  이것을 변수에 할당하고 이것을 메소드 파라미터를 전달하고 리턴타입으로 리턴할 수 있음
		 *  이 말은 자바에서 함수를 First class object로 사용할 수 있다는 말임
		 *  
		 */
	}
}

