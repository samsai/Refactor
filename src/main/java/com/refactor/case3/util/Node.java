package com.refactor.case3.util;

import java.util.List;

public class Node {
	
	private List<Leaf> leafs;
	
	private List<TreeRoot> treeRoots;

	public List<Leaf> getLeafs() {
		return leafs;
	}

	public void setLeafs(List<Leaf> leafs) {
		this.leafs = leafs;
	}

	public List<TreeRoot> getTreeRoots() {
		return treeRoots;
	}

	public void setTreeRoots(List<TreeRoot> treeRoots) {
		this.treeRoots = treeRoots;
	}
}
