#include <iostream>
#include <vector>
using namespace std;

int n, m;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n >> m;
    vector<int> numbers(n), sum(n);

    for(int i = 0; i< n; i++){
        cin >> numbers[i];

        // 첫 번째 숫자는 sum 배열에 그대로 입력
        if(i == 0){
            sum[i] = numbers[i];
            continue;
        }

        // 누적합 저장
        sum[i] = numbers[i] + sum[i-1];
    }

    for(int i = 0; i< m; i++){
        int a, b;
        cin >> a >> b;

        // 1번 index부터의 합이 질문일 경우 누적합인 sum[b]값 그대로 출력
        if(a == 1){
            cout << sum[b-1] << "\n";
            continue;
        }

        // numbers[a] + .. + numbers[b] = sum[b] - sum[a-1]
        cout << sum[b-1] - sum[a-2] << "\n";
    }

    return 0;
}
