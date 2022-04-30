package com.algo.bj.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_23291_어항정리 {
    private static int N; // 어항수
    private static int K; // 물고기수 차이
    private static List<Integer>[] fishbowl; // 어항
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader((new InputStreamReader((System.in))));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        fishbowl = new ArrayList[N];
        for(int i=0; i<N; i++){
            fishbowl[i] = new ArrayList<>();
        }
        for(int i=0; i<N; i++){
            fishbowl[0].add(Integer.parseInt(st.nextToken()));
        }// end input

        int count = 0;
        do {
            // 1. 물고기 수 제일적은곳에 한마리 추가하기
            addOneFish();
            // 2. 공중부양 (어항쌓기)
            upFishbowl();
            // 3. 물고기 수를 조절
            balancingFish();
            // 4. 다시 일렬로 어항놓기.
            lineUpFishbowl();
            // 5. 다시공중부양
            reUpFishbowl();
            // 6. 물고기수조절 & 일렬로 놓기 반복.
            balancingFish();
            lineUpFishbowl();
            count++;
        } while(isFinished());
        System.out.println(count);
    }

    private static void lineUpFishbowl() {
//        // 가장 왼쪽에 있는 어항부터... 가장아래에있는어항부터
        List<Integer> tmp = new ArrayList<>();
        int row = 0;
        for(int col = 0; col < fishbowl[row].size(); col++){
            while(row < N && col < fishbowl[row].size()){
                tmp.add(fishbowl[row].get(col));
                row++;
            }
            row = 0;
        }
        for(int i = 0; i < N; i++){
            fishbowl[i].clear();
        }
        fishbowl[0].addAll(tmp);
    }

    private static void reUpFishbowl() {
        List<Integer> left = new ArrayList<>();
        for(int i=0; i<N/2; i++){
            left.add(0,fishbowl[0].remove(0));
        }// end for

        fishbowl[1].addAll(left);

        List<Integer> left1 = new ArrayList<>();
        List<Integer> left2 = new ArrayList<>();
        for(int i=0; i<N/4; i++){
            left1.add(0,fishbowl[1].remove(0));
        }// end for
        for(int i=0; i<N/4; i++){
            left2.add(0,fishbowl[0].remove(0));
        }// end for
        fishbowl[2].addAll(left1);
        fishbowl[3].addAll(left2);
    }

    private static void balancingFish() {
        int[][] calc = new int[N][N];
        int[] dy = {0,-1}; // 우 하
        int[] dx = {1,0};

        for(int y=fishbowl.length - 1; y >= 0; y--){
            int len = fishbowl[y].size();
            for(int x=0;x<len;x++){
                for(int dir=0; dir<2; dir++){
                    int ny = y+dy[dir];
                    int nx = x+dx[dir];
                    if(0<=ny && nx <len){
                        int d = Math.abs(fishbowl[y].get(x)-fishbowl[ny].get(nx))/5;
                        if(d>0){
                            if(fishbowl[y].get(x)<fishbowl[ny].get(nx)){
                                calc[ny][nx]-=d;
                                calc[y][x]+=d;
                            }else{
                                calc[ny][nx]+=d;
                                calc[y][x]-=d;
                            }
                        }
                    }
                }
            }
        }// end for

        for(int y=fishbowl.length - 1; y >= 0; y--) {
            int len = fishbowl[y].size();
            for (int x = 0; x < len; x++) {
                calc[y][x] += fishbowl[y].get(x);
            }
            fishbowl[y] = new ArrayList<>();
            for (int x = 0; x < len; x++) {
                 fishbowl[y].add(calc[y][x]);
            }
        }
    }

    private static void upFishbowl() {
        int currentHeight = 1;
        int currentWidth = 1;

        while(true) {
            // 1. 임시 리스트 생성
            ArrayList<Integer> tmp = new ArrayList<>();

            // 2. 임시 리스트 담기
            for (int count = 0; count < currentWidth; count++) {
                for (int row = 0; row < currentHeight; row++) {
                    int num = fishbowl[row].remove(0);
                    tmp.add(num);
                }
            }

            // 3. 임시 리스트에서 원본 리스트로 복제
            int rowIdx = currentWidth;
            while (!tmp.isEmpty()) {
                for (int count = 0; count < currentHeight; count++) {
                    int num = tmp.remove(0);
                    fishbowl[rowIdx].add(num);
                }
                rowIdx--;
            }

            // 3. height, width 계산
            int nextHeight = 0;
            int nextWidth = 0;
            while (!fishbowl[nextHeight].isEmpty()) {
                nextHeight++;
            }
            nextWidth = fishbowl[nextHeight - 1].size();
            currentHeight = nextHeight;
            currentWidth = nextWidth;

            // 4. 진행여부 판단
            if (currentHeight > fishbowl[0].size() - currentWidth) {
                break;
            }
        }
    }

    private static void addOneFish() {
        int min = 10000;
        ArrayList<Integer> minList = new ArrayList<>();
        for(int i=0; i<N; i++){
            if( min > fishbowl[0].get(i)){
                minList.clear();
                minList.add(i);
                min = fishbowl[0].get(i);
            }else if(min == fishbowl[0].get(i)){
                minList.add(i);
            }
        }// end for

        for(int idx : minList){
            int val = fishbowl[0].get(idx);
            fishbowl[0].remove(idx);
            fishbowl[0].add(idx, val + 1);
        }

    }

    private static boolean isFinished() {
        int min = 10000;
        int max = -1;
        for(int i=0; i<N; i++){
            if( min > fishbowl[0].get(i)){
                min = fishbowl[0].get(i);
            }
            if(max < fishbowl[0].get(i)){
                max = fishbowl[0].get(i);
            }
        }// end for
        return K < max - min;
    }
}