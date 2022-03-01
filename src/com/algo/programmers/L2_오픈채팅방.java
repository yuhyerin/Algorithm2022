package com.algo.programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class L2_오픈채팅방 {
	public static void main(String[] args) {
		L2_오픈채팅방 t = new L2_오픈채팅방();
		String[] record = {"Enter uid1234 Muzi", 
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"};
		
		String[] answer = t.solution(record);
		for(String str : answer) {
			System.out.println(str);
		}
	}

	// 가상닉네임 
	// 관리자 창 
	// [닉네임]님이 들어왔습니다. 
	// [닉네임]님이 나갔습니다.
	// 닉네임 변경방법. 1) 나갔다 새로운이름으로 들어오기, 2) 채팅방에서 변경하기 
	// 변경하면 기존 채팅방에 출력되있던 메세지도 변경됨.
	// 채팅방 중복닉네임 허용
	// 
	
	public class User{
		String uid;
		String type;
		public User(String uid, String type) {
			super();
			this.uid = uid;
			this.type = type;
		}
	}
	
	private String[] solution(String[] record) {
		int N = record.length; // 문자열 길이 (1~100,000)
		Map<String, String> names = new HashMap<String, String>();
		Queue<User> que = new LinkedList<User>();
		
		StringTokenizer st = null;
		int changeCnt = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(record[i]);
			String type = st.nextToken();
			String uid = st.nextToken();
			if("Enter".equals(type)){
				// 들어옴 
				String name = st.nextToken();
				que.add(new User(uid,type));
				names.put(uid, name);
				
			}else if("Leave".equals(type)) {
				// 나감
				que.add(new User(uid,type));
				
			}else {
				// 변경
				changeCnt++;
				String newName = st.nextToken();
				names.put(uid, newName);
			}
		}
		
		String[] answer = new String[N-changeCnt];
		int idx=0;
		while(!que.isEmpty()) {
			User user = que.poll();
			if("Enter".equals(user.type)) {
				answer[idx++] = names.get(user.uid)+"님이 들어왔습니다.";
			}else {
				answer[idx++] =names.get(user.uid)+"님이 나갔습니다.";
			}
		}
		return answer;
	}
}
