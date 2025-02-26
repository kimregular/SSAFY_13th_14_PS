package src.w250217.정이.p16235;

import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, K;
    static int[][] food;
    static int[][] add_food;
    static PriorityQueue<Tree> list = new PriorityQueue<>();
    static ArrayList<Tree> dead_list = new ArrayList<>();

    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        //양분 
        //양분로봇
        food = new int[N][N];        
        add_food = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                food[i][j] = 5;
                add_food[i][j] = Integer.parseInt(st.nextToken());
            }
            
        }
        
        //나무 리스트
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            list.add(new Tree(x, y, age));
        }
        
        //계절 시작
        for(int i=0; i<K; i++) {
            spring();
            summer();
            fall();
            winter();
        }
        
        //살아남은 나무 갯수
        System.out.println(list.size());
        br.close();
    }
    
    public static void spring() {
        ArrayList<Tree> liveList = new ArrayList<>();
        int size = list.size()-1;
        for(int i=size; i>=0; i--) {
            Tree tree = list.poll();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            
            // 땅이 가진 양분이 나무 나이 보다 적으면 해당 나무 deadList 등록 후 삭제
            if(food[x][y] < age) {
                //removeList.add(tree);
                dead_list.add(tree);
                continue;
            }
            // 살아남은 나무는 추가
            liveList.add(tree);
            // 양분 먹은 만큼 줄어듬
            food[x][y] -= age;
            // 나무 나이 증가
            tree.age++;
        }
        list.clear();
        list.addAll(liveList);
    }
    
    public static void summer() {
        for(Tree deadTree : dead_list) {
            int x = deadTree.x;
            int y = deadTree.y;
            int dead_food = deadTree.age / 2;
            
            //죽은 자리에 양분 추가
            food[x][y] += dead_food;
        }
        dead_list.clear();
    }
    
    public static void fall() {     
        ArrayList<Tree> addList = new ArrayList<>();
        for(Tree tree : list) {
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if(age % 5 != 0) continue;
            
            for(int k=0; k<8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx<0 || nx>N-1 || ny<0 || ny>N-1) continue;
                
                // 8방향에 나이가 1인 나무 추가
                addList.add(new Tree(nx, ny, 1));
            }
        }
        // 번식한 나무들 추가
        list.addAll(addList);
    }
    
    public static void winter() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                // 땅에 양분 추가
                food[i][j] += add_food[i][j];
            }
        }
    }
}

class Tree implements Comparable<Tree>{
    int x;
    int y;
    int age;
    
    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
         return Integer.compare(age, o.age);
    }
}


