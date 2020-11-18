package section5;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * Java8에 새로운 날짜와 시간 API가 생긴 이유
 * 그전까지 사용하던 java.util.Date 클래스는 mutable(변할수 있음)하기 떄문에 thead safe하지 않다.
 * 클래스 이름이 명확하지 않다. Date인데 시간까지 다룸
 * 버그 발생할 여지가 많다. (타입 안정성이 없고, 월이 0부터 시작함...)
 * 날짜 시간 처리가 복잡한 애플리케이션에서는 보통 Joda Time을 씀
 */
public class DateAPI {

	public static void main(String[] args) throws InterruptedException {
		// 기본 자바에 있던 Date
		Date date = new Date();		// 날짜를 표현하는데? 시간도 가지고 있음? Date는 근본적으로 타임스탬프
		long time = date.getTime();	// date은데 시간을 가져옴? 그리고 이 시간은 EPOCK으로 보여줌
		System.out.println(date);
		System.out.println(time);
		
		// 스레드를 3초동안 재움
		Thread.sleep(1000 * 3);
		// 3초후 시간을 기록
		Date after3Seconds = new Date();
		System.out.println(after3Seconds);	// 출력
		after3Seconds.setTime(time);		// 다시 인스턴스의 값을 변경하면 3초이전으로 돌아감
		System.out.println(after3Seconds);	// 이런 것을 mutable하다라고 함 값이 변할 수 있음!!
		// 멀티 스레드 환경에서 thead safe하지 않다
		
		// 버그가 발생할 여지가 많다. month는 0부터 시작해서...
//		Calendar sangjinBirthDay = new GregorianCalendar(1993, 10, 13);
		Calendar sangjinBirthDay = new GregorianCalendar(1993, Calendar.OCTOBER, 13);
		// 타입안정성이 없다!! int타입을 파라미터로 쓸수 있어서 년에 -10이 들어올 수 있다.
		// 해결 방법은 year이라는 타입을 만들거나 enum을 만들어야한다.
		
		/*
		 * 위 이유로 자바8에서 Jada Time이 표준으로 들어와서 JSR-310이라는 스팩으로 표준화해서
		 * java.time 패키지에 들어옴
		 * 디자인 철학? Clear API들이 명확해야한다. 예) Date에서 Time을 꺼내는 등.
		 * 			 Immutable 기존의 인스턴스는 변경되지 않고 새로운 인스턴스를 만듬
		 * 			 Fluent null리턴하거나 null을 받는게 없다
		 * 			 Extensible 양력 음력 등 다양하게 쓸 수 있다.
		 */
	}
}
