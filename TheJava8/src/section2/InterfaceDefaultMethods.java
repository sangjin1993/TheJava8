package section2;

/*
 * 인터페이스의 기본 메소드
 * 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
 * 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
 * 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
 * 	컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.
 * 	반드시 문서화 할 것.(@implSpec 자바독 태그 사용)
 * Object가 제공하는 기능(equals, hasCode)는 기본 메소드로 제공할 수 없다.
 * 	구현체가 재정의해야 한다.
 * 본인이 수정할 수 있는 인터페이스에만 기본 메소드를 추가 할 수 있다.
 * 인터페이스를 상속받고 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
 * 인터페이스 구현체가 재정의 할 수도 있다.
 */
public interface InterfaceDefaultMethods {

	void printName();
	
	//인터페이스에 새 기능 추가하면 이 인터페이스를 구현한 클래스에서 모두 컴파일 에러가 발생
//	void printNameUpperCase();
	
	// default 키워드로 새기능 추가할 수 있음
	/**
	 * @implSpec 이 구현체는 getNmae()으로 가져온 문자열을 대문자로 바꿔 출력한다.
	 * 이런 식으로 상세하게 설명해야 한다.
	 * 만약 이 메소드가 문제를 일으키면 재정의 할 수 있다.
	 */
	default void printNameUpperCase() {
		System.out.println(getName().toUpperCase());
	}
	
	// 컬렉션에 removeIf를 보면 default 메소드로 컬렉션의 하위 클래스들이 이 기능을 모두 가질 수 있음
	
	// 이 getName이 어떤 값을 가져오는지 모르기 때문 null이 넘어오면 런타임Excetion 발생할수도 있음
	String getName();
	
	// A default method cannot override a method from java.lang.Object 
	// 제약사항으로 Object가 제공하는 기능은 기본 메소드로 추가할 수 없다.
//	default String toString() {}
	
	// 인터페이스에서 추상메소드로 선언하는 것은 오류가 발생하지 않음
	// 선언하더라도 모든 Object가 구현하는 메소드 중에 하나이고
	// 인스턴스를 만들기만 하면 기본 구현체는 Object가 제공. 추상메소드로 취급 안함
	// 인터페이스의 제약사항이 있을 경우에만 쓴다. 
	// Object에서 제공하는 기능과 다를 때
	String toString();
	
	// 위는 인스턴스가 사용할 수 있는 것
	// 해당 인터페이스를 구현한 모든 인스턴스 또는 해당 타입에 관련된어 있는 유틸리티 핼퍼메소드 제공하고 싶은경우
	// static 메소드를 제공 할 수 있다.
	static void printAnything() {
		System.out.println("Name");
	}
}
