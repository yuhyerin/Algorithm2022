package com.algo.bj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_9663_NQueen {
	/**
	 * 퀸은 가로,세로,대각선으로 이동이 가능하다.
	 * 퀸이 0번째 행에 있다면, 다른퀸은 0번행에 놓을 수 없다.
	 * --> 즉, 한 행에 하나의 퀸만 놓을 수 있다. */
	static int N;
	static int[] row;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		row = new int[N];
		count = 0;
		Arrays.fill(row, -1);
		pick(0); // 1행에 퀸을 놓아라..
		
		System.out.println(count);
	}

	private static void pick(int y) {
		
		if(y==N) { // 끝.
			count++;
			return;
		}
		
		for (int x = 0; x < N; x++) {
			
			boolean isLocate = true;
			for(int r=0; r<y; r++) {
				if (row[r] == x || isCross(r,row[r], y, x)) {
					isLocate = false;
					break;
				}
			}
			
			if(isLocate) {
				// 놓기. 
				row[y] = x; // (y,x)에 퀸을 놓음.
				pick(y+1);
			}
		}
	
	}
	
	// (nr,nc)가 (r,c)의 대각선상에 있는지 여부를 리턴하는 함수 
	private static boolean isCross(int r, int c, int nr, int nc) {
		return Math.abs(nr-r)==Math.abs(nc-c);
	}
}
