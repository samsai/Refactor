package com.refactor.case3.util;

public class Leaf {
	private String clazz;
	private String detail;
	private int rootId;
	private Node node;
	private String tagId;
	private String opType;
	private String opValue;
	private String leafId;
	private UiConditionNameListTag tag;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getRootId() {
		return rootId;
	}

	public void setRootId(int rootId) {
		this.rootId = rootId;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpValue() {
		return opValue;
	}

	public void setOpValue(String opValue) {
		this.opValue = opValue;
	}

	public String getLeafId() {
		return leafId;
	}

	public void setLeafId(String leafId) {
		this.leafId = leafId;
	}

	public UiConditionNameListTag getTag() {
		return tag;
	}

	public void setTag(UiConditionNameListTag tag) {
		this.tag = tag;
	}
}
