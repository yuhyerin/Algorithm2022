package com.algo.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class L2_카카오프렌즈_컬러링북 {
	public static void main(String[] args) {
		L2_카카오프렌즈_컬러링북 t = new L2_카카오프렌즈_컬러링북();
		int m = 6; // 1~100 (세로)  
		int n = 4; // 1~100 (가로)  
		int[][] picture = {
				{1, 1, 1, 0},
				{1, 2, 2, 0},
				{1, 0, 0, 1},
				{0, 0, 0, 1},
				{0, 0, 0, 3},
				{0, 0, 0, 3}};
		
		int[] result = t.solution(m, n, picture);
		for(int r : result) {
			System.out.print(r+" ");
		}
		System.out.println();
	}
	// 0 : 칠하지 않은 영역.
	public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0; // 총 몇개의 영역으로 되어있는지.
        int maxSizeOfOneArea = 0; // 칸수 
        
        boolean[][] visit = new boolean[m][n];
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		if(picture[i][j]==0) continue;
        		if(!visit[i][j]) {
        			numberOfArea++; // 영역갯수 세기
        			int result = bfs(i,j,picture,visit);
        			if(maxSizeOfOneArea < result) {
        				maxSizeOfOneArea = result;
        			}
        		}
        	}
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea; 
        answer[1] = maxSizeOfOneArea; 
        return answer;
    }
	
	private int bfs(int y, int x, int[][] picture, boolean[][] visit) {
		
		int[] dy = {-1,1,0,0}; // 상 하 좌 우 
		int[] dx = {0,0,-1,1};
		
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {y,x});
		int value = picture[y][x];
		int cnt = 0;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int cy = cur[0];
			int cx = cur[1];
			
			if(!visit[cy][cx]) {
				visit[cy][cx]= true;
				cnt++;
				
				for(int d=0; d<4; d++) {
					int ny = cy+dy[d];
					int nx = cx+dx[d];
					if(!canGo(ny,nx, picture.length, picture[0].length)) continue;
					if(picture[ny][nx]==0) continue;
					if(picture[ny][nx]==value) {
						que.add(new int[] {ny, nx});
					}
				}// end for
			}
		}// end while
		
		return cnt;
	}
	
	private boolean canGo(int y, int x, int m, int n) {
		if(0<=y && y<m && 0<=x && x<n) return true;
		return false;
	}
}
