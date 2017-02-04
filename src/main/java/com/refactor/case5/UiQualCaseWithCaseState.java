/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case5;

import com.refactor.case5.util.*;

public class UiQualCaseWithCaseState {

    private String id;
    private int rId;
    private String name;
    private String caseState;
    private String createDate;
    private String caseType;

    UiQualCaseWithCaseState(){}

    public UiQualCaseWithCaseState(QualCase casee, QualAudit latestQualAudit, QualItAudit latestQualItAudit) {
        this.rId = casee.getrId();
        this.id = casee.getId();
        this.name = casee.getQualCaseBasicProfile().getName();
        this.caseState = getQualCaseState(casee, latestQualAudit, latestQualItAudit);
        this.createDate = casee.getQualCaseBasicProfile().getCreateTime().toString();
        this.caseType = casee.getCaseType().toString();
    }

    String getQualCaseState(QualCase qualCase, QualAudit latestQualAudit, QualItAudit latestQualItAudit) {
        CaseStage caseStage = qualCase.getCaseStage();

        switch (caseStage) {
            case AUTOCLOSED:
                return getCaseState(CaseStage.AUTOCLOSED, null);
            case FINISHED:
                return getCaseState(CaseStage.FINISHED, null);
            case FORCED_FINISH:
                return getCaseState(CaseStage.FORCED_FINISH, null);
            case DRAFT:
                return getCaseState(CaseStage.DRAFT, null);
            case AUDIT:
                if (latestQualAudit == null) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.状态异常);
                }

                AuditState auditState = latestQualAudit.getAuditState();
                if (AuditState.SUBMITTED == auditState) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.待领导审核);
                } else if (AuditState.WITHDRAW == auditState) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.领导审核退回);
                }

                if (latestQualItAudit == null) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.状态异常);
                }

                AuditState auditItState = latestQualItAudit.getAuditState();
                if (AuditState.SUBMITTED == auditItState) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.待IT审核);
                } else if (AuditState.WITHDRAW == auditItState) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.IT审核退回);
                } else {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.状态异常);
                }
            case RUN:
                QualBatch currentQualBatch = qualCase.getQualBatch();
                if (currentQualBatch == null) {
                    return getCaseState(CaseStage.RUN, null);
                }

                QualBatchStage qualBatchStage = currentQualBatch.getQualBatchStage();
                if (QualBatchStage.RUN_FEATURE == qualBatchStage || QualBatchStage.RUN_EVENT == qualBatchStage
                        || QualBatchStage.RUN_POINT == qualBatchStage || QualBatchStage.RUN_BALANCE == qualBatchStage) {
                    return getCaseState(CaseStage.RUN, QualCaseSubState.跑批中);
                } else if (QualBatchStage.REVIEW == qualBatchStage) {
                    return getCaseState(CaseStage.RUN, QualCaseSubState.待检核);
                } else if (QualBatchStage.RUN_SENDOUT == qualBatchStage) {
                    return getCaseState(CaseStage.RUN, QualCaseSubState.待发送);
                } else {
                    return getCaseState(CaseStage.RUN, null);
                }
            default:
                return getCaseState(CaseStage.ERROR, null);
        }
    }

    public UiQualCaseWithCaseState(QualCustomizedCase casee,
                                   QualCustomizedAudit latestQualAudit) {
        this.rId = casee.getrId();
        this.id = casee.getQualCustomizedCaseId();
        this.name = casee.getBasicProfile().getName();
        this.caseState = getQualCustomizedCaseState(casee, latestQualAudit);
        this.createDate = casee.getBasicProfile().getCreateTime().toString();
        this.caseType = casee.getCaseType().toString();
    }

    String getQualCustomizedCaseState(QualCustomizedCase qualCustomizedCase, QualCustomizedAudit latestQualAudit) {
        CaseStage caseStage = qualCustomizedCase.getCaseStage();

        Lifecycle lifeCycle = qualCustomizedCase.getLifeCycle();
        switch (caseStage) {
            case AUTOCLOSED:
                return getCaseState(CaseStage.AUTOCLOSED, null);
            case FINISHED:
                return getCaseState(CaseStage.FINISHED, null);
            case FORCED_FINISH:
                return getCaseState(CaseStage.FORCED_FINISH, null);
            case DRAFT:
                if(null == lifeCycle)
                    return getCaseState(CaseStage.ERROR, null);
                CustomizedLifecycleStage customizedLifeCycleStage = lifeCycle.getLifeCycleStage();
                if(CustomizedLifecycleStage.RUN == customizedLifeCycleStage)
                    return getCaseState(CaseStage.DRAFT, QualCaseSubState.跑批中);
                if(CustomizedLifecycleStage.REVIEW == customizedLifeCycleStage)
                    return getCaseState(CaseStage.DRAFT, QualCaseSubState.待检核);
                return getCaseState(CaseStage.DRAFT, null);
            case AUDIT:
                if (latestQualAudit == null) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.状态异常);
                }

                AuditState auditState = latestQualAudit.getAuditState();
                if (AuditState.SUBMITTED == auditState) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.待领导审核);
                } else if (AuditState.WITHDRAW == auditState) {
                    return getCaseState(CaseStage.AUDIT, QualCaseSubState.领导审核退回);
                }
                return getCaseState(CaseStage.AUDIT, QualCaseSubState.状态异常);
            case RUN:
                if(null == lifeCycle)
                    return getCaseState(CaseStage.ERROR, null);
                if(CustomizedLifecycleStage.RUN_SENDOUT == lifeCycle.getLifeCycleStage())
                    return getCaseState(CaseStage.RUN, QualCaseSubState.待发送);
                //todo 处理dataclean等状态
                return getCaseState(CaseStage.RUN, null);
            default:
                return getCaseState(CaseStage.ERROR, null);
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    private String getCaseState(CaseStage caseStage, QualCaseSubState subState){
        if (subState == null)
            return caseStage.toString();
        return caseStage.toString()+"（"+subState.toString()+"）";
    }

}


