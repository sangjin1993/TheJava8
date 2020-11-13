package section1;

/*
 * InterfaceDefaultMethods를 구현한 클래스
 * 
 * 만약 여러 인터페이스를 구현할 때 같은 default메소드가 있으면 자바는 컴파일에러를 발생시킴
 * 해결 할려면 메소드를 재정의하면 해결 가능
 */
public class DefaultMethods implements InterfaceDefaultMethods{
	
	String name;
	
	public DefaultMethods(String name) {
		this.name = name;
	}
	
	@Override
	public void printName() {
		System.out.println(this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	// default메소드 재정의
	@Override
	public void printNameUpperCase() {
		System.out.println(this.name.toUpperCase());
	}
	
	public static void main(String[] args) {
		DefaultMethods method = new DefaultMethods("sangjin");
		method.printName();	// sangjin출력
		method.printNameUpperCase(); // SANGJIN출력
		
		// static 메소드
		InterfaceDefaultMethods.printAnything();
				
	}
}
