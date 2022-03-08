package com.algo.programmers;

public class L2_행렬테두리회전하기 {
	public static void main(String[] args) {
		L2_행렬테두리회전하기 t = new L2_행렬테두리회전하기();
		int rows = 3;
		int columns = 3;
//		int[][] queries = {
//				{2,2,5,4},
//				{3,3,6,6},
//				{5,1,6,3}};
		int[][] queries = {
				{1,1,2,2},
				{1,2,2,3},
				{2,1,3,2},
				{2,2,3,3}
		};
		int[] result = t.solution(rows, columns, queries);
		for(int r: result) {
			System.out.print(r+" ");
		}
		System.out.println();
	}
	
	public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows+1][columns+1];
        int num=1;
        for(int i=1; i<=rows; i++) {
        	for(int j=1;j<=columns;j++) {
        		map[i][j]=num++;
        	}
        }
        
        for(int i=0; i<queries.length; i++) {
        	int sy = queries[i][0];
        	int sx = queries[i][1];
        	int ey = queries[i][2];
        	int ex = queries[i][3];
        	int min = rotate(map, sy,sx,ey,ex);
        	answer[i]=min;
        }
        return answer;
    }

	private int rotate(int[][] map, int sy, int sx, int ey, int ex) {
		int min = Integer.MAX_VALUE;
		
		int top_start = map[sy][sx];
		// 1. 상 ^
		for(int y=sy+1; y<=ey; y++) {
			if(map[y][sx]<min) min = map[y][sx];
			map[y-1][sx] = map[y][sx];
		}
		// 2. 좌 <-- 
		for(int x=sx+1; x<=ex; x++) {
			if(map[ey][x]<min) min = map[ey][x];
			map[ey][x-1] = map[ey][x];
		}
		//3. 하 V
		for(int y=ey-1; y>=sy; y--) {
			if(map[y][ex]<min) min = map[y][ex];
			map[y+1][ex]=map[y][ex];
		}
		//4. 우 -->
		for(int x=ex-1; x>sx; x--) {
			if(map[sy][x]<min) min = map[sy][x];
			map[sy][x+1] = map[sy][x];
		}
		if(top_start<min) min = top_start;
		map[sy][sx+1] = top_start;
		
		return min;
		
	}
	
	private void printMap(int[][] map) {
		System.out.println("========================");
		for(int i=1; i<map.length; i++) {
			for(int j=1; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("========================");
	}
}
