package section1;

// 추상 메소드가 하나인 인터페이스 = 함수형 인터페이스
// 함수형 인터페이스를 정의 할 때는 @FunctionalInterface 어노테이션을 사용
// 어노테이션 사용시 문법에 위반되면 에러 발생
@FunctionalInterface
public interface FunctionalInterfaceExam {
	
	// 인터페이스에서 abstract를 생략 할수 있음 밑에있는것과 같은 것
//	abstract void doIt();
	
	void doIt();
	
	// 두 개가 있다 함수형인터페이스가 아님
//	void doItAgain();
	
	// java8 부터 인터페이스 안에 static 메소드를 정의 할 수 있음
	static void printName() {
		System.out.println("SangJin");
	}
	
	// java8 부터 default 메소드도 정의 할 수 있음
	default void printAge() {
		System.out.println("28");
	}
	
	// 함수형 인터페이스는 다른 메소드가 있어도 되지만 추상메소드는 오직 하나일 때만
}
