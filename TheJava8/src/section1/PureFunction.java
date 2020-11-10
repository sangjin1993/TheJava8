package section1;

/*
 * 순수함수(Pure function)
 * 사이드 이팩트가 없다. (한수 밖에 있는 값을 변경하지 않는다.)
 * 상태가 없다. (함수 밖에 있는 값을 사용하지 않는다.)
 */
@FunctionalInterface
public interface PureFunction {
	
	int doIt(int number);
	
}
