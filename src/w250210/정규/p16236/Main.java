package w250210.정규.p16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
		// 메인 로직 구동 부
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution(); // 풀이 로직 호출
			System.out.println(s.solution(readInput(br)));
			// 풀이 후 결과 출력

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[][] readInput(BufferedReader br) throws IOException {
		// 인풋 처리 메서드
		// 백준 인풋을 필요한대로 가공하여 풀이 로직에 제공
		int len = Integer.parseInt(br.readLine());
		int[][] result = new int[len][len];
		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return result;
	}
}

class Solution {
	// 풀이 로직 클래스

	private Ocean ocean; // 바다의 상태
	private BabyShark babyShark; // 아기 상어 상태

	public int solution(int[][] map) {
		init(map); // 인스턴스 초기화

		int cnt = 0; // 결과값 -> 엄마 상어에게 도움을 요청하기까지 걸리는 시간

		while (ocean.edibleFishAround()) {
			// 바다에 먹을 수 있는 물고기가 있는 동안 반복

			int[] target = ocean.getTargetPathAndSize(babyShark.getLocation(), babyShark.getSize());
			// 먹을 수 있는 물고기의 목록 생성

			if (target == null) break;
			// 먹을 수 있는 물고기 목록이 비어있다면 종료

			cnt += babyShark.moveAndEat(target, ocean.removeFishAt(target));
			// 물고기를 먹으러 가는 시간을 결과값에 저장
			// 먹힌 물고기는 바다에서 제거
		}

		return cnt;
		// 도움을 요청하는 시간 반환
	}

	private void init(int[][] map) {
		this.ocean = new Ocean(map);
		this.babyShark = new BabyShark(ocean.initShark());
	}
}

class Ocean {
	// 바다의 상태를 관리하는 클래스

	private int[][] field;
	// 바다 지도
	private int edibleFishCount;
	// 먹을 수 있는 물고기가 몇마리인지 저장

	public Ocean(int[][] map) {
		this.field = map;
		countEdibleFish();
		// 먹을 수 있는 물고기 저장하기
	}

	public int[] initShark() {
		// 상어의 위치를 전달 -> 이 값으로 아기상어 생성함
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == 9) {
					field[i][j] = 0;
					return new int[]{i, j};
				}
			}
		}
		return new int[]{0, 0};
		// 상어의 위치가 없다면  {0, 0}
		// 여기까지 진행될 일은 없다
	}

	public boolean edibleFishAround() {
		// 먹을 물고기가 있냐?
		return edibleFishCount > 0;
	}

	public int[] getTargetPathAndSize(int[] sharkLocation, int sharkSize) {
		// 먹을 수 있는 물고기의 목록 반환하는 메서드
		// 먹을 수 있다면 그 물고기까지의 거리와 그 물고기의 크기를 반환

		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		// 4방 탐색

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[field.length][field[0].length];
		q.offer(new int[]{sharkLocation[0], sharkLocation[1], 0});
		visited[sharkLocation[0]][sharkLocation[1]] = true;
		// 처음 상어가 있는 위치를 방문 처리

		List<int[]> candidates = new ArrayList<>();
		// 먹을 수 있는 물고기 후보 저장
		int minDist = Integer.MAX_VALUE;
		// 먹을 수 있는 물고기 거리 저장

		while (!q.isEmpty()) {
			int[] cur = q.poll(); // 현재 상어의 위치
			int x = cur[0];
			int y = cur[1];
			int dist = cur[2];

			if (dist > minDist) break;
			// 이미 먹을 수 있다고 알고 있는 물고기보다 멀리 있다면 순환 종료

			if (0 < field[x][y] && field[x][y] < sharkSize) {
				// 해당 위치가 먹을 수 있는 물고기의 위치라면
				candidates.add(new int[]{x, y, dist});
				// 먹이 후보에 추가
				minDist = dist;
				// 먹이 후보 거리 갱신
				continue;
			}

			for (int[] dir : directions) {
				// 물고기가 없거나 상어와 크기가 같은 물고기(먹을 수 없음)가 있는 위치
				int nx = x + dir[0];
				int ny = y + dir[1];

				if (isOutOfBounds(nx, ny)) continue; // 바다 밖이면 안되고
				if(visited[nx][ny]) continue; // 이미 확인한 곳이면 안 되고
				if(field[nx][ny] > sharkSize) continue; // 상어보다 큰 물고기가 있다면 안됨
				// 위의 조건을 모두 통과하면 아래 로직 실행

				visited[nx][ny] = true; // 방문추가하고
				q.offer(new int[]{nx, ny, dist + 1});
				// q에 추가
			}
		}

		candidates.sort((a, b) -> { // 먹을 수 있는 물고기들의 우선순위 설정
			// 1. 나에게서 가장 가까운 물고기
			if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
			// 2. 가까운 물고기가 여러마리라면 가장 왼쪽에 있는 물고기
			if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
			// 3. 가장 왼쪽 물고기가 여러마리라면 가장 위에 있는 물고기
			return Integer.compare(a[1], b[1]);
			// 순으로 정렬
		});

		return candidates.isEmpty() ? null : candidates.get(0);
		// 먹이 후보가 비어있다면 null
		// 후보가 있다면 첫번째 후보 반환
	}

	private boolean isOutOfBounds(int x, int y) {
		// 바다 밖인지 확인하는 메서드
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}

	public int removeFishAt(int[] target) {
		// 잡아먹힌 물고기를 바다에서 제거
		if (field[target[0]][target[1]] > 0) edibleFishCount--;
		// 잡아먹히면서 먹을 수 있는 물고기 개수 감소
		field[target[0]][target[1]] = 0; // 크기 0 처리
		return target[2];
		// 잡아먹은 물고기의 거리 반환
	}

	private void countEdibleFish() {
		// 먹을 수 있는 물고기 개수 카운트
		edibleFishCount = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] > 0 && field[i][j] < 9) {
					edibleFishCount++;
				}
			}
		}
	}
}

class BabyShark {
	// 아기상어 관리 클래스
	private int[] location;
	// 현재 상어의 위치
	private int size;
	// 현재 상어의 크기
	private int eatCount;
	// 몇마리 먹었나?

	public BabyShark(int[] location) {
		this.location = location;
		this.size = 2; // 처음 크기는 2이다.
		this.eatCount = 0; // 처음 먹은 물고기는 0마리이다.
	}

	public int[] getLocation() {
		return location;
		// 현재 위치 반환
	}

	public int getSize() {
		return size;
		// 현재 크기 반환
	}

	/**
	 *
	 * @param targetLocation 물고기의 위치
	 * @param distance 물고기의 거리
	 * @return distance 물고기의 거리 그대로 반환
	 */
	public int moveAndEat(int[] targetLocation, int distance) {
		// 이동해서 물고기 먹기
		location = targetLocation; // 상어의 위치 변환
		eatCount++; // 먹은 물고기 개수 변환
		if (eatCount == size) { // 먹은 개수가 크기와 같다면
			size++; // 크기 키우고
			eatCount = 0; // 먹은 물고기 소화시키기
		}
		return distance; // 잡아먹힌 물고기까지의 이동거리 반환
	}
}
