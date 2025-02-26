package w250217.준범.p3151_합이0;

import java.util.*;
import java.io.*;


class Main {
    static int n;
    static int data[];


    static int lowerBound(int cur, int low, int high) {
        while(low < high) {
            int mid = low + (high - low)/2;
            if(data[mid] + cur < 0) {
                low = mid + 1;
            }else {
                high = mid;
            }
        }
        return low;
    }

    static int upperBound(int cur, int low, int high) {
        while(low < high) {
            int mid = low + (high - low)/2;
            if( data[mid] + cur<=0) {
                low = mid + 1;
            }else {
                high = mid;
            }
        }
        return low;
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        data = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);

        long cnt = 0;

        for(int i=0;i<n-2;i++) {
            for(int j=i+1;j<n-1;j++) {
                int cur = data[i] + data[j];

                int lower = lowerBound(cur, j + 1, n);
                int upper = upperBound(cur, j + 1, n);

                cnt += (long)(upper - lower);


            }
        }

        System.out.println(cnt);

    }
}