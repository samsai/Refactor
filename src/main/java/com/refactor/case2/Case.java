/**
 * @company China Merchants Bank
 * @copyright Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case2;

import com.refactor.case2.util.Action;
import com.refactor.case2.util.ActionConstant;
import com.refactor.case2.util.CaseState;
import com.refactor.case2.util.CaseWorkflowRecord;

import java.util.*;

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

	public void withdrawItAudit(String userId, String remark) {
		this.setState(CaseState.IT审核退回);
		CaseWorkflowRecord.CaseOperation op = CaseWorkflowRecord.CaseOperation.IT审核;
		this.addWorkflowRecordWithRemark(userId, "", op, remark, 2);
	}

    public void passItAudit(String userId, String remark) {
        this.setState(CaseState.上线运行);
        CaseWorkflowRecord.CaseOperation op = CaseWorkflowRecord.CaseOperation.IT审核;
        Set<Action> actions = this.getActions();
        Iterator<Action> it = actions.iterator();
        Action action = it.next();
        if (this.isReach()) {
            action.setActionState(ActionConstant.ActionState.READY_TO_RUN_BATCH.getState());
        } else {
            action.setActionState(ActionConstant.ActionState.HAS_CHECKED.getState());
        }
        this.addWorkflowRecordWithRemark(userId, "", op, remark, 1);
    }
}
