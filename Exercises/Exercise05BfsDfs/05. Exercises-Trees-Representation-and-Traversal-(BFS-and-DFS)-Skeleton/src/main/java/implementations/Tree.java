package implementations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.AbstractTree;

public class Tree<E> implements AbstractTree<E> {

	private E key;
	private Tree<E> parent;
	private List<Tree<E>> children;

	public Tree(E key/* , Tree<E>... subtrees */) {
		this.key = key;
		this.children = new ArrayList<>();
//		for (Tree<E> tree : subtrees) {
//			tree.setParent(this);
//			this.children.add(tree);
//		}
	}
	
	
	public Tree() {

		this.children = new ArrayList<>();

	}

	@Override
	public void setParent(Tree<E> parent) {
		this.parent = parent;

	}

	@Override
	public void addChild(Tree<E> child) {
		this.children.add(child);
	}

	@Override
	public Tree<E> getParent() {
		return this.parent;
	}

	@Override
	public E getKey() {
		return this.key;
	}

	@Override
	public String getAsString() {

		Deque<Tree<E>> deque = new ArrayDeque<>();

		StringBuilder sb = new StringBuilder();

		traverseTreeWithRecurrence(sb, 0, this);

		return sb.toString().trim();
	}

//	public String traverseWithBFS() {
//
//		StringBuilder sb = new StringBuilder();
//
//		Deque<Tree<E>> queue = new ArrayDeque<>();
//
//		queue.offer(this);
//		
//		int indent = 0;
//		
//		while(!queue.isEmpty()) {
//			Tree<E> tree = queue.poll();
//			
//			if(tree.getParent() != null) {
//				indent = 2;
//			}
//			
//			if(tree.children.size() == 0) {
//				indent = 4;
//			}
//			
//			sb.append(getPadding(indent))
//			.append(tree.key)
//			.append("\r\n");
//			
//			for (Tree<E> child : tree.children) {
//				
//				queue.offer(child);
//			}
//			
//		}
//
//		return sb.toString().trim();
//
//	}
	
	public List<Tree<E>> traverseWithBFS() {

		Deque<Tree<E>> queue = new ArrayDeque<>();

		queue.offer(this);
		
		List<Tree<E>> allNodes = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			Tree<E> tree = queue.poll();
			allNodes.add(tree);
			
			for (Tree<E> child : tree.children) {
				
				queue.offer(child);
			}
			
		}

		return allNodes;

	}

	private void traverseTreeWithRecurrence(StringBuilder sb, int indent, Tree<E> tree) {

//		sb.append(getPadding(indent)).append(tree.getKey()).append("\r\n");
		sb.append(getPadding(indent)).append(tree.getKey()).append(System.lineSeparator());
		for (Tree<E> child : tree.children) {
			traverseTreeWithRecurrence(sb, indent + 2, child);
		}

	}
	
	private void traverseTreeWithRecurrence(List<Tree<E>> collection,  Tree<E> tree) {
		
		collection.add(tree);
		
		for (Tree<E> child : tree.children) {
			traverseTreeWithRecurrence(collection, child);
		}

	}

	private String getPadding(int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(" ");
		}

		return sb.toString();
	}

	@Override
	public List<E> getLeafKeys() {
		return traverseWithBFS().stream().filter(tree -> tree.children.size() == 0)
//				.sorted((f,s) -> f.getKey() - s.getKey())
				.map(Tree::getKey)
				.collect(Collectors.toList());
	}

	@Override
	public List<E> getMiddleKeys() {
		List<Tree<E>> allNodes = new ArrayList<>();
		this.traverseTreeWithRecurrence(allNodes, this);
		
		return allNodes.stream()
				.filter(tree -> tree.parent != null && tree.children.size() > 0 )
				.map(Tree::getKey)
//				.collect(Collectors.toList());
				.collect(Collectors.toCollection(ArrayList::new));
	}

//	@Override
//	public Tree<E> getDeepestLeftmostNode() {
//		
//		List<Tree<E>> trees = this.traverseWithBFS();
//		
//		
//		int maxPath = 0;
//		Tree<E>deepestNode = null;
//		
//		for (Tree<E> tree : trees) {
//			if(tree.isLeaf()) {
//				int currentPath = getStepsFromLeafToRoot(tree); 
//				if(currentPath > maxPath) {
//					maxPath = currentPath;
//					deepestNode = tree;
//				}
//			}
//		}
//		
//		return deepestNode;
//	}
	
	
	@Override
	public Tree<E> getDeepestLeftmostNode() {

		int[] maxPath = new int[1]; 
		List<Tree<E>>deepestNode = new ArrayList<>();
		
		deepestNode.add(new Tree<>()); 
		
		int max = 0;
		
		findDeepestNodeDFS(deepestNode, maxPath,max, this);
		
		return deepestNode.get(0); 
	}
	
	
	
	
	private void findDeepestNodeDFS(List<Tree<E>> deepestNode, int[] maxPath, int max, Tree<E> tree) {
		
		
		max++;
		
		if(max > maxPath[0]) {
			maxPath[0] = max;
			deepestNode.set(0, tree) ;
		}
		
		for (Tree<E> child : tree.children) {
			findDeepestNodeDFS(deepestNode, maxPath,max, child);
		}
		
		
	}


	private boolean isLeaf() {
		return this.parent != null && this.children.size() == 0 ;
	}

	@Override
	public List<E> getLongestPath() {
		
		List<E> longestPathElements = new ArrayList<>(); 
		Tree<E> deepestNode = this.getDeepestLeftmostNode();
		
		while(deepestNode!=null) {
			longestPathElements.add(deepestNode.key);
			deepestNode = deepestNode.parent;
		}
		
		Collections.reverse(longestPathElements);
		
		return longestPathElements; 
	}

	@Override
	public List<List<E>> pathsWithGivenSum(int sum) {
		
		List<List<E>> result = new ArrayList<>();
	
		findPathsWithGivenSum(sum,result, this, 0 );
		
		return result;
	}

	private void findPathsWithGivenSum(int sum, List<List<E>> result, Tree<E> node, int calculatedSum) {
		
		calculatedSum += (Integer)node.getKey();
		
		if(calculatedSum == sum) {
			List<E> entry = new ArrayList<>();
			
			Tree<E> current = node;
			
			while(current!= null) {
				entry.add(current.key);
				current = current.parent;
			}
			Collections.reverse(entry);
			result.add(entry); 
		}
		
		
		for (Tree<E> child : node.children) {
			
			findPathsWithGivenSum(sum,result, child, calculatedSum );
			
		}
		
	}


	@Override
	public List<Tree<E>> subTreesWithGivenSum(int sum) {
		
		
		List<Tree<E>>result = new ArrayList<>();
		
		findSubtreesWithGivenSum(sum, result, this);
		
		
		return result;
	}


	private int findSubtreesWithGivenSum(int sum, List<Tree<E>> result, Tree<E> node) {
		
		int calculatedSum = (Integer)node.key;
		

		for (Tree<E> tree : node.children) {
			calculatedSum += findSubtreesWithGivenSum(sum, result, tree);
		}
		
		if(calculatedSum == sum) {
			result.add(node);

		}
		
		
		return calculatedSum;

	}
}
