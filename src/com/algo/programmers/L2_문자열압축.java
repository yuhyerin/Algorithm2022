package com.algo.programmers;

import java.util.*;

class L2_문자열압축 {
    // 비손실 문자열 압축...
    // 2a2ba3c (문자가 반복되지 않아 한번만 나타난 경우 1은 생략)
    // 2개단위로 잘라서 압축 -> 2ab2cd2ab2cd  or 8개단위로잘라서압축. 
    // 압축해서 표현한 문자열 중 가장짧은것의 길이를 리턴... 
	public static void main(String[] args) {
		L2_문자열압축 t = new L2_문자열압축();
		String s = "abcabcabcabcdededededede";
		System.out.println(t.solution(s));
	}
	
    public int solution(String s) {
        
        int N = s.length();
        int answer = 1000;
        
        // 쪼개기 
        for(int i=1; i<=N; i++){
            int result = calc(s,N,i);
            if(answer>result){
                answer = result;
            }
        }
        return answer;
    }
    
    public int calc(String s, int N, int n) {
		
        int idx= 0;
        List<String> arr = new ArrayList<String>();
        
        // n개 크기로 문자열 쪼개기
        while(true){
            if(idx+n>=N){
                arr.add(s.substring(idx));
                break;
            }
            arr.add(s.substring(idx, idx+n));
            idx+=n;
        }// end while
        
        int front = 0;
        int back = 1;
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        
        while(true){
        	if(back >= arr.size()) {
        		break;
        	}
        	
            if(arr.get(front).equals(arr.get(back))){
                cnt++;
                back++;
            }else{
                if(cnt!=1){
                    sb.append(cnt);
                }
                sb.append(arr.get(front));
                front = back;
                back = front+1;
                cnt=1; // 초기화
            }
        }// end while
        if(cnt!=1){
            sb.append(cnt);
        }
        sb.append(arr.get(front));
       
        return sb.toString().length();
	}
    
}