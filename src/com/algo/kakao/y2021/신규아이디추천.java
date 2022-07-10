package com.algo.kakao.y2021;

public class 신규아이디추천 {
	
	public static void main(String[] args) {
		신규아이디추천 t = new 신규아이디추천();
		String new_id = "...!@BaT#*..y.abcdefghijklm";
//		String new_id = "z-+.^.";
//		String new_id = "=.=";
//		String new_id = "123_.def";
//		String new_id = "abcdefghijklmn.p";
		String result = t.solution(new_id);
		System.out.println("===정답===");
		System.out.println(result);
	}
	
	/**
	 * new_id : 길이 1~1000 문자열 
	 * 
	 * 
	 * 아이디 길이 3~15
	 * 소문자 숫자 - _ . 문자만 사용 가능
	 * 마침표는 처음과 끝에 사용할 수 없음. & 연속으로 사용할 수 없음.
	 * */
	public String solution(String new_id) {
        String answer = "";
        // 1. new_id의 모든 대문자를 소문자로 치환 
        answer = new_id.toLowerCase();
        
        // 2. new_id에서 알파벳,숫자,-,_,.를 제외한 모든 문자를 제거.
        answer = answer.replaceAll("[^a-z\\d\\-_.]", "");
        
        // 3. . 가 2번이상 연속된 부분을 하나의 . 으로 치환
//        int len = answer.length();
//        for(int n=len;n>=2; n--) {
//        	String find = ".".repeat(n);
//        	answer = answer.replace(find, ".");
//        }
        answer = answer.replaceAll("[.]+", "."); // 정규표현식 + 앞의문자가 1개이상..여러개반복되는 문자열
        
        // 4. 처음이나 끝에 . 이 있으면 제거.
        if(answer.length()>=1 && answer.charAt(0)=='.') {
        	answer = answer.substring(1);
        }
        if(answer.length()>=1 && answer.charAt(answer.length()-1)=='.') {
        	answer = answer.substring(0, answer.length()-1);
        }
        
        // 5. 빈문자열이면 a를 대입.
        if(answer.length()==0) {
        	answer = "a";
        }
        
        // 6. 길이가 16이상이면 끝에문자들은 제거.
        // ( 제거 후 .이 끝에 위치한다면 .도 추가로 제거함 )
        if(answer.length()>=16) {
        	answer = answer.substring(0,15);
        }
        
        // 뒤에서부터 연속된 . 을찾아 다 지워줘야함.
        int cnt = 0;
        for(int i=answer.length()-1; i>=0; i--) {
        	if(answer.charAt(i)=='.') {
        		cnt++;
        	}else {
        		break;
        	}
        }
        answer = answer.substring(0,answer.length()-cnt);
        
        // 7. 길이가 2자 이하라면 마지막문자를 반복해서 길이가 3이될때까지 붙여줌. 
        if(answer.length()<=2) {
        	char last = answer.charAt(answer.length()-1);
        	while(answer.length()!=3) {
        		answer += last;
        	}
        }
        
        return answer;
    }
	
}
