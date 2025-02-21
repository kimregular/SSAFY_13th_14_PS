package w250217.정규.p16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.years, ip.fertilizeInfo, ip.treeInfo));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int fertilizeLen = Integer.parseInt(st.nextToken());
		int numOfTrees = Integer.parseInt(st.nextToken());
		int years = Integer.parseInt(st.nextToken());

		int[][] fertilizeInfo = new int[fertilizeLen][fertilizeLen];

		for (int i = 0; i < fertilizeLen; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < fertilizeLen; j++) {
				fertilizeInfo[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] treeInfo = new int[numOfTrees][3];
		for (int i = 0; i < numOfTrees; i++) {
			st = new StringTokenizer(br.readLine());
			treeInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
			treeInfo[i][1] = Integer.parseInt(st.nextToken()) - 1;
			treeInfo[i][2] = Integer.parseInt(st.nextToken());
		}

		return new Input(years, fertilizeInfo, treeInfo);
	}

	private static class Input {

		final int years;
		final int[][] fertilizeInfo;
		final int[][] treeInfo;

		public Input(int years, int[][] fertilizeInfo, int[][] treeInfo) {
			this.years = years;
			this.fertilizeInfo = fertilizeInfo;
			this.treeInfo = treeInfo;
		}
	}
}

class Solution {

	private Field field;
	private PriorityQueue<Tree> trees;
	private Queue<Tree> deadTrees;

	public int solution(int years, int[][] fertilizeInfo, int[][] treeInfo) {
		init(fertilizeInfo, treeInfo); // 풀이 로직 초기화

		while (years-- > 0) { // 주어진 년도 반복
			spring();
			summer();
			fall();
			winter();
		}
		return trees.size(); // 끝나고 남은 나무의 개수 반환
	}

	private void init(int[][] fertilizeInfo, int[][] treeInfos) {
		this.field = new Field(fertilizeInfo);
		this.trees = new PriorityQueue<>();
		this.deadTrees = new ArrayDeque<>();
		for (int[] treeInfo : treeInfos) {
			this.trees.offer(new Tree(treeInfo[0], treeInfo[1], treeInfo[2]));
		}
	}

	private void spring() {
		PriorityQueue<Tree> newTrees = new PriorityQueue<>();
		// 자라난 나무들 임시 저장

		while (!trees.isEmpty()) {
			Tree tree = trees.poll();
			if (field.afford(tree)) {
				// 만약 나무가 먹을 양분이 있으면
				field.sock(tree);
				// 땅에서는 양분이 사라지고
				tree.grow();
				// 나무는 자라남
				newTrees.offer(tree);
				// 임시 저장
			} else {
				deadTrees.offer(tree);
				// 먹을게 없으면 으앙 쥬금
			}
		}
		trees = newTrees;
		// 임시 나무들을 다시 원래 변수에 저장
	}

	private void summer() {
		for (Tree deadTree : deadTrees) {
			// 죽은 나무들을
			field.fertilizeWith(deadTree);
			// 땅의 비료로
		}
		deadTrees.clear();
		// 이거 안 해서 시간초과 났음
	}

	private void fall() {
		int[][] DIRECTIONS = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
		// 8방향 탐색

		Queue<Tree> temp = new ArrayDeque<>();
		// 새로 생긴 나무들 임시 저장

		for (Tree tree : trees) {
			if (tree.age % 5 != 0) continue;
			// 나이가 5배수가 아니면 스킵

			for (int[] direction : DIRECTIONS) {
				int nx = tree.x + direction[0];
				int ny = tree.y + direction[1];

				if (field.isOutOfField(nx, ny)) continue;
				// 밭 범위를 벗어나면 스킵

				temp.offer(new Tree(nx, ny, 1));
				// 새로운 나무 임시 저장
			}
		}
		trees.addAll(temp);
		// 새로 생긴 나무들 모두 저장
	}

	private void winter() {
		field.fertilize();
		// 주어진 수치만큼 땅에 비료 추가
	}
}

class Tree implements Comparable<Tree> {

	int x;
	int y;
	int age;

	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}

	public void grow() {
		age++;
	}

	@Override
	public int compareTo(Tree o) {
		return Integer.compare(age, o.age);
	}
}

class Field {

	private int[][] field;
	private int[][] fertilizeInfo;

	public Field(int[][] fertilizeInfo) {
		this.fertilizeInfo = fertilizeInfo;
		this.field = new int[fertilizeInfo.length][fertilizeInfo.length];
		for (int[] f : field) {
			Arrays.fill(f, 5);
			// 땅의 초기값은 5
		}
	}

	public boolean afford(Tree tree) {
		return field[tree.x][tree.y] >= tree.age;
		// 나무가 먹을 만큼 영양분이 있는지?
	}

	public void sock(Tree tree) {
		field[tree.x][tree.y] -= tree.age;
		// 나무가 영양분 빨아감
	}

	public boolean isOutOfField(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field.length;
	}

	public void fertilize() {
		// 지역에 정해진 양만큼 영양분 추가
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] += fertilizeInfo[i][j];
			}
		}
	}

	public void fertilizeWith(Tree tree) {
		// 죽은 나무를 영양분으로 변환
		field[tree.x][tree.y] += (tree.age / 2);
	}
}