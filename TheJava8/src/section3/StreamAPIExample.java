package section3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * StreamAPI
 * 걸러내기
 * 	Filter(Predicate)
 * 	예) 이름이 3글자 이상인 데이터만 새로운 스트림으로
 * 변경하기
 * 	Map(Funcction) 또는 FlatMap(Function)
 * 	예) 각각의 Post 인스턴스에서 String title만 새로운 스트림으로
 *  예) List<Stream<String>>을 String의 스트림으로
 * 생성하기
 * 	generate(Supplier) 또는 Iterate(T seed, UnaryOperator)
 * 	예) 10부터 1씩 증가하는 무제한 숫자 스트림
 * 	예) 랜덤 int 무제한 스트림
 * 제한하기
 * 	limit(long) 또는 skip(long)
 * 	예) 최대 5개의 요소가 담기 스트림을 리턴한다.
 * 	예) 앞에서 3개를 뺸 나머지 스트림을 리턴한다.
 * 
 * 스트림에 있는 데이터가 특정 조건을 만족하는지 확인
 * 	anyMatch(), allMatch(), nonMatch()
 * 	예) k로 시작하는 문자열이 있는지 확인한다. (true 또는 false를 리턴한다.)
 * 	예) 스트림에 있는 모든 값이 10보다 작은지 확인한다.
 * 개수 세기
 * 	count()
 * 	예) 10보다 큰 수의 개수를 센다.
 * 스트림을 데이터 하나로 뭉치기
 * 	reduce(identity, BiFunction), collect(), sum(), max()
 * 	예) 모든 숫자 합 구하기
 * 	예) 모든 데이터를 하나의 List 또는 Set에 옮겨 담기
 */
public class StreamAPIExample {
	
	public static void main(String[] args) {
		
		List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        // 내 코드
        System.out.println("======my code======");
        List<OnlineClass> springStartTitle = new ArrayList<>();
        for (int i = 0; i < springClasses.size(); i++) {
        	if(springClasses.get(i).getTitle().startsWith("spring")) {
        		springStartTitle.add(springClasses.get(i));
        	}
        }
        springStartTitle.forEach((c) -> System.out.println(c.getId()));
        
        // 기선쌤 코드
        System.out.println("======keesunSSem======");
        springClasses.stream()
        			.filter(oc -> oc.getTitle().startsWith("spring"))
        			.forEach(oc -> System.out.println(oc.getId()));
        // 스트림 파이프라인을 만들고 중계 오퍼레이션으로 filter 종료(터미널) 오퍼레이션으로 forEach사용 
        // filter은 스트림 파이프라인에 지나가는 특정 타입(OnlineClass)의 인스턴스를 찾아서 
        // forEach로 출력
        
        System.out.println("close 되지 않은 수업");
        // 내 코드
        System.out.println("======my code======");
        // 이 때 생각... 그냥 filter를 쓰면 될 줄알았음... 하지만 쓰는 방법만 알았지 어떻게 쓰는지는 모름 ㅠㅠ 반성.
        springClasses.stream().filter(oc -> oc.isClosed())
        					.forEach(oc -> System.out.println(oc.getId()));
        
        // 기선쌤 코드 
        System.out.println("======keesunSSem======");
        // 여기서는 Predicate false나 true를 리턴
        // isClosed는 강의가 닫혀있다는 것이닌깐 닫히지 않은 false를 받아야 올바른 해결책.. 독해를 못했네...
        springClasses.stream().filter(oc -> !oc.isClosed())
        						.forEach(oc -> System.out.println(oc.getId()));
        // 위에 코드를 단축시킬 수 있음?(단축인가?) !! 메소드 레퍼런스, 스태틱 메소드까지 활용하면
        // filter 안에 ! 을 쓸수가 없지만 Predicate 인터페이스의 스태틱 메소드 not()을 사용하면 해결 가능!!!
        springClasses.stream().filter(Predicate.not(OnlineClass::isClosed))
        					.forEach(oc -> System.out.println(oc.getId()));
        
        
        System.out.println("수업 이름만 모아서 스트림 만들기");
        // 내 코드
        System.out.println("======my code======");
        List<String> classNameList = new ArrayList<>();
        for (int i = 0; i < springClasses.size(); i++) {
        	classNameList.add(springClasses.get(i).getTitle());
        }
        classNameList.forEach(System.out::println);
        
        // 기선쌤 코드
        System.out.println("======keesunSSem======");
        // map에 들어온 타입은 OnlineClass타입 이지만 나갈때는 다른 타입으로 만들수 있음 이떄는 getTitle이 String타입이므로 map의 리턴타입은 String
        springClasses.stream().map(oc -> oc.getTitle())
        					.forEach(System.out::println);

        
        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // 내 코드
        System.out.println("======my code======");
        for (int i = 0; i < keesunEvents.size(); i++) {
        	for (int j = 0; j < keesunEvents.get(i).size(); j++) {
        		System.out.println(keesunEvents.get(i).get(j).getId());
        	}
        }
        // 기선쌤 코드
        System.out.println("======keesunSSem======");
        // List안에 List
        // Stream안에 List가 흐름 이 List를 flat해서 납짝?하게 만든다? 안에있는 것들을 풀어 냄
        // 하나의 스트림으로 flat시킴 중계형 오퍼레이션
        keesunEvents.stream()
//        			.flatMap(list -> list.stream()) 메소드 레퍼런스로 바꿀수 있음
        			.flatMap(Collection::stream)
    				.forEach(oc -> System.out.println(oc.getId()));		// 여기서는 OnlineClass타입으 옴
        // 현재 오는 오퍼레이터에 안에 타입이 무엇인지 생각하고 계산할 수 있어야 함.!!! 오퍼레이터에 따라 달라질 수 있기 때문에
        	
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // 무재한 스트림 이 상태로는 아무것도 안일어남 중계형 오퍼레이터닌깐!
        Stream.iterate(10, i -> i + 1);
        Stream.iterate(10, i -> i + 1)
        		.skip(10)
        		.limit(10)
        		.forEach(System.out::println);
        
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        // anyMatch는 종료형 오퍼레이터이기 때문에 boolean을 리턴
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);
        
        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        // 이것도 종료형 오퍼레이터
        List<String> filterMapSpring = springClasses.stream()				// OnlineClass타입
        					.filter(oc -> oc.getTitle().contains("spring"))	// OnlineClass타입
        					.map(OnlineClass::getTitle)						// String 타입
        					.collect(Collectors.toList());
        filterMapSpring.forEach(System.out::println);
        // map 과 filter는 순서를 바꿀수 있음 주의사항! 타입이 바낄 수 있으니 잘 생각해서 사용!!!
        List<String> mapFilterSpring = springClasses.stream()	// OnlineClass
        					.map(OnlineClass::getTitle)			// String
        					.filter(t -> t.contains("spring"))	// String
        					.collect(Collectors.toList());
        mapFilterSpring.forEach(System.out::println);
	}
}
