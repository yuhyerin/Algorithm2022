package com.algo.programmers;

public class L2_단체사진찍기 {
	public static void main(String[] args) {
		L2_단체사진찍기 t = new L2_단체사진찍기();
		int n = 2; // 조건의 갯수.
		String[] data = {"N~F=0", "R~T>2"};
		System.out.println(t.solution(n, data));
	}
	// 1. 네오 & 프로도 나란히.
	// 2. 튜브 & 라이언 적어도 3칸이상 떨어져.
	// 모든 경우의 수 리턴.
	public int solution(int n, String[] data) {
        int answer = 0;
        // 어피치/콘/프로도/제이지/무지/네오/라이언/튜브 
		String[] names = {"A","C","F","J","M","N","R","T"};
		int N = names.length;
        
		String[] pick = new String[N];
		boolean[] visit = new boolean[N];
        answer += permutation(N,0,names,visit, n,data, pick);
        
//        for(int i=0; i<n; i++) {
//        	String condition = data[i]; // N~F=0 형태.
//        	char me = condition.charAt(0); // 조건 제시자 
//        	char you = condition.charAt(2); // 상대방
//        	char giho = condition.charAt(3); // =, <, >
//        	int num = condition.charAt(4)-'0'; // 두 프렌즈 사이에 있는 다른프렌즈 수
//        }
        return answer;
    }
	
	private int permutation(int N, int cur, String[] names,boolean[] visit, int n, String[] data, String[] pick) {
		
		if(cur==N) {
			if(checkCondition(n,data,pick)) {
				return 1;
			}else {
				return 0;
			}
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				pick[cur] = names[i];
				answer += permutation(N,cur+1, names,visit, n, data, pick);
				visit[i] = false;
				pick[cur] = "";
			}
		}
		
		return answer;
		
	}
	
	private boolean checkCondition(int n, String[] data, String[] pick) {
		String pickStr = new String("");
		for(String str : pick) {
			pickStr+=str;
		}
		
		for(int i=0; i<n; i++) {
			String condition = data[i];
			char me = condition.charAt(0);
			char you = condition.charAt(2);
			char giho = condition.charAt(3);
			int dist = condition.charAt(4)-'0';
			
			int meIdx = -1;
			int youIdx = -1;
			// 조건제시자와 상대방 사이의 거리를 계산.
			for(int j=0; j<8; j++) {
				char ch = pickStr.charAt(j);
				if(ch==me) {
					meIdx = j;
				}
				if(ch==you) {
					youIdx = j;
				}
				if(meIdx!=-1 && youIdx !=-1) break;
			}
			
			int calcDist = Math.abs(meIdx-youIdx)-1;
			
			if(giho=='=') {
				if(calcDist != dist) {
					return false;
				}
				
			}else if(giho=='>') {
				if(calcDist <= dist) {
					return false;
				}
			}else {
				if(calcDist >= dist) {
					return false;
				}
			}
		}
		return true;
	}
}
