package com.algo.jungol.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JO_1848_극장좌석 {
	/* 
	 * 좌석번호 : 1~N 
	 * 바로왼쪽/오른쪽으로 좌석을 옮길 수 있음. 
	 * 고정석 회원 존재.
	 * 고정석 회원 번호들이 주어졌을 때, 사람들이 좌석에 앉는 서로다른 방법의 가짓수. 
	 * **/
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 좌석갯수 : 1~40 
		int M = Integer.parseInt(br.readLine()); // 고정석갯수 : 0~N
		/* 고정석 사이의 칸 수에 따라 나올수 있는 경우의 수
		 * 1칸: 1
		 * 2칸: 2 
		 * 3칸: 3
		 * 4칸: 5
		 * 5칸: 8
		 * **/
		int[] memo = new int[41];
		memo[0] = 1;
		memo[1] = 1;
		for(int i=2; i<=N; i++) {
			memo[i] = memo[i-1]+memo[i-2];
		}
		
		int answer = 1;
		int prev = 0;
		for(int i=0; i<M; i++) {
			int k = Integer.parseInt(br.readLine());
			answer *= memo[k-prev-1];
			prev = k;
		}// end for 
		
		answer *= memo[N-prev];
		System.out.println(answer);
		
	}
	
//	static int[] vip = new int[44];
//	static int[][] dp = new int[44][44];
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine()); // 좌석갯수 : 1~40 
//		int M = Integer.parseInt(br.readLine()); // 고정석갯수 : 0~N
//		for(int i=1; i<=M; i++) {
//			int k = Integer.parseInt(br.readLine());
//			vip[k]=1; // 고정좌석
//		}
//		
//		dp[1][0] = 0;
//		dp[1][1] = 1;
//		// ex) 길이9를 예제로 생각해보자. 
//		for(int i=2; i<=N; i++) {
//			// 자신이나 이전이 vip라면
//			if(vip[i]==1 || vip[i-1]==1) {
//				dp[i][i-1]=0;
//				System.out.println(i+"또는 이전좌석이 고정좌석이에요! ");
//				System.out.printf("dp[%d][%d] = 0%n",i,i-1);
//			}else {
//				// ex) 마지막에 8이오는 경우. 
//				// 8이오기 위해서는 배열의끝이 7인경우의수(dp[i-1][i-2])를 제외해주어야한다.
//				// 7로끝나면 9가 올 수 없기때문.
//				dp[i][i-1] = dp[i-1][i-1];
//				System.out.println(i+"는 고정좌석x ");
//				System.out.printf("dp[%d][%d] = dp[%d][%d]%n",i,i-1,i-1,i-1);
//				System.out.println(dp[i][i-1]+" = "+dp[i-1][i-1]);
//			}
//			// dp[i][i] : i로 끝나는 i길이의 모든 좌석배치방법.
//			// ex) 9길이의 배열에서 마지막에 9가 오기위해서는 
//			// 이전길이의 배열의 끝이 8로끝나도되고 7로끝나도 되기 때문이다. 
//			dp[i][i] = dp[i-1][i-1] + dp[i-1][i-2];
//			System.out.printf("dp[%d][%d] = dp[%d][%d]+ dp[%d][%d] %n",
//					i,i,i-1,i-1,i-1,i-2);
//			System.out.println(dp[i][i]+" = "+dp[i-1][i-1]+" + "+dp[i-1][i-2]);
//			System.out.println("====================");
//		}
//		System.out.println(dp[N][N]+dp[N][N-1]);
//		
//	}
	
}
