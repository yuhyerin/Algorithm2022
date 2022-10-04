package com.algo.jungol.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JO_3522_튜토리얼DP {
	
	static long[] fibo;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num = 1000000007;
		fibo = new long[N+1];
		fibo[1]=1;
		fibo[2]=1;
		for(int i=3; i<=N; i++) {
			fibo[i]=(fibo[i-2]+fibo[i-1])%num;
		}
		System.out.println(fibo[N]);
	}
	
}
