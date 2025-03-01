#include<iostream>
#include<queue>
using namespace std;

int n, l, r, cntTeam = 0;
int arr[51][51];
bool visit[51][51];
int dy[4] = { 0, 0, -1, 1 }, dx[4] = { -1, 1, 0, 0 };

void bfs(int y, int x) {
	queue<pair<int, int>> q, teamQ;
	q.push({ y, x });
	teamQ.push({ y, x });
	visit[y][x] = true;
	int cnt = 0, sum = 0;

	while (!q.empty()) {
		int currY = q.front().first;
		int currX = q.front().second;
		q.pop();
		cnt++;
		sum += arr[currY][currX];

		for (int i = 0; i < 4; i++) {
			int nextY = currY + dy[i];
			int nextX = currX + dx[i];

			if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) continue;

			// 이미 방문한 칸이거나, 차이가 l, r 범위 내에 있지 않을 시 continue
			int diff = abs(arr[currY][currX] - arr[nextY][nextX]);
			if (visit[nextY][nextX] || diff < l || diff > r) continue;

			visit[nextY][nextX] = true;
			q.push({ nextY, nextX });
			teamQ.push({ nextY, nextX });
			cntTeam++;
		}
	}
	// 현재 칸에서 연합맺을 인접 국가가 없으면 return
	if (teamQ.size() == 1) return;

	// 연합 맺은 나라간 인구 재조정
	int population = sum / cnt;
	while (!teamQ.empty()) {
		int currY = teamQ.front().first;
		int currX = teamQ.front().second;
		teamQ.pop();

		arr[currY][currX] = population;
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> l >> r;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}

	int days = 0;
	do {
		cntTeam = 0;
		fill(&visit[0][0], &visit[n - 1][n], false);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j]) continue;
				bfs(i, j);
			}
		}
		/*
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cout << arr[i][j] << " ";
			}
			cout << "\n";
		}
		cout << "\n";*/

		if (cntTeam != 0) days++;
	} while (cntTeam > 0);

	cout << days << "\n";

	return 0;
}
