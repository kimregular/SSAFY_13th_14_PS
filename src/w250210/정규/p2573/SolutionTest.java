package w250210.정규.p2573;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@ParameterizedTest(name = "{index}")
	@MethodSource("testSource")
	void test(int[][] input, int output) {
		// given
		Solution s = new Solution();

		// when
		// then
		assertEquals(output, s.solution(input));
	}

	private static Stream<Arguments> testSource() {
		return loadData()
				.stream()
				.map(testCase -> Arguments.of(testCase.input, testCase.output));
	}

	private static List<TestCase> loadData() {
		List<TestCase> result = new ArrayList<>();

		for (int i = 1; i < 6; i++) {
			String inputFilePath = "src/w250210/정규/p2573/source/input" + i + ".txt";
			String outputFilePath = "src/w250210/정규/p2573/source/output" + i + ".txt";
			result.add(new TestCase(readInput(inputFilePath), readOutput(outputFilePath)));
		}
		return result;
	}

	private static int[][] readInput(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int[][] field = new int[height][width];

			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < width; j++) {
					field[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			return field;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static int readOutput(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			return Integer.parseInt(br.readLine());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class TestCase {
		final int[][] input;
		final int output;

		public TestCase(int[][] input, int output) {
			this.input = input;
			this.output = output;
		}
	}
}