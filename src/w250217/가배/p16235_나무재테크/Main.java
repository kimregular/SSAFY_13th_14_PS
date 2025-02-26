package w250217.가배.p16235_나무재테크;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A, nut; 
    static PriorityQueue<Tree> trees = new PriorityQueue<>();
    static ArrayList<Tree> alive = new ArrayList<>();
    static ArrayList<Tree> dead = new ArrayList<>();
    
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    static class Tree implements Comparable<Tree> {
        int x, y, age;
        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        nut = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nut[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        while (K-->0) {
            simulate();
        }

        System.out.println(trees.size());
    }

    static void simulate() {
        alive.clear();
        dead.clear();

        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            if (nut[tree.x][tree.y] >= tree.age) {
                nut[tree.x][tree.y] -= tree.age;
                tree.age++;
                alive.add(tree);
            } else {
                dead.add(tree);
            }
        }

        for (Tree tree : dead) {
            nut[tree.x][tree.y] += tree.age / 2;
        }

        for (Tree tree : alive) {
            if (tree.age % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int nx = tree.x + dx[d];
                    int ny = tree.y + dy[d];
                    if (0<=nx && nx<N && 0<=ny && ny<N) {
                        trees.add(new Tree(nx, ny, 1));
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nut[i][j] += A[i][j];
            }
        }

        trees.addAll(alive);
    }
}