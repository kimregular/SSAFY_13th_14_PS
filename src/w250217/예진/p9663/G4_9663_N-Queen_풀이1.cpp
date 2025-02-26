#include<iostream>
#include<math.h>
#include<deque>
using namespace std;

int n, cnt;
int arr[16];
bool visit[16];
deque<pair<int, int>> dq;

void queen(int idx) {
	if (idx == n) {
		//for (int i = 0; i < n; i++) cout << arr[i] << " ";
		//cout << "\n";
		cnt++;
		return;
	}
	for (int i = 1; i <= n; i++) {
		if (visit[i]
			|| (idx != 0 && abs(arr[idx-1] - i) == 1)
			) continue;

		// 기울기 확인
		bool isAvail = true;
		for (int j = 0; j < dq.size(); j++) {
			if(abs(dq[j].first - (idx + 1)) != abs(dq[j].second - (i))) continue;
			isAvail = false;
			break;
		}
		if (!isAvail) continue;

		dq.push_back({ idx + 1, i });
		visit[i] = true;
		arr[idx] = i;
		queen(idx + 1);
		visit[i] = false;
		dq.pop_back();
	}
}

int main() {
	cin >> n;
	queen(0);
	cout << cnt << "\n";
}