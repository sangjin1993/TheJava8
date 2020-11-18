package section4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalAPI {
	
	public static void main(String[] args) {
		List<OnlineClass> springClasses = new ArrayList<>();
		springClasses.add(new OnlineClass(1, "spring boot", true));
		springClasses.add(new OnlineClass(5, "rest api development", false));
		
		// stream을 사용할 때도 Optional을 리턴하는 종료 오퍼레이션이 있음
		springClasses.stream()
					.filter(oc -> oc.getTitle().startsWith("spring"))
					.findFirst();
		// 위 로직의 리턴타입은 Optional 타입으로 나옴
		Optional<OnlineClass> spring = springClasses.stream()
				.filter(oc -> oc.getTitle().startsWith("spring"))
				.findFirst();
		
		// 있는지 검사하는 메소드 isPresent()
		boolean present = spring.isPresent();
		System.out.println(present); // true 리턴
		// title에 없는 jap를 isPresent를 사용하면
		Optional<OnlineClass> jpa = springClasses.stream()
				.filter(oc -> oc.getTitle().startsWith("jap"))
				.findFirst();
		System.out.println(jpa.isPresent());	// false를 리턴
		
		// Optional에 있는 값을 가져오는 방법 get() Optional이 감싸고 있는 타입으로 꺼낼수 있음
		OnlineClass onlineClass = spring.get();
		System.out.println(onlineClass.getTitle());	// 아무런 문제 없이 출력됨
		// 문제는 값이 들어 있으면 아무런 문제가 없지만 
		// 비어있는 Optional이면 runtimeException이 발생
//		OnlineClass onlineClass1 = jpa.get();
//		System.out.println(onlineClass1.getTitle());	// NoSuchElementException이 발생
		
		// 확인을 하고 꺼내야 안전
		if (jpa.isPresent()) {
			OnlineClass onlineClass2 = jpa.get();
			System.out.println(onlineClass2.getTitle());
		}
		
		// if문을 추가하는게 아니라 Optional이 제공하는 메소드로 충분 커버를 할 수 있음 get을 사용하지 않고서
		// Optianl안에 있는 값을 가지고 무언가를 해야한다. isPresent를 이용해서 Consumer 함수형 인터페이스를
		// 람다표현식으로 작성해서 이용할 수 있음
		spring.ifPresent(oc -> System.out.println(oc.getTitle()));
		
		// 이 레퍼런스를 가지고 뒤에서 참조해야 할 때 바로 get으 꺼내는게 아니라
		// orElse()를 사용해서 있으면 가져오고 없으면 ()안에 있는 값을 리턴하라.
		// ()안의 값은 함수형 인터페이스가 들어가는게 아니라 인스턴스가 들어옴
		System.out.println("====spring orElse====");
		OnlineClass onlineClassOrElse = spring.orElse(createNewClass());
		// spring은 있으로 값이 출력됨
		System.out.println(onlineClassOrElse.getTitle());
		// jpa으로 하면 값이 없으므로 새로운 클래스가 사용이됨
		System.out.println("====jpa orElse====");
		OnlineClass onlineClassOrElse2 = jpa.orElse(createNewClass());
		System.out.println(onlineClassOrElse2.getTitle());
		
		System.out.println("====spring orElseGet====");
		// 하지만 있을 때도 만들고 없을 때도 만들어서 orElse()안에 있는 코드는 무조건 실행이 되는 문제점이 있음
		// 해결방법! : orElseGet을 사용!!! orElseGet은 Supplier 함수형 인터페이스를 사용할 수 있음
		OnlineClass onlineClassOrElseGet = spring.orElseGet(() -> createNewClass());
		// 메소드 레퍼런스로 해도 됨
//		OnlineClass onlineClassOrElseGet = spring.orElseGet(OptionalAPI::createNewClass);
		// orElseGet을 사용하면 값이 있는 경우에는 Supplier를 실행을 안함
		System.out.println(onlineClassOrElseGet.getTitle());
		// 함수형 인터페이스이기 때문에 lazy하게 다룰수 있음. 
		System.out.println("====jpa orElseGet====");
		// 값이 없는 경우
		OnlineClass onlineClassOrElseGet1 = jpa.orElseGet(() -> createNewClass());
		System.out.println(onlineClassOrElseGet1.getTitle());

		// orElse는 이미 만들어져 있는 인스턴스나 상수를 참고해서 사용할때
		// orElseGet은 동적으로 무언가 만들고 작업을 해야한다할 경우 사용하는게 적절
		// 뭔가를 만들어 줄수 없을 떄는 orElseThrow()를 사용하여 에러를 던짐
//		OnlineClass onlineClassOrElseThrow = jpa.orElseThrow();
		
		System.out.println("====filter====");
		// Optional에 들어있는 값을 걸러낼때 filter(Predicate)사용
		// filter는 값이 있다고 가정하에 동작. 만약 값이 없으면 동작을 안함
		// 리턴 타입은 Optional 타입
		Optional<OnlineClass> onlineClassFilter = spring.filter(oc -> oc.getId() > 10);
		System.out.println(onlineClassFilter.isEmpty());
	}
	
	private static OnlineClass createNewClass() {
		System.out.println("creating new online class");
		return new OnlineClass(10, "New class", false);
	}
}
