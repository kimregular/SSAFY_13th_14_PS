package w250210.정규.p4963;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

	static Stream<Arguments> testSource() {
		return Stream.of(
				Arguments.of(new int[][]{{0}}, 0),
				Arguments.of(new int[][]{{0, 1}, {1, 0}}, 1),
				Arguments.of(new int[][]{
						{1, 1, 1},
						{1, 1, 1}
				}, 1),
				Arguments.of(new int[][]{
						{1, 0, 1, 0, 0},
						{1, 0, 0, 0, 0},
						{1, 0, 1, 0, 1},
						{1, 0, 0, 1, 0}
				}, 3)
		);
	}

	@ParameterizedTest(name = "{index} input : {0} : expected {1}")
	@MethodSource("testSource")
	void test(int[][] input, int expected) {
		// given
		Solution s = new Solution();

		// when
		// then
		assertEquals(expected, s.solution(input));
	}

}