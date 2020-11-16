package section3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Stream
 * 	sequence of elements supporting sequential and parallel aggregate operations
 * 	파파고 왈 : 순차 및 병렬 애그리게이트 작동을 지원하는 요소의 순서
 *  기선쌤 왈 :  sequence of elements (요소들의 연속인데) sequential and parallel(sequential 또는 parallel하게 처리할 수 있는)
 *  		   aggregate operations (operations를 사용해서 처리할 수 있는 일련에...)
 *	데이터를 담고 있는 저장소 (컬렉션)이 아니다.
 *	Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
 *	무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
 *	중개 오퍼레이션은 근본적으로 lazy하다.
 *	손쉽게 병렬 처리할 수 있다.
 *
 * 스트림 파이프라인
 * 	0 또는 다수의 중개 오퍼레이션(intermediate operation)과 한 개의 종료 오퍼레이션(termial operation)으로 구성한다.
 * 	스트림의 데이터 소스는 오직 터미널 오퍼레이션을 실행할 때에만 처리한다.
 * 
 * 중개 오퍼레이션
 * 	Streeam을 리턴한다.
 * 	Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다.
 *  (대부분은 Stateless지만 distinct나 sorted 처럼 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
 * 	filter, map, limit, skip, sorted, ...
 * 
 * 종료 오퍼레이션
 * 	Stream을 리턴하지 않는다.
 * 	collect, allMatch, count, forEach, min, max, ...
 */
public class StreamAPI {
	
	public static void main(String[] args) {
		// Stream은 List, Set 등 콜렉션이나 연속된 데이터를 처리하는 오퍼레이션들의 모임 이 자체는 데이터가 아님
		// 데이터를 가지고 처리하는 게 Stream
		
		List<String> names = new ArrayList<>();
		names.add("sangjin");
		names.add("keesun");
		names.add("whiteship");
		names.add("tody");
		names.add("foo");
		
		// Function in nature라는 뜻은 소스를 변경하지 않는다.
		names.stream().map(String::toUpperCase);
		// 이 결과가 또다른 Stream이 되는 것. (중계형)
		Stream<String> stringStream = names.stream().map(String::toUpperCase);
		// names의 데이터가 대분자로 바뀌지 않음
		names.forEach(System.out::println);
		System.out.println("============구 분=============");
		
		// 중계 오퍼레이션은 근본적으로 lazy하다라는 뜻 lazy = 게으른
		// 스트림 API는 두가지로 나눌수 있다
		// 중계, 종료 오퍼레이션이 있다.
		// 가장 큰 차이점 중계는 Stream을 리턴 종료는 다른 타입을 리턴한다. 
		names.stream().map((s) -> {
			// 과연 이름이 출력이 될까?		답!!! 출력이 되지 않음!!!
			System.out.println(s);
			return s.toUpperCase();
		});
		// 위 로직의 리턴타입은 스트림 !!
		// 중계형 오퍼레이터는 터미널(종료)오퍼레이가 오기 전까지 실행되지 않음
		System.out.println("============구 분=============");
		
		names.forEach(System.out::println);
		
		System.out.println("==========종료 오퍼레이터 사용==============");
		// 스트링 파이프라인은 중계오퍼레이터를 0 또는 여러개를 넣을 수 있음 하지만 반드시 종료 오퍼레이터가 있어야됨
		// 종료 오퍼레이터가 없으면 종계오퍼레이터는 실행이 안됌.!!! 그냥 정의만 한거
		// 종료 오퍼레이터가 있으면
//		names.stream().map((s) -> {
//			System.out.println(s);
//			return s.toUpperCase();
//		}).collect(Collectors.toList());
		// 이 로직의 리턴 타입은 List    Stream이 아님!!!
		List<String> collect = names.stream().map((s) -> {
			System.out.println(s);
			return s.toUpperCase();
		}).collect(Collectors.toList());
		// 이제 처리를 함
		// 대문자로 변환한 collect 출력
		collect.forEach(System.out::println);
		// 원본 소스는 바뀌지 않음
		System.out.println("============원본 소스============");
		names.forEach(System.out::println);
		// 또다른 의미로 lazy가 있음. 그 lazy는 다 처리하지 않아도 Stream을 중간에 끝낼수 있다로 쓰임
		
		// Stream사용하면 손쉽게 병렬처리를 할 수 있음
		// Stream을 사용하지 않고 toUpperCase()
//		for(String name : names) {
//			System.out.println(name.toUpperCase());
//		}
		// 위 작업을 병렬적으로 처리하기 어려움. 하지만 parallelStream사용하면 병렬적으로 할 수 있음!! 
		List<String> paralleCollect = names.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("====parallelStream====");
		paralleCollect.forEach(System.out::println);
		// 병렬적으로 처리하는지 스레드 확인
		System.out.println("====parallelStream할 때 스레드====");
		List<String> checkThread = names.parallelStream().map((s) -> {
			// 스레드 이름을 찍음
			System.out.println(s + " " + Thread.currentThread().getName());
			return s.toUpperCase();
		}).collect(Collectors.toList());
		// ForkJoinPool를 이용해서 병렬적으로 처리함!!
		// 그냥 Stream을 사용하면?
		System.out.println("====stream할 때 쓰레드====");
		List<String> checkThread1 = names.stream().map((s) -> {
			// 스레드 이름을 찍음
			System.out.println(s + " " + Thread.currentThread().getName());
			return s.toUpperCase();
		}).collect(Collectors.toList());
		// 메인 스레드에서 처리!!
		
		// 주의할 점 병렬처리를 한다고 무조건 빨라지는게 아님 
		// 스레드를 만들 때 처리 비용이 듬. 쓰레드를 만들고 처리하고 컨텍스트 스위치를 할때 비용이 많이 듬 
		// 데이터가 정말 방대하게 많을 경우에만 쓰는게 좋음
		// 직접 만들어보고 시간을 재서 사용하는 것이 이용할 때 좋음.
	}
}
