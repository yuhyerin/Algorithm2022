package com.algo.jungol.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JO_1141_두줄로타일깔기 {
	static int[] memo = new int[1000001];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// N : 1~100,000 (세로의 칸수)
		int num = 20100529;
		// 2종류의 타일 존재함: 2x2, 1x2 
		memo[1] = 1;
		memo[2] = 3;
		for(int i=3; i<=N; i++) {
			memo[i] = ((memo[i-1]+(memo[i-2]*2)))%num;
		}
		System.out.println(memo[N]);
	}
}
