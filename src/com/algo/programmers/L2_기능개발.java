package com.algo.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class L2_기능개발 {
	public static void main(String[] args) {
		L2_기능개발 t = new L2_기능개발();
//		int[] progresses = {93, 30, 55};
		int[] progresses = {95, 90, 99, 99, 80, 99};
		// 첫번째 기능 -> 93%완료 ( 하루에 1%씩 작업가능. 7일간작업후 배포가능 )
		// 두번째 기능 -> 30%완료 ( 하루에 30%씩 작업가능. 3일간작업후 배포가능 )
		// 세번째 기능 -> 55%완료 ( 하루에 5%씩 작업가능. 9일간작업후 배포가능)
		// 결론: 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됨...
//		int[] speeds = {1,30,5};
		int[] speeds = {1,1,1,1,1,1};
		int[] result = t.solution(progresses, speeds);
		for(int r: result) {
			System.out.print(r+" ");
		}
		System.out.println();
	}
	
	public int[] solution(int[] progresses, int[] speeds) {
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        int N = progresses.length;
        Queue<Integer> que = new LinkedList<Integer>();
        for(int i=0; i<N; i++) {
        	double day =  Math.ceil((double)(100-progresses[i])/speeds[i]);
        	que.add((int)day);
        }
        
        int prev = que.poll(); // 맨앞에 하나 꺼내고
        int count = 1;
        while(!que.isEmpty()) {
        	int next = que.poll();
        	if(prev<next) {
        		result.add(count);
        		count=1;
        		prev = next; // prev를 바꿔줘야 하는 때는 더큰값을 만났을때만..
        	}else {
        		count++;
        	}
        	
        }
        result.add(count);
        
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
        	answer[i] = result.get(i);
        }
        return answer;
    }
}
