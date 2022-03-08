package com.algo.programmers.study;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class 신고결과받기 {
	/**
	 * 각 유저는 한번에 한명의 유저 신고 가능  
	 * 신고횟수 제한 x 
	 * 서로 다른 유저를 계속해서 신고 가능.
	 * 한 유저를 여러번 신고는 가능하나, 동일 유저에 대한 신고횟수는 1회로 처리됨.
	 * k번 이상 신고된 유저는 게시판 이용정지.
	 * 해당유저를 신고한 모든유저에게 정지사실이 메일로 발송.
	 * */
	public static void main(String[] args) {
		신고결과받기 t = new 신고결과받기();
//		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] id_list = {"con", "ryan"};
//		String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k = 3;
		int[] result = t.solution(id_list, report, k);
		for(int r : result) {
			System.out.print(r+" ");
		}
		System.out.println();
	}
	
	public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        Map<String, HashSet<String>> map2 = new HashMap<String, HashSet<String>>();
        for(String id : id_list) {
        	map.put(id, new HashSet<>());
        	map2.put(id, new HashSet<>());
        }
        
        for(String input : report) {
        	String[] names = input.split(" ");
        	// map1 : [사람이름] - (신고자1, 신고자2, ... )
        	map.get(names[1]).add(names[0]);
        	// map2 : [사람이름] - (내가신고한사람1, 내가신고한사람2, ... )
        	map2.get(names[0]).add(names[1]);
        }
        
        for(int i=0; i<id_list.length; i++) {
        	answer[i] = map2.get(id_list[i]).size();
        }// 초기화
        
        for(int i=0; i<id_list.length; i++) {
        	int n = map.get(id_list[i]).size();
        	if(n<k) { // k번 이상 신고당하지 않았다면... 
        		for(int j=0; j<id_list.length; j++) {
        			if(map2.get(id_list[j]).contains(id_list[i])) {
        				answer[j]--;
        			}
        		}
        	}
        }
        
        return answer;
    }
}
