package com.refactor.case4;


import com.refactor.case4.util.Audit;
import com.refactor.case4.util.CaseLifeCycle;
import com.refactor.case4.util.Release;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public class UINavigation {
    private String caseId;
    private String createDate;
    private String navigationNo;
    private String nameListBatchState;
    private String docSendBatchState;
    private String syncBatchState;
    private Audit audit;
    private Release release;
    private String caseType;

    public UINavigation(ReachCase casee){
        this.caseId = casee.getId();
        this.audit = casee.getCurrentAudit();
        this.navigationNo = casee.getStatusCode();
        this.caseType = casee.getCaseType().toString();
        CaseLifeCycle caseLifeCycle = casee.getCurrentCaseLifeCycle();
        if(caseLifeCycle!=null){
            this.docSendBatchState = caseLifeCycle.getRunDocLifeCycleState()==null?"0":caseLifeCycle.getRunDocLifeCycleState().toString();
            this.nameListBatchState = caseLifeCycle.getRunNamelistLifeCycleState()==null?"0":caseLifeCycle.getRunNamelistLifeCycleState().toString();
            this.syncBatchState = caseLifeCycle.getSendOutLifeCycleState()==null?"0":caseLifeCycle.getSendOutLifeCycleState().toString();
        }else{
            this.nameListBatchState="0";
            this.docSendBatchState="0";
            this.syncBatchState="0";
        }
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNavigationNo() {
        return navigationNo;
    }

    public void setNavigationNo(String navigationNo) {
        this.navigationNo = navigationNo;
    }

    public String getNameListBatchState() {
        return nameListBatchState;
    }

    public void setNameListBatchState(String nameListBatchState) {
        this.nameListBatchState = nameListBatchState;
    }

    public String getDocSendBatchState() {
        return docSendBatchState;
    }

    public void setDocSendBatchState(String docSendBatchState) {
        this.docSendBatchState = docSendBatchState;
    }

    public String getSyncBatchState() {
        return syncBatchState;
    }

    public void setSyncBatchState(String syncBatchState) {
        this.syncBatchState = syncBatchState;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

}
