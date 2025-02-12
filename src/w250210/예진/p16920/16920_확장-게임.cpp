/*
	백준 16920 확장 게임
	링크 : https://www.acmicpc.net/problem/16920
*/

#include<iostream>
#include<vector>
#include<queue>
#include<string>
using namespace std;

// playerCnt : 플래이어 수, totalCnt : 종료 조건을 위한 격자 위 요소들의 수
int row, col, playerCnt, isEnd;
int arr[1001][1001];
int cnt[1001], dist[1001];
int dy[4] = { 0, 0, -1, 1 }, dx[4] = { -1, 1, 0, 0 };

// 일반 bfs에서의 visit이 아니라, 상하좌우 탐색이 끝난 칸을 true로 함.
// BFS for문에서 dist[player]번째 칸, 즉 사방을 탐색하지 않은 끝 칸은 visit false로 유지
// 그 대신 arr[y][x] != 0 으로 방문 여부 판별
bool visit[1001][1001]; 

void bfs(int player) {
	queue<pair<pair<int, int>, int>> q; // y, x, 이동횟수

	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			if (arr[i][j] != player || visit[i][j]) continue;
			q.push({ { i, j }, 0 });
		}
	}

	// cntBfs : player의 확장 여부를 확인하는 변수
	int cntBfs = 0;
	while (!q.empty()) {
		int currY = q.front().first.first;
		int currX = q.front().first.second;
		int moveCnt = q.front().second;
		q.pop();
		visit[currY][currX] = true; // 확실히 사방을 다 탐색한(하게 될) 애만 visit true

		for (int i = 0; i < 4; i++) {
			int nextY = currY + dy[i];
			int nextX = currX + dx[i];

			if (nextY < 0 || nextX < 0 || nextY >= row || nextX >= col) continue;
			// 1. 빈칸이 아닌경우(벽, 다른 player가 이미 차지한 경우, 즉 보통bfs에서 visit)의 역할 pass
			// 2. visit : 이미 상하좌우 탐색이 끝난 칸은 재탐색하지 않음
			if (arr[nextY][nextX] != 0 || visit[nextY][nextX]) continue;

			cnt[player]++;
			arr[nextY][nextX] = player;
			cntBfs++;

			if (moveCnt + 1 >= dist[player]) continue;
			q.push({ {nextY, nextX}, moveCnt + 1 });
			//visit[nextY][nextX] = true; 이코드 주석처리 안해도 맞긴함
		}
	}
	if (cntBfs == 0) isEnd++;
}


int main() {
	ios::sync_with_stdio(0);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> row >> col >> playerCnt;

	for (int i = 1; i <= playerCnt; i++) cin >> dist[i];

	for (int i = 0; i < row; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < col; j++) {
			int c = str[j];
			if (c == '.') continue;
			if (c == '#') {
				arr[i][j] = -1;
				continue;
			}
			arr[i][j] = c - '0';
			cnt[arr[i][j]]++;
		}
	}

	/*
		종료조건 : 모든 player가 더이상 확장할 수 없을 때.
		따라서 player 한명이 확장 하지 못할때마다
		isEnd 를 +1 시켜준 뒤 player수 와 같아지면 종료.

		#, player1, ... , players 가 차지한 공간을 row*col 과 비교하여 종료하면 안됨.
		. . . . 1
		. # # # .
		. # . # .
		. # . # .
		. # # # .
		2 . . . .
		위와같이 빈 공간이더라도 도달할 수 없는 hidden test case가 존재함!
	*/
	while (isEnd != playerCnt) {
		isEnd = 0;
		for (int i = 1; i <= playerCnt; i++) {
			bfs(i);
			if (isEnd == playerCnt) break;
		}
	}


	for (int i = 1; i <= playerCnt; i++) cout << cnt[i] << " ";
	cout << "\n";

	return 0;

}