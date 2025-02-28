package w250224.정규.p2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@ParameterizedTest
	@MethodSource("inputSource")
	void test(int num, String expected) {
		// given
		Solution s = new Solution();

		// when
		// then
		assertEquals(expected, s.solution(num));
	}

	static Stream<Arguments> inputSource() {
		return Stream.of(Arguments.of(4, "2333\n" + "2339\n" + "2393\n" + "2399\n" + "2939\n" + "3119\n" + "3137\n" + "3733\n" + "3739\n" + "3793\n" + "3797\n" + "5939\n" + "7193\n" + "7331\n" + "7333\n" + "7393"));
	}

}