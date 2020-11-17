package section4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/*
 * Java8에 추가된 인터페이스
 * 오직 값 한 개가 들어있을 수도 있는 컨테이너.
 */
public class OptionalInterface {
	
	public static void main(String[] args) {
		List<OnlineClass> springClasses = new ArrayList<>();
		springClasses.add(new OnlineClass(1, "spring boot", true));
		springClasses.add(new OnlineClass(2, "spring data jpa", true));
		springClasses.add(new OnlineClass(3, "spring mvc", false));
		springClasses.add(new OnlineClass(4, "spring core", false));
		springClasses.add(new OnlineClass(5, "rest api development", false));
		
		// 이런 클래스가 있다고 가정
		OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
		// 얼마나 학습했는지 궁금해서 시간을 출력을 하면 무사히 실행이 될까?
//		Duration stydyDuration = spring_boot.getProgress().getStudyDuration();
//		System.out.println(stydyDuration);
		// NullPointException이 발생 레퍼런스 타입은 기본타입이 Null
		// Null을 참조해서 무슨일을 할 수 없다.
		// 해결할 떄
//		Progress progress = spring_boot.getProgress();
//		if(progress != null) {
//			System.out.println(progress.getStudyDuration());
//		}
		// 이런 식으로 해결을 함. 하지만 이런 코드는 에러를 만들기 좋은 코드 
		// 왜냐 우린 사람이라 null을 체크하는 걸 깜박할 수 있음
		// null을 리턴하는 자체가 문제이다.
		// java8이전에는 별다른 대안이 없었음 
		// 이전에는 두가지 방법으로 해결 null인 경우 에러를 throw하는 것과 
		// 에러가 발생이되면 스택트레이스를 찍어 담게된다 리소스를 쓰게됨!! 
		// 두 번쨰 처리 방법은 Null을 리턴하여 그 코드를 사용하는 클라이언트 코드가 주의하는 방법이 있다.
		
		// Optional OnlineClass에 getProgress 참조
		
		/* Optional의 주의사항
		 * 리턴 값으로만 쓰기를 권장한다. (메소드 매개변수 타입, 맵의 키타입, 인스턴스 필드 타입으로 쓰지말자.)
		 * Optional을 리턴하는 메소드에서 null을 리턴하지 말자.
		 * 프리미티브 타입용 Optional을 따로 쓰자. OptianlInt, OptionalLong...
		 * Collection, Map, Stream Array, Optional은 Optional로 감싸지 말 것.
		 */
	}
}

