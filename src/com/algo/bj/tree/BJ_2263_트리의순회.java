package com.algo.bj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2263_트리의순회 {
	
	static class Node{
		int num;
		Node left;
		Node right;
		
		Node(int num){
			this.num = num;
		}
	}
	
	static int N;
	static int[] inorder;
	static int[] postorder;
	static Node root;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// inorder ( L-V-R ) : 루트노드 기준으로 왼쪽은 왼쪽 서브트리, 오른쪽은 오른쪽 서브트리 구성.
		StringTokenizer st = new StringTokenizer(br.readLine());
		inorder = new int[N];
		for(int i=0; i<N; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		
		// postorder ( L-R-V ) : 맨마지막노드가 최상위 루트노드.
		st = new StringTokenizer(br.readLine());
		postorder = new int[N];
		for(int i=0; i<N; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		
		Node root = makeTree(0,N-1,0,N-1);
		
		preorder(root);
		System.out.println(sb.append("\n").toString());
	}
	
	private static void preorder(Node root) {
		sb.append(root.num).append(" ");
		if(root.left !=null) preorder(root.left);
		if(root.right !=null) preorder(root.right);
	}

	private static Node makeTree(int inStart, int inEnd, int postStart, int postEnd) {
		
		if(postStart>postEnd) {
			return null;
		}else if(postStart==postEnd) {
			return new Node(postorder[postStart]);
		}
		
		Node root = new Node(postorder[postEnd]);
		
		int rootIdx;
		for(rootIdx=inStart; rootIdx<=inEnd; rootIdx++) {
			if(inorder[rootIdx]==root.num) {
				break;
			}
		}
		int leftSize = rootIdx - inStart; // 왼쪽서브트리의노드갯수: rootIdx - inStart 
		int rightSize = inEnd - rootIdx; // 오른쪽서브트리의노드갯수: inEnd - rootIdx
		
		root.left = makeTree(inStart, rootIdx-1, postStart, postStart+leftSize-1);  // left sub      
		root.right = makeTree(rootIdx+1 , inEnd, postEnd-rightSize, postEnd-1); // right sub      
		
		return root;
	}
	
}
