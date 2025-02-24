#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int arr[1000001];
int m, n, maxLength, ans;

void binarySearch() {
	int start = 1, end = maxLength;
	int mid = 0, cnt = 0;
	while (start <= end) {
		cnt = 0;
		mid = (start + end) / 2;

		for (int i = 0; i < n; i++) cnt += arr[i] / mid;

		if (cnt < m) {
			end = mid - 1;
			continue;
		}

		ans = mid;
		start = mid + 1;

	}
	return;
}

int main() {
	cin >> m >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		maxLength = max(maxLength, arr[i]);
	}

	binarySearch();
	cout << ans << "\n";
}