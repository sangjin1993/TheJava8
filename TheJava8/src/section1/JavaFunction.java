package section1;

import java.util.function.Function;

/*
 * Function <T, V> 첫 번쨰는 입력값의 타입 두 번쨰는 리턴값의 타입
 */
public class JavaFunction implements Function<Integer, Integer>{

	@Override
	public Integer apply(Integer integer) {
		// 어떤 값을 받아서 10을 더해줌
		return integer + 10;
	}

}
