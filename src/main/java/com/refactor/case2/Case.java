/**
 * @company China Merchants Bank
 * @copyright Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case2;

import com.refactor.case2.util.Action;
import com.refactor.case2.util.CaseState;
import com.refactor.case2.util.CaseWorkflowRecord;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class Case implements Comparable<Case>{

	protected Long id;

	protected String name;

	protected String description;

	protected CaseState state;

	protected Set<Action> actions = new HashSet<>();

	protected SortedSet<CaseWorkflowRecord> workflows = new TreeSet<>();

	protected Case() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public CaseState getState() {
		return state;
	}

	public void setState(CaseState state) {
		this.state = state;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int compareTo(Case c) {
		if (this.id == null || c.id == null)
			return -1;
		return this.id.compareTo(c.id);
	}

	public Set<Action> getActions() {
		return actions;
	}

	public abstract boolean isReach();

	public abstract void addWorkflowRecordWithRemark(String userId, String s, CaseWorkflowRecord.CaseOperation op, String noticeRemark, int i);
}
