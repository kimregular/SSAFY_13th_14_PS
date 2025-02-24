#include<iostream>
#include<algorithm>
using namespace std;

int n, m;
int arr[9], nums[9];

void solve(int k) {
	if (k == m) {
		for (int i = 0; i < m; i++) cout << arr[i] << " ";
		cout << "\n";
		return;
	}

	int prev = -1;
	for (int i = 0; i < n; i++) {
		if (prev == nums[i] || (k != 0 && nums[i] < arr[k - 1])) continue;
		arr[k] = nums[i];
		solve(k + 1);
		prev = nums[i];
	}
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) cin >> nums[i];
	sort(nums, nums + n);

	solve(0);
}