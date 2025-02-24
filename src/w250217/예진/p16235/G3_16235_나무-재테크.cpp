#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<deque>
using namespace std;

int n, m, k, treeCnt;
vector<int> trees[11][11];
int plusFood[11][11], food[11][11];
queue<pair<pair<int, int>, int>> toFood;
queue<pair<int, int>> toSpread;
int dy[8] = { 0, 0, -1, 1, -1, -1, 1, 1 }, dx[8] = { -1, 1, 0, 0, -1, 1, -1, 1 };

void spring(int y, int x) {
	sort(trees[y][x].begin(), trees[y][x].end());
	int size = trees[y][x].size(), idx = 0;
	for (int i = 0; i < size; i++) {
		int treeAge = trees[y][x][idx];
		//if (treeAge == 0) continue; // 죽은 나무, 양분된 나무

		// 양분이 나무보다 덜 남음, 죽어야함
		if (food[y][x] < treeAge) {
			//trees[y][x][idx] = 0;
			toFood.push({ {y, x}, treeAge });
			treeCnt--;
			trees[y][x].erase(trees[y][x].begin() + idx);
			continue;
		}

		idx++;
		// 나이만큼 양분 먹음
		food[y][x] -= treeAge;
		trees[y][x][i]++;

		// 나이가 5의 배수이면 번식 queue에 추가
		if ((treeAge + 1) % 5 == 0) toSpread.push({ y, x });
	}

}

// 죽은 나무 -> 양분
void summer() {
	while (!toFood.empty()) {
		int currY = toFood.front().first.first;
		int currX = toFood.front().first.second;
		int age = toFood.front().second;
		toFood.pop();

		food[currY][currX] += (age / 2);
	}
}

// 나이 5배수인 나무 팔방 번식
void fall() {
	while (!toSpread.empty()) {
		int currY = toSpread.front().first;
		int currX = toSpread.front().second;
		toSpread.pop();

		for (int i = 0; i < 8; i++) {
			int nextY = currY + dy[i];
			int nextX = currX + dx[i];

			if (nextY < 1 || nextX < 1 || nextY > n || nextX > n) continue;

			trees[nextY][nextX].push_back(1);
			treeCnt++;
		}
	}
}

void winter() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			food[i][j] += plusFood[i][j];
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m >> k;
	treeCnt = m;

	//fill(&food[1][1], &food[n][n + 1], 5);
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			food[i][j] = 5;
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> plusFood[i][j];
		}
	}

	for (int i = 0; i < m; i++) {
		int y, x, age;
		cin >> y >> x >> age;
		trees[y][x].push_back(age);
	}

	for (int y = 0; y < k; y++) {

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (trees[i][j].size() == 0) continue;
				spring(i, j);
			}
		}

		summer();
		fall();
		winter();
	}

	cout << treeCnt << "\n";


}