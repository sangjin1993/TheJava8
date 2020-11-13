package section2;

/*
 * InterfaceDefaultMethods를 상속받은 인터페이스
 */
public interface ExtendsInterface extends InterfaceDefaultMethods {

	// ExtendsInterface에서는 InterfaceDefaultMethods가 제공하는 기본 메소드 제공하고 싶지 않을 때
	// 다시 추상 메소드로 선언하면 된다. 하지만 다시 구현한 클래스에서 재정의를 해야한다.
	void prinrtNameUpperCase();
	
}
