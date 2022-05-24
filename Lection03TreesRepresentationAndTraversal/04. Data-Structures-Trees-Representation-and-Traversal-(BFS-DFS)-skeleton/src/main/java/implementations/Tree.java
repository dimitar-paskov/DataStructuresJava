package implementations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import interfaces.AbstractTree;

public class Tree<E> implements AbstractTree<E> {

	private E value;
	private Tree<E> parent;
	private List<Tree<E>> children;

	public Tree(E value, Tree<E>... subtrees) {

		this.value = value;
		this.parent = null;
		this.children = new ArrayList<>();

//		for ( Tree<E> subtree : subtrees) {
//			this.children.add(subtree);
//		}
//		this.children.addAll(Arrays.asList(subtrees));
		
		for (Tree<E> subtree : subtrees ) {
			this.children.add(subtree);
			subtree.parent = this;
		}
	}

	@Override
	public List<E> orderBfs() {
		List<E> result = new ArrayList<>();
		
		if(this.value == null) {
			return result;
		}
		
		Deque<Tree<E>> childrenQueue = new ArrayDeque<>();

		childrenQueue.offer(this);

		while (!childrenQueue.isEmpty()) {

			Tree<E> current = childrenQueue.poll();
			result.add(current.value);
			for (Tree<E> child : current.children) {
				childrenQueue.offer(child);
			}

		}

		return result;
	}

//    Recursive Implementation
	@Override
	public List<E> orderDfs() {

		List<E> result = new ArrayList<>();

		this.doDfs(this, result);

		return result;
	}

	private void doDfs(Tree<E> node, List<E> result) {

		for (Tree<E> child : node.children) {
			this.doDfs(child, result);
		}

		result.add(node.value);
	}

//    Iterable variant??? 
//    @Override
//    public List<E> orderDfs() {
//    	
//    	List<E> result = new ArrayList<>();
//    	
//    	Deque<Tree<E>> toTraverse = new ArrayDeque<>();
//    	
//    	toTraverse.push(this);
//    	toTraverse.push(this);
//    	
//    	while(!toTraverse.isEmpty()) {
//    		
//    		Tree<E> current = toTraverse.pop();
//    		
//    		for (Tree<E> tree : current.children) {
//				 toTraverse.push(tree); 
//			}
//    		
//    		
//    		result.add(current.value); 
//
//    	};
//    	
//    	
//        return result;
//    }

	@Override
	public void addChild(E parentKey, Tree<E> child) {
		Tree<E> search = findBfs(parentKey); // BFS search variant
//		Tree<E> search = findRecursive(this, parentKey);

		if (search == null) {
			throw new IllegalArgumentException();

		}

		search.children.add(child);
		child.parent = search;
	}

// BFS search variant
	private Tree<E> findBfs(E parentKey) {

		ArrayDeque<Tree<E>> childrenQueue = new ArrayDeque<>();

		childrenQueue.offer(this);

		while (!childrenQueue.isEmpty()) {

			Tree<E> current = childrenQueue.poll();
			if (current.value.equals(parentKey)) {
				return current;
			}
			for (Tree<E> child : current.children) {
				childrenQueue.offer(child);
			}

		}

		return null;
	}

	// DFS search variant
	private Tree<E> findRecursive(Tree<E> current, E parentKey) {

		if (current.value.equals(parentKey)) {
			return current;
		}
		for (Tree<E> child : current.children) {
			Tree<E> found = this.findRecursive(child, parentKey);
			if (found != null) {
				return found;
			}
		}

		return null;
	}

	@Override
	public void removeNode(E nodeKey) {

		Tree<E> nodeToBeRemoved = findBfs(nodeKey);
		
		if(nodeToBeRemoved == null) {
			throw new IllegalArgumentException();
		}
		
		for (Tree<E> child : nodeToBeRemoved.children) {			
			child.parent = null;
		}
		
		nodeToBeRemoved.children.clear();

		Tree<E> parent = nodeToBeRemoved.parent;
		
		// if Node is not Root
		if(parent != null) {
//			for (int i = 0; i < parent.children.size(); i++) {
//				if (parent.children.get(i).equals(nodeToBeRemoved)) {
//					parent.children.remove(i);
//					break;
//				}
//			}
			parent.children.remove(nodeToBeRemoved);
		}
		
		nodeToBeRemoved.parent = null;
		nodeToBeRemoved.value = null;

	}


	private void swapChildInParent(Tree<E> searchedFirst, Tree<E> searchedSecond) {

		if (searchedFirst != null && searchedSecond != null) {
			int indexFT = 0;
			int indexST = 0;
			Tree<E> parentFT = searchedFirst.parent;

			for (int i = 0; i < parentFT.children.size(); i++) {
				if (parentFT.children.get(i).equals(searchedFirst)) {

					indexFT = i;
					break;
				}
			}

			Tree<E> parentST = searchedSecond.parent;
			for (int i = 0; i < parentST.children.size(); i++) {
				if (parentST.children.get(i).equals(searchedSecond)) {
					indexST = i;
					break;
				}
			}

			parentFT.children.set(indexFT, searchedSecond);
			parentST.children.set(indexST, searchedFirst);
		}
	}

	@Override
	public void swap(E firstKey, E secondKey) {
		Tree<E> searchedFirstTree = findBfs(firstKey);
		Tree<E> searchedSecondTree = findBfs(secondKey);
		
		if(searchedFirstTree == null ||searchedSecondTree == null ) {
			throw new IllegalArgumentException();
		}

		Tree<E> parentFT = searchedFirstTree.parent;
		Tree<E> parentST = searchedSecondTree.parent;
		
		if(parentFT == null) {
			this.value = searchedSecondTree.value;
			this.parent = null;
			this.children = searchedSecondTree.children;
			searchedSecondTree.parent = null;
			return;
		}
		
		if(parentST == null) {
			this.value = searchedFirstTree.value;
			this.parent = null;
			this.children = searchedFirstTree.children;
			searchedFirstTree.parent = null;
			return;
		}

		swapChildInParent(searchedFirstTree, searchedSecondTree);

		searchedFirstTree.parent = parentST;
		searchedSecondTree.parent = parentFT;

	}
}
