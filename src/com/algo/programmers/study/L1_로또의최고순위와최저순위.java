package com.algo.programmers.study;

import java.util.Arrays;

public class L1_로또의최고순위와최저순위 {
	
	public static void main(String[] args) {
		L1_로또의최고순위와최저순위 t = new L1_로또의최고순위와최저순위();
//		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] lottos = {0, 0, 0, 0, 0, 0};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		int[] result = t.solution(lottos, win_nums);
		for(int r: result) {
			System.out.print(r+" ");
		}
		System.out.println();
	}
	/**
	 * 등수 
	 * 6개 -> 1
	 * 5개 -> 2
	 * 4개 -> 3
	 * 3개 -> 4
	 * 2개 -> 5
	 * 1,0 -> 6
	 * */
	
	public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];// 최고, 최저 등수 
        
        Arrays.sort(win_nums); // 정렬 
        Arrays.sort(lottos);
        
        int zeroCnt = 0;
        for(int i=0; i<6; i++) { // 알아볼수없는 번호 갯수. 
        	if(lottos[i]==0) {
        		zeroCnt++;
        	}
        }
        // 일치하는 번호 갯수.
        int sameCnt = 0;
        for(int i=0; i<6; i++) {
        	for(int j=0; j<6; j++) {
        		if(lottos[i]==win_nums[j]) {
        			sameCnt++;
        			break;
        		}
        	}
        }
        //최저등수 
        answer[1]=getRank(sameCnt);
        
        //최고등수
        answer[0]=getRank(sameCnt+zeroCnt);
        
        
        
        return answer;
    }
	
	public int getRank(int cnt) {
		switch(cnt) {
        case 6:
        	return 1;
        case 5:
        	return 2;
        case 4:
        	return 3;
        case 3:
        	return 4;
        case 2:
        	return 5;
        default:
        	return 6;	
        }
	}
}
