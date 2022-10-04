package com.algo.bj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_3687_성냥개비 {
	
	// 2개사용 -> 1
	// 3개사용 -> 7
	// 4개사용 -> 4 
	// 5개사용 -> 2,3,5
	// 6개사용 -> 6,9,0
	// 7개사용 -> 8
	// 만들 수 있는 가장 큰수, 가장 작은수 출력.
	// 1. 가장 큰수 
	// 홀수개인 경우 : 홀수로 만들 수 있는 숫자를 만들고, 나머지는 1(2개사용으로) 채우기
	// 짝수개인 경우 : 모두 1로 채우기. (4개써서 4 만드는것보다 2개씩 2번써서 11 만드는게 큼)
	
	// 2. 가장 작은수 
	// 2개 dp[2]-> 1
	// 3개 dp[3]-> 7
	// 4개 dp[4]-> 4
	// 5개 dp[5]-> 2
	// 6개 dp[6]-> 6 (0으로 시작할 수 없기 때문)
	// 7개 dp[7]-> 8
	// 8개 dp[8]-> 10 (dp[2]+dp[6] /8개이상부터는 한자리 숫자는 못만듦. )
	// **
	// 9개 -> 18 (dp[2]+dp[7])
	// 10개 -> 22 (dp[5]+dp[5] /2개로 1을만들면.. 8이남게되기때문.)
	// 11개 -> 20 (dp[5]+dp[6]) 
	// 12개 -> 28 (dp[5]+dp[7])
	// 13개 -> 68 (dp[6]+dp[7])
	// 14개 -> 88 (dp[7]+dp[7])
	// ** 
	// 15개 -> 108 (dp[8]+dp[7])
	// ...
	
	static int[] numbers = {6,2,5,5,4,5,6,3,7,6};
	static long[] minDp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine()); // 2~ 100개 
			minDp = new long[101];
			Arrays.fill(minDp, Long.MAX_VALUE);// 최대값으로 초기화 
			minDp[2]=1;
			minDp[3]=7;
			minDp[4]=4;
			minDp[5]=2;
			minDp[6]=6;
			minDp[7]=8;
			minDp[8]=10;
			
			String[] add = {"0","0","1","7","4","2","0","8"};
			// i:성냥갯수 (9부터~ )
			for(int i=9; i<= n; i++) {
//				System.out.println(">>> minDp["+i+"]의 후보들 <<<");
				for(int j=2; j<=7; j++) {
					// i:[9] - (minDp[7]+add[2]) (minDp[6]+add[3]) (minDp[5]+add[4]) (minDp[4]+add[5]) (minDp[3]+add[6]) (minDp[2]+add[7])
					// i:[10]- (8+2) (7+3) (6+4) (5+5) (4+6) (3+7) 
					// i:[11]- (9+2) (8+3) (7+4) (6+5) (5+6) (4+7)
					String value = ""+minDp[i-j]+add[j];
//					System.out.printf("(%d+%d)>>%s ",(i-j),j,value);
					minDp[i]= Math.min(Long.parseLong(value), minDp[i]);
				}
//				System.out.printf(" 최종>> %d %n",minDp[i]);
			}// end for
			
			StringBuilder max = new StringBuilder();
			// ex) 10개 -> 각2개씩 써서 1을 5개만듦. 
			// ex) 11개 -> 3개사용해서 7을 1개 만들고 나머지로 1을 5개만듦.
			long a = n/2;
			long b = n%2;
//			System.out.println("n:"+n+", a:"+a+", b:"+b);
			if(b==1) {
				// 홀수면 
				max.append("7"); // 가장적은 홀수개를 이용해서 만들수 있는 수.
			}else {
				// 짝수면
				max.append("1");
			}
			for(int i=0; i<a-1; i++) {
				max.append("1");
			}
			System.out.println(minDp[n]+" "+max.toString());
			
		}// end Testcase 
	}

}
