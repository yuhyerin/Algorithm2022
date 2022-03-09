package com.algo.programmers;

import java.util.ArrayList;
import java.util.List;

public class L2_뉴스클러스터링 {
	
	public static void main(String[] args) {
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
		L2_뉴스클러스터링 t = new L2_뉴스클러스터링();
		System.out.println(t.solution(str1, str2));
	}
	/**
	 * 문자열 길이는 2~1,000
       두글자씩 끊어서 다중집합의 원소로 만든다.
       영문자만 유효. 공백,숫자,특문은 버린다.
       대소문자 무시. 
       결과값에 65535을 곱한 후 소수점아래를 버리고 정수부만 출력.
     */
	public int solution(String str1, String str2) {
        
        // 정규식으로 숫자,특문 전처리 
        str1=str1.replaceAll("[^a-zA-Z]", "-").replaceAll(" ", "-").toLowerCase();
        str2=str2.replaceAll("[^a-zA-Z]", "-").replaceAll(" ", "-").toLowerCase();
        
        List<String> A = new ArrayList<>();
        List<String> B = new ArrayList<>();
        
        int[] aCount = new int[1001];
        int[] bCount = new int[1001];
        
        makeElement(A,aCount,str1);
        makeElement(B,bCount,str2);
        
        int aLen = A.size();
        int bLen = B.size();
        
        int gyo = 0; //교집합 
        for(String a : A) {
        	for(String b : B) {
        		if(a.equals(b)){
        			gyo++;
        		}
        	}
        }
        int hap = aLen+bLen-gyo; // 합집합
        if(hap==0) return 65536;
        
        return (int)((double)gyo/hap*65536);
    }

	private void makeElement(List<String> A, int[] aCount, String str1) {
		for(int i=0; i<str1.length()-1; i++) {
        	String val = str1.substring(i,i+2);
        	int idx = (val.charAt(0)-'a' + 1) * 26 + (val.charAt(1)-'a' + 1);
        	if(!val.contains("-")) {
        		val += aCount[idx]++;
        		A.add(val);
        	}
        }
	}
}
