#include<iostream>
#include<algorithm>
using namespace std;

int n;
pair<int, int> arr[1000001];
int ans[1000001];

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		arr[i] = { a, i };
	}

	sort(arr, arr + n);

	ans[arr[0].second] = 0;

	int prev = arr[0].first, num = 0;
	for (int i = 1; i < n; i++) {
		if (arr[i].first == prev) {
			ans[arr[i].second] = num;
			continue;
		}
		prev = arr[i].first;
		ans[arr[i].second] = ++num;
	}

	for (int i = 0; i < n; i++) cout << ans[i] << " ";
	cout << "\n";
}