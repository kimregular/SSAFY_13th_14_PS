#include<iostream>
#include<queue>
#include<vector>
#include<deque>
using namespace std;

int n, m, litCnt;
bool isEnd;
bool isLit[101][101], bfsVisit[101][101];
int reachable[101][101]; // 0 : (1, 1)로부터 못감 or 시도 안해좀, 1 : (1, 1)로부터 해당 칸 까지 무조건 도달 가능
typedef pair<int, int> pii;
int dy[4] = { 0, 0, -1, 1 }, dx[4] = { -1, 1, 0, 0 };
//vector<pair<pii, pii>> v;
deque<pair<pii, pii>> switches;

/* (1, 1) -> 각 지점까지 갈 수 있는지 하나하나 확인하던 함수
void isReachable() {
	fill(&bfsVisit[0][0], &bfsVisit[n - 1][n], false);
	queue<pii> q;
	q.push({ 1, 1 });
	bfsVisit[1][1] = true;

	while (!q.empty()) {
		int currY = q.front().first;
		int currX = q.front().second;
		q.pop();

		//if (currY == y && currX == x) return true;

		for (int i = 0; i < 4; i++) {
			int nextY = currY + dy[i];
			int nextX = currX + dx[i];

			if (nextY < 1 || nextX < 1 || nextY > n || nextX > n) continue;
			if (!isLit[nextY][nextX] || bfsVisit[nextY][nextX]) continue;

			if (reachable[nextY][nextX]) {
				q.push({ nextY, nextX });
				bfsVisit[nextY][nextX] = true;
				continue;
			}

			bfsVisit[nextY][nextX] = true;
			reachable[nextY][nextX] = true;
			q.push({ nextY, nextX });
		}
	}
	return;
}
*/

// (y, x) -> (1, 1) 을 갈 수 있는지 확인하는 함수
bool isReachable2(int y, int x) {
	fill(&bfsVisit[0][0], &bfsVisit[n - 1][n], false);
	queue<pii> q;
	q.push({ y, x });
	bfsVisit[y][x] = true;

	// 현재 칸에 불이 안들어와있다면 절대 도착할 수 없음
	if (!isLit[y][x]) return false;

	while (!q.empty()) {
		int currY = q.front().first;
		int currX = q.front().second;
		q.pop();

		// 1, 1로 가는 경로에 있는 칸이 1, 1까지 갈 수 있다면 y, x도 갈 수 있음
		if (reachable[currY][currX] || currY == 1 && currX == 1) {
			reachable[y][x] = true;
			return true;
		}

		for (int i = 0; i < 4; i++) {
			int nextY = currY + dy[i];
			int nextX = currX + dx[i];


			if (nextY < 1 || nextX < 1 || nextY > n || nextX > n) continue;
			if (!isLit[nextY][nextX] || bfsVisit[nextY][nextX]) continue;

			bfsVisit[nextY][nextX] = true;
			q.push({ nextY, nextX });

			//reachable[nextY][nextX] = true;
			// 이걸 하면 안됨. 해당 함수는 y,x 에서부터 시작해서 1, 1에 갈 수 있는지 확인하는 코드이므로 
			// 여기서 next 좌표로 갈 수 있도라도 고립된 곳이라 1, 1까지 못 갈수도 있음. 
			// 따라서 1, 1에 도착하면 (y, x)만 reachable true로 바꿔줌
		}
	}
	return false;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(nullptr);
	cout.tie(nullptr);


	cin >> n >> m;
	isLit[1][1] = true;
	reachable[1][1] = true;
	litCnt = 1;

	for (int i = 0; i < m; i++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;
		switches.push_back({ {a, b}, {c, d} });
	}

	// 중단 조건 확인 용 변수
	int idx = 0; 
	int startSize = switches.size();
	while (!switches.empty()) {
		int fromY = switches.front().first.first;
		int fromX = switches.front().first.second;
		int toY = switches.front().second.first;
		int toX = switches.front().second.second;

		// idx기준 한바퀴를 돌았을 때
		if (idx == startSize || switches.size() == 0) {
			// 한바퀴를 돌았음에도 사이즈가 같으면 무한루프 상태 -> break
			if (switches.size() == startSize) break;

			// 사이즈가 달라진것은 아직 방문 중
			startSize = switches.size();
			idx = 0;
		}

		idx++;
		switches.pop_front();

		// 이미 불이 켜져있는 칸이라면 pass
		if (isLit[toY][toX]) continue;

		// to에 불이 켜지지 않은 상태만 남음

		// to의 불을 켜기 위해 1, 1에서 from으로 갈 수 있는지를 확인
		bool isPossible = reachable[fromY][fromX]; // 만약 배열에 값이 없다면
		if (!isPossible) isPossible = isReachable2(fromY, fromX); // 함수 돌아 확인하기

		// 여전히 from이 방문할 수 없는 곳이라면 -> 뒤에 다시 넣어주기(언제 다시 될지 모름)
		if (!isPossible) {
			switches.push_back({ {fromY, fromX}, {toY, toX} });
			continue;
		}

		// 1, 1 -> fromY, fromX 갈 수 있는경우
		isLit[toY][toX] = true;
		if(!(toY == 1 && toX == 1)) litCnt++;
	}

	cout << litCnt << "\n";
}