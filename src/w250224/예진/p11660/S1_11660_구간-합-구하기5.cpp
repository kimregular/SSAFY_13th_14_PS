#include <iostream>
using namespace std;

int n, m;
int numbers[1025][1025], sum[1025][1025];

int main(){
    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n >> m;
    for(int i = 1; i<= n;i++){
        for(int j = 1; j<= n;j++){
            cin >> numbers[i][j];

//            // 첫째 칸 -> 어차피 1, 1부터 시작이기때문에 따로 처리해주지 않아도 됨
//            if(i == 1 && j == 1){
//                sum[1][1] = numbers[1][1];
//                continue;
//            }
//
//            // 가로 첫줄
//            if(i == 1){
//                sum[1][j] = sum[1][j-1] + numbers[1][j];
//                continue;
//            }
//
//            // 세로 첫줄
//            if(j == 1){
//                sum[i][1] = sum[i-1][1] + numbers[i][1];
//                continue;
//            }

            sum[i][j] = numbers[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
        }
    }

    for(int i = 0; i< m; i++){
        int fromY, fromX, toY, toX;
        cin  >> fromY >> fromX >> toY >> toX;
        
//      어차피 1, 1부터 시작이기때문에 따로 처리해주지 않아도 됨
//        if(fromY == 1 && fromX == 1){
//            cout << sum[toY][toX] << "\n";
//            continue;
//        }
//
//        if(fromY == 1){
//            cout << sum[toY][toX] - sum[toY][fromX - 1] << "\n";
//            continue;
//        }
//
//        if(fromX == 1){
//            cout << sum[toY][toX] - sum[fromY-1][toX] << "\n";
//            continue;
//        }

        cout << sum[toY][toX] - sum[toY][fromX - 1] - sum[fromY - 1][toX] + sum[fromY -1][fromX-1] << "\n";
    }

    return 0;
}
