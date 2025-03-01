#include<iostream>
#include<algorithm>
#include<queue>
#include<vector>
#include<map>
#include<unordered_set>
#include<unordered_map>
using namespace std;
typedef pair<int, int> pii;

int n, m;
int arr[51][51], dist[14][51][51];
bool visit[51][51];
vector<pii> chickens, houses;
vector<int> perm;
int dy[4] = { 0, 0, -1, 1 }, dx[4] = { -1, 1, 0, 0 };


// y, x 치킨집으로부터 모든 집까지의 거리를 기록
// dist[idx][y][x] = a : idx번째 치킨집에서 y, x 좌표까지의 거리
void bfsFromChicken(int idx, int y, int x) {
	fill(&visit[0][0], &visit[n - 1][n], false);
	queue<pii> q;
	q.push({ y, x });
	dist[idx][y][x] = 0;
	int houseCnt = 0;

	while (!q.empty()) {
		int currY = q.front().first;
		int currX = q.front().second;
		q.pop();

		if (arr[currY][currX] == 1) houseCnt++;
		for (int i = 0; i < 4; i++) {
			int nextY = currY + dy[i];
			int nextX = currX + dx[i];

			if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) continue;
			if (visit[nextY][nextX]) continue;

			q.push({ nextY, nextX });
			dist[idx][nextY][nextX] = dist[idx][currY][currX] + 1;
			visit[nextY][nextX] = true;

			if (arr[nextY][nextX] == 1) {
				houseCnt++;
				//cout << "치킨집 " << idx << " 에서 " << nextY << ", " << nextX << " 거리 : " << dist[idx][nextY][nextX] << "\n";
			}
			//if (houseCnt == houses.size()) return;
		}
	}
}

int main() {
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 1) houses.push_back({ i, j });
			else if (arr[i][j] == 2) chickens.push_back({ i, j });
		}
	}

	// 치킨집 개수만큼 조합 할 1, 0 저장
	// 1 : m개, 0: 치킨집 총 수 - m개
	for (int i = 0; i < chickens.size() - m; i++) perm.push_back(0);
	for (int i = 0; i < m; i++) perm.push_back(1);

	// 모든 치킨집별로 집들까지의 거리 계산
	for (int i = 0; i < chickens.size(); i++) bfsFromChicken(i, chickens[i].first, chickens[i].second);

	// 치킨집 조합
	int minCityDist = 3000;
	do {
		// 0 1 1 0
		//cout << "============================================================\n이번 조합 : ";
		//for (int j = 0; j < perm.size(); j++) {
		//	cout << perm[j] << " ";
		//}
		//cout << "\n";

		// 집별로, 이번 조합에서 선택된 치킨집들 중 최단 거리, 즉 집별 치킨 거리를 currDist에 저장
		// cityDist : 도시 치킨거리
		// minCityDist : 조합별 도시 치킨거리 중 최소값
		int cityDist = 0;
		for (int i = 0; i < houses.size(); i++) {
			int houseY = houses[i].first;
			int houseX = houses[i].second;
			int currDist = 3000;
		//	cout << "집 위치 : " << houseY << ", " << houseX << "\n";
			for (int j = 0; j < perm.size(); j++) {
				if (perm[j] == 0) continue;
				currDist = min(currDist, dist[j][houseY][houseX]);
			}
			cityDist += currDist;
			
		//	cout << "현재 집의 치킨 거리 : " << currDist << "\n현재까지 도시의 치킨 거리 : " << cityDist << "\n";
		}
		minCityDist = min(minCityDist, cityDist);
	} while (next_permutation(perm.begin(), perm.end()));


	int ans = 0;
	cout << minCityDist << "\n";

	return 0;
}

/*
반례
5 1
2 0 1 0 2
0 0 0 0 0
1 0 2 0 1
0 0 0 0 0
2 0 1 0 2
*/
