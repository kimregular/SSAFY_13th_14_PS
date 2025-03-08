package w250217.준범.p14921_용액합성하기;

import java.util.*;
import java.io.*;


class Main {
    static int n;
    static int data[];

    static int bns(int target, int low, int high) {
        int closest = data[low];
        while(low <= high) {
            int mid = (low + high)/2;

            if(Math.abs(data[mid] - target) < Math.abs(closest - target)) {
                closest = data[mid];
            }

            if(data[mid] < target) {
                low = mid+1;
            }else {
                high = mid-1;
            }


        }


        return closest;
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

        int minDiff = Integer.MAX_VALUE;
        int ans = 0;
        for(int i=0;i<n-1;i++) {
            int cur = data[i];
            int found = bns(-1*cur, i+1, n-1);

            if(Math.abs(cur + found) < minDiff ) {
                minDiff = Math.abs(cur + found);
                ans = cur + found;
            }

        }

        System.out.println(ans);

    }
}