package com.algo.programmers.study;

public class L1_숫자문자열과영단어 {
	
	public static void main(String[] args) {
		L1_숫자문자열과영단어 t = new L1_숫자문자열과영단어();
		String s = "123";
		System.out.println(t.solution(s));
	}
	
	// 0 1 2 3 4 5 6 7 8 9
	// ze on tw th fo fi si se ei ni
	public int solution(String s) {
        String answer = "";
        String[][] numbers = {
        		{"ze","4"}, 
        		{"on","3"},
        		{"tw","3"},
        		{"th","5"},
        		{"fo","4"},
        		{"fi","4"},
        		{"si","3"},
        		{"se","5"},
        		{"ei","5"},
        		{"ni","4"}};
        
        int cur = 0;
        while(cur<s.length()) {
        	
        	// 한글자 읽어서 숫자로 변환 시도
        	String strNum = s.charAt(cur)+"";
        	int realNum = 0;
        	try {
    			realNum = Integer.parseInt(strNum);
    			answer+=realNum;
    			cur++;
    		}catch(NumberFormatException e) {
    			// 영어로 변경 
    			String word = s.substring(cur,cur+2);
    			for(int i=0; i<10; i++) {
    				if(numbers[i][0].equals(word)) {
    					cur += Integer.parseInt(numbers[i][1]);
    					answer += i;
    					break;
    				}
    			}
    		}
        }
        return Integer.parseInt(answer);
    }
	
	public int solution2(String s) {
		
        String[] digits = {"0","1","2","3","4","5","6","7","8","9"};
        String[] alphabets = {"zero","one","two","three","four","five","six","seven","eight","nine"};

        for(int i=0; i<10; i++){
            s = s.replaceAll(alphabets[i],digits[i]);
        }

        return Integer.parseInt(s);
    }
}
