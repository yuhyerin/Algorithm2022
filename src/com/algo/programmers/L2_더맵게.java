package com.algo.programmers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class L2_더맵게 {
	
	public static void main(String[] args) {
		L2_더맵게 t = new L2_더맵게();
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		System.out.println(t.solution(scoville, K));
	}
	/**
	 * 모든음식을 K 이상으로 만들고싶다.
	 * 음식중에 스코빌지수 가장낮은 2개를 선택해서 섞는다.
	 * 가장작은거+두번째로작은거*2
	 * 
	 * 모든음식이 K 이상이 될때까지 반복해서 섞는다.
	 * 섞어야 하는 최소횟수 리턴.
	 * */
	public int solution(int[] scoville, int K) {
        int answer = 0;
        
        int N = scoville.length;
        Queue<Long> que = new PriorityQueue<Long>();
        
        for(int i=0; i<N; i++) {
        	que.add((long)scoville[i]);
        }// 배열 -> Priority que에 넣기.  (정렬됨) 
        
        while(true) {
            answer++;
            if(que.size()==1){
                if(que.peek()>=K){
                    break;
                }
            }
        	if(que.size()<2) {
        		return -1;
        	}
            long a = que.poll();
            long b = que.poll();
            if(a<K) {
            	long mix = a+2L*b;
                que.add(mix);
            }else {
            	break;
            }
        }
        
        return answer-1;
    }
	
}
