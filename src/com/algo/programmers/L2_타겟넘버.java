package com.algo.programmers;

public class L2_타겟넘버 {
	
	public static void main(String[] args) {
		L2_타겟넘버 t = new L2_타겟넘버();
//		int[] numbers = {1, 1, 1, 1, 1};
		int[] numbers = {4,1,2,1};
//		int target = 3;
		int target = 2;
		System.out.println(t.solution(numbers, target));
	}
	
	public int solution(int[] numbers, int target) {
        int count = 0;
        
        // 현재인덱스, 부호(false:-, true:+) 
        count=go(numbers,target,-1,-1,0);
        
        return count;
    }

	private int go(int[] numbers,int target, int idx, int depth, int result) {
		int count = 0;
		if(depth==numbers.length-1) {
			if(result == target) {
				return 1;
			}
		}
		
		if(idx<numbers.length-1) {
			count+=go(numbers, target, idx+1, depth+1, result+numbers[idx+1]); // (+) 
			count+=go(numbers, target, idx+1, depth+1,result-1*numbers[idx+1]); // (-) 
		}
		return count;
	}
}
