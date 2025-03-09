package w250224.준범.p2023;

import java.util.*;
import java.io.*;


class Main {
    static int n;
    static boolean[] isPrime;


    static boolean func(int x) {
        for(int i=1; i <= Math.pow(10,n-1) ;i *= 10) {
            int val = x / i;
            if(!isPrime[val]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int k = (int) Math.pow(10, n);
        isPrime = new boolean[k];

        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for(int i=2;i*i<k;i++) {
            if(isPrime[i]) {
                for(int j=i*i;j<k;j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        for(int i=(int)Math.pow(10,  n-1)+1; i<k;i++) {
            if(func(i)) System.out.println(i);
        }



    }

}
