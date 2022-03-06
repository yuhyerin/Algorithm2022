package com.algo.programmers;

public class L2_124나라의숫자 {
	/**
	 * 1:1
	 * 2:2
	 * 3:4
	 * 
	 * 4:11
     * 5:12
	 * 6:14
	 * 
	 * 7:21
	 * 8:22
	 * 9:24
	 * 
	 * 10:41
	 * 11:42
	 * 12:44
	 * 
	 * 13:111
	 * 14:112
	 * 15:114
	 * 
	 * 16:121
	 * 17:122
	 * 18:124
	 * 
	 * 19:141
	 * 20:142
	 * 21:144 (9개)
	 * 22~30 / 9개
	 * 31~39 / 9개 
	 * 40:1111 
	 * */
	public String solution(int n) {
        String answer = "";
        int[] nums = {4,1,2};
        
        while(true) {
        	answer = nums[n%3]+answer;
        	n = (n-1)/3; // n=3일때, n/3해주면 몫1이 생김. 
        	if(n==0) break;
        }
        return answer;
    }
	
	public static void main(String[] args) {
		L2_124나라의숫자 t = new L2_124나라의숫자();
		for(int n=1; n<=40; n++) {
			System.out.println("n: "+ n+">>> "+t.solution(n));
			System.out.println("=============================");
		}
	}
	
}
