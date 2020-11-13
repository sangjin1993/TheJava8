package section2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

/*
 * 자바 8에서 추가한 기본 메소드로 인한 API변화
 * Iterable의 기본 메소드
 * 	forEach()
 * 	spliterator()
 * Collection의 기본 메소드
 * 	stream()/ parallelStream()
 * 	removeIf(Predicate)
 * 	spliterator()
 * Comparator의 기본 메소드 및 스태딕 메소드
 * 	reversed()
 * 	thenComparing()
 * 	static reverseOrder()/ naturalOrder()
 * 	static nullsFirst()/ nullsLast()
 * 	static comparing()
 * 
 */
public class Java8API {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("sangjin");
		names.add("keesun");
		names.add("whiteship");
		names.add("toby");
		names.add("foo");
		
		// forEach() Consumer 함수형 인터페이스 사용
		names.forEach((s) -> System.out.println(s));
		// 메소드 레퍼런스
		names.forEach(System.out::println);
		
		// spliterator() 쪼갤수 있는 기능을 가지고 있는 Iterator
		Spliterator<String> spliterator = names.spliterator();
		// tryAdvance로 Consumer를 받아서 순회 있으면 true 없으면 false를 리턴
		while (spliterator.tryAdvance(System.out::println));
		
		System.out.println("=====================");
		
		// split하는 기능은 trySplit()
		// 병렬적으로 처리할 때 유용, 
		Spliterator<String> spliterator1 = names.spliterator();
		Spliterator<String> spliterator2 = spliterator1.trySplit();
		while (spliterator1.tryAdvance(System.out::println));
		System.out.println("==========trySplit===========");
		while (spliterator2.tryAdvance(System.out::println));
		
		// stream()는 spliterator를 사용하여 만들어져 있음
		// K를 포함한 names의 엘리먼트 갯수
		long k = names.stream().map(String::toUpperCase)
					.filter(s -> s.startsWith("K"))
					.count();
		System.out.println(k);
		
		// removeIf() Predicate 함수형 인터페이스 사용하여 k가 들어있는 요소 삭제
		names.removeIf(s -> s.startsWith("k"));
		names.forEach(System.out::println);
		
		// Comparator는 함수형 인터페이스 sort할 떄 많이 쓰임
		// 문자 순으로 정렬
		names.sort(String::compareToIgnoreCase);
		// 역순으로 할때? reversed() 사용!
		Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
		names.sort(compareToIgnoreCase.reversed());
		// 또다른 조건으로 재정렬을 할 때! thenComparing()
		names.sort(compareToIgnoreCase.reversed().thenComparing(String::length));
		
		// Java8로 업데이트 되고 API설계나 API를 제공하는 라이브러리에 변화가 생김
		// 인터페이스가 있고 추상 메서드를 3개 가지고 있음
		// 이런 경우 제공할 때 추상클래스를 하나 만들고 비어있는 구현체를 넣어 놓음
		// 이유? 실제로 구현할 때 인터페이스를 다 구현하는게 아니라 a는 a, b는 b, c는 c만 구현
		// 편의성을 제공하기 위해서 java8이전에
		// Java8이후에는 인터페이스에서 제공할 수 있게됨 기본 메소드를 사용해서 a, b, c로 구현
		// 이 인터페이스를 Impl를 하게됨 
		// 차이점! 클래스를 상속을 쓰면서 구현 이렇게 하면 상속은 하나만 할 수 있는 불편함이 생김
		// 		 구현하는 클래스들은 인터페이스를 구현한거기 때문에 상속이 자유로움 내가 만든 프레임웍때문에 
		//		 상속이 강제되지 않고 비침투적으로 간결하게 코딩할 수 있음(스프링이 대표적)
		// WebMvcConfigurer인터페이스를 구현한 추상클레스인 WebMvcConfigurerAdapter가 @deprecated되었음
	}
}
