//package solutions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BinaryTree {
//
//	private int key;
//	private BinaryTree left;
//	private BinaryTree right;
//	private BinaryTree parent;
//
//	public BinaryTree(int key, BinaryTree first, BinaryTree second) {
//		this.key = key;
//		this.left = first;
//		this.right = second;
//		if(this.left != null) {
//			this.left.parent = this;
//		}
//		
//		if(this.right != null) {
//			this.right.parent = this;
//		}
//
//	}
//
//	public Integer findLowestCommonAncestor(int first, int second) {
//
//		List<Integer> firstPath = new ArrayList<>();
//		List<Integer> secondPath = new ArrayList<>();
//		
//		BinaryTree firstNode = findNode(first, this);
//		BinaryTree secondNode = findNode(second, this);
//
//		
//		BinaryTree current = firstNode;
//		while(current != null) {
//			firstPath.add(current.key);
//			current = current.parent;
//		}
//
//		
//		
//		current = secondNode;
//		while(current != null) {
//			secondPath.add(current.key);
//			current = current.parent;
//		}
//
//		
//		int index = 0;
//		for(int i = 0; (firstPath.get(firstPath.size() - 1 - i) == secondPath.get(secondPath.size() -1 -i)) ; i++){
//			index++;
//			
//		}
//		index--;
//		
//
//		return firstPath.get(firstPath.size() -1 - index);
//	}
//
//	private BinaryTree findNode(int searcheKey, BinaryTree binaryTree) {
//		
//		BinaryTree result = null;
//
//		int currentKey = binaryTree.key;
//		
//		if(currentKey == searcheKey) {
//			return binaryTree;
//		}
//		
//		if(binaryTree.left != null) {
//						
//			result =  findNode(searcheKey, binaryTree.left);
//			if (result != null) {
//				return result;
//			}
//		}
//		
//		if(binaryTree.right != null) {
//			result =  findNode(searcheKey, binaryTree.right);
//			if (result != null) {
//				return result;
//			}
//		}
//		
//		return result;
//		
//		
//		
//	}
//
//
//	public List<Integer> topView() {
//		return null;
//	}
//}


package solutions;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

	private int value;
	private BinaryTree left;
	private BinaryTree right;


	public BinaryTree(int key, BinaryTree first, BinaryTree second) {
		this.value = key;
		this.left = first;
		this.right = second;

	}

	public Integer findLowestCommonAncestor(int first, int second) {
		
		List<Integer> firstPath = findPath(first);
		List<Integer> secondPath = findPath( second);
		
		int smallerSize = Math.min(firstPath.size(), secondPath.size());
		
		int i = 0;
		for(; i < smallerSize; i++) {
			
			if(!firstPath.get(i).equals(secondPath.get(i))) {
				break;
			}
		}
		
		
		

		return firstPath.get(i - 1); 
	}

	


	private List<Integer> findPath( int element) {
		
		List<Integer> result = new ArrayList<>();
		
		findNodePath(this,element,result);

		
		return result;
	}

	private boolean findNodePath(BinaryTree binaryTree, int element, List<Integer> currentPath) {
		if(binaryTree == null) {
			return false;
		}
		
		if(binaryTree.value == element) {
			return true;
		}
		
		currentPath.add(binaryTree.value);
		
		boolean leftResult = findNodePath(binaryTree.left, element, currentPath);
		if(leftResult) {
			return true;
		}
		
		
		boolean rightResult = findNodePath(binaryTree.right, element, currentPath);
		if(rightResult) {
			return true;
		}
		
		currentPath.remove(Integer.valueOf(binaryTree.value)) ;
		return false;
	}

	public List<Integer> topView() {
		return null;
	}
}




