package section1;

public class Greeting {
	
	private String name;
	
	// 기본 생성자
	public Greeting() {
		
	}
	
	// 파라미터를 받는 생성자
	public Greeting(String name) {
		this.name = name;
	}
	
	// getter
	public String getName() {
		return name;
	}
	
	
	// 인스턴스 메소드
	public String hello(String name) {
		return "hello " + name;
	}
	
	// 스태틱 메소드
	public static String hi(String name) {
		return "hi " + name;
	}
}
