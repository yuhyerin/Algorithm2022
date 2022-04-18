package com.algo.programmers;

import java.util.LinkedList;
import java.util.List;

public class L2_프린터 {
	public static void main(String[] args) {
		L2_프린터 t = new L2_프린터();
		int[] priorities = {2,1,3,2};
		int location = 2;
		System.out.println(t.solution(priorities, location));
	}
	public int solution(int[] priorities, int location) {
        int N = priorities.length;
        int[] count = new int[10];
        String findDocument = "";
        
        List<String> que = new LinkedList<String>();
        
        for(int i=0; i<N; i++){
            int p = priorities[i];
            String document = ""+(char)('A'+p)+(count[p]);
            que.add(document);
            count[p]++;
            if(i==location){
                findDocument = document;
            }
        }
        
        int cnt = 1;
        while(!que.isEmpty()){
            String cur = que.remove(0);
            boolean isMorePriority = false;
            for(String d : que){
                if(cur.charAt(0)<d.charAt(0)){
                    isMorePriority = true;
                    break;
                }
            }// end for
            
            if(isMorePriority){
                que.add(cur);
            }
            else{
                if(cur.equals(findDocument)){
                    break;
                }
                cnt++;
            }
        }// end while 
        
        return cnt;
    }
}
