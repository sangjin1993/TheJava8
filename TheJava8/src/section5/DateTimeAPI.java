package section5;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/*
 * Date와 Time API
 * 기계 시간으로 표현하는 방법
 * 인류 시간으로 표현하는 방법
 * 기간을 표현하는 방법
 * 파싱 또는 포메팅
 * 레거시 API 지원
 */
public class DateTimeAPI {
	
	public static void main(String[] args) {
		// 기계 시간을 표현하는 방법 now(), of(epochMilli)
		Instant instant = Instant.now();
//		Instant instant2 = Instant.of();
		// 기계 시간(epochTime)이지만 사용자 친화적으로 출력해줌
		System.out.println(instant);	// 기준시 UTC, GMT
		// 로컬기준으로 사용하고 싶으면
		// ZoneId를 가져와서 atZone으로 값을 넘기면 됨
		ZoneId zone = ZoneId.systemDefault();	// 시스템에 있는 기본값
		System.out.println(zone);				// Asisa/Seoul
		ZonedDateTime zoneDateTime = instant.atZone(zone);
		System.out.println(zoneDateTime);		// Zone에 있는 시간 출력
		
		// 인류용 API
		LocalDateTime now = LocalDateTime.now();
		// Local이라는 단어가 있어 현재 시스템의 Zone정보를 참조함
		System.out.println(now);
		// 만약 서버컴퓨터가 LocalDateTime으로 하면 서버기본 존정보를 참조해서 그 시간대를 쓰일 수 있어 주의
		// of를 써서 특정 시간을 입력할 수 있음
		LocalDateTime birthDay = LocalDateTime.of(1993,  Month.OCTOBER, 13, 0, 0, 0);
		System.out.println(birthDay);
		
		// 기간을 표현하는 방법
		LocalDate today1 = LocalDate.now(); // 현재
		LocalDate thisYearBrithday = LocalDate.of(2021, Month.OCTOBER, 13);	// 내년 생일
		// D-day를 구할 때 Duration을 사용해서
		long dday = Duration.between(today1.atStartOfDay(), thisYearBrithday.atStartOfDay()).toDays();
		System.out.println(dday);
		
		// 포매팅 문자열을 다룰때 사용하면 유용 
		LocalDateTime nowDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		// https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
		// 위 포멧을 가지고 사용하는 것도 유용
		System.out.println(nowDateTime.format(formatter));	// 포멧팅 출력
		
		// 파싱
		// formatter형식의 text를 LocalDate타입으로 만듬
		LocalDate parse = LocalDate.parse("10/13/1993", formatter);
		System.out.println(parse);
		
		// 레거시 API지원
		// Date <-> Instant
		Date date = new Date();
		Instant instant2 = date.toInstant();	// Date타입을 Instant타입으로 바꿀수 있음
		Date newDate = Date.from(instant2);		// 반대로 from()을 이용해서 Instant타입을 Date타입으로 바꿀 수 있음
		// GregorianCalendar <-> LocalDateTime
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		GregorianCalendar.from(zoneDateTime);
		// ZoneId <-> TimeZone
		ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
		TimeZone.getTimeZone(zoneId);
		
		// 팁!! 
//		now.plus(10, TemporalUnit)  TemporalUit은 아무것도 없고 ChronoUnit을 써서!!! 
		now.plus(10, ChronoUnit.DAYS);	// java8에 추가된 기능은 이 상태로는 아무런 일도 안일어남 
		LocalDateTime plus = now.plus(10, ChronoUnit.DAYS);		// 새로운 인스턴스를 만들어서 해야 됨!
		
	}
}
