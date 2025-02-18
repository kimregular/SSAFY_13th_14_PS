#include<iostream>
#include<queue>
using namespace std;

int n;
int arr[4][4];
int dy[2] = { 1, 0 }, dx[2] = { 0, 1 };
bool visit[4][4];
/*
처음에 방향이 아래오른쪽 밖에 없어서 루프가 발생하지 않을 거라 생각했습니다.
visit배열을 쓰지 않았는데, 제출하니 메모리 초과가 발생했습니다.
그래서 visit 코드만 추가하고 재제출 하더니 맞았어요.
도대체 이해가 가지 않아서 지피티한테 물어봤는데
문제에 칸에 적혀있는 거리가 '0'~100 범위 내의 수라고 해서, 거리가 0인경우
무한루프가 돌고(계속해서 큐에 같은 칸을 쌓음) 메모리초과가 난다고 하네요.

조건이 만약 1~100이었다면 visit배열을 쓰지 않아도 된다고합니다!
*/
bool bfs() {
	queue<pair<int, int>> q;
	q.push({ 0, 0 });
	visit[0][0] = true;

	while (!q.empty()) {
		int currY = q.front().first;
		int currX = q.front().second;
		int dist = arr[currY][currX];
		q.pop();

		if (currY == n - 1 && currX == n - 1) return true;

		for (int i = 0; i < 2; i++) {
			int nextY = currY + dy[i] * dist;
			int nextX = currX + dx[i] * dist;

			if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) continue;
			if (visit[nextY][nextX]) continue;

			q.push({ nextY, nextX });
			visit[nextY][nextX] = true;
		}
	}
	return false;
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}

	if (bfs()) cout << "HaruHaru\n";
	else cout << "Hing\n";
}