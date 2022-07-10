package com.algo.programmers.dp;

public class 정수삼각형 {
	public static void main(String[] args) {
		정수삼각형 t = new 정수삼각형();
		int[][] triangle = {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
		int result = t.solution(triangle);
		System.out.println(result);
	}
	/**
	 * 각 층에서의 최대값을 구해야 한다.
	 * 점화식 db[i][j] = triangle[i][j]+ 좌우노드 중 최대값 
	 * 
	 * triangle 
	 * 7  0  0  0  0
	 * 3  8  0  0  0
	 * 8  1  0  0  0
	 * 2  7  4  4  0
	 * 4  5  2  6  5
	 * 
	 * dp
	 * 7  0  0  0  0
	 * 10 15 0  0  0
	 * 18 16 15 0  0
	 * 20 25 20 19 0
	 * 24 30 27 26 24
	 * */
	public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        
        for(int i=1; i< triangle.length; i++) {
        	dp[i][0] = triangle[i][0] + dp[i-1][0]; // 맨 첫번째열은 전행의 첫번째열 dp값을 더해야 되는 구조.
        	
        	for(int j=1; j<i+1; j++) {
        		// 두번째 열부터는 전행의 전열, 같은 열 두개를 비교해서 큰값을 더해주는 구조.  
        		dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
        	}
        }
        
        int max = 0;
        for(int i=0; i<dp[dp.length-1].length; i++) {
        	max = Math.max(dp[dp.length-1][i], max);
        }
        
        return max;
    }
}
