package com.refactor.case4;

import com.refactor.case4.util.*;


public abstract class ReachCase {
    private String id;

    private CaseLifeCycle currentCaseLifeCycle;

    private Content content;

    private NameListSetting nameListSetting;

    protected CaseStage caseStage;

    private Audit currentAudit;

    private FinishCaseRecord currentFinishCaseRecord;

    public Audit getCurrentAudit() {
        return currentAudit;
    }

    public void setCurrentAudit(Audit currentAudit) {
        this.currentAudit = currentAudit;
    }

    public CaseLifeCycle getCurrentCaseLifeCycle() {
        return currentCaseLifeCycle;
    }

    public void setCurrentCaseLifeCycle(CaseLifeCycle currentCaseLifeCycle) {
        this.currentCaseLifeCycle = currentCaseLifeCycle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FinishCaseRecord getCurrentFinishCaseRecord() {
        return currentFinishCaseRecord;
    }

    public void setCurrentFinishCaseRecord(FinishCaseRecord currentFinishCaseRecord) {
        this.currentFinishCaseRecord = currentFinishCaseRecord;
    }

    //列表页单次、多次的状态显示
    //public abstract String parseCaseSateForUiCase();
    //单次、多次配置页面loading使用
    public CaseState parseCaseStateByStatusCode() {
        char[] code = this.getStatusCode().toCharArray();
        CaseLifeCycle caseLifeCycle = this.currentCaseLifeCycle;
        if (CaseStage.AUTOCLOSED.equals(caseStage))
            return CaseState.自动关闭;
        if (code[7] == '1')
            return CaseState.已结案;
        if (code[6] == '1' && caseLifeCycle != null) {
            if (caseLifeCycle.getSendOutLifeCycleState().equals(LifeCycleState.SUBMITTED))
                return CaseState.同步跑批中;
            if (caseLifeCycle.getSendOutLifeCycleState().equals(LifeCycleState.SUCCESS))
                return CaseState.同步跑批成功;
            if (caseLifeCycle.getSendOutLifeCycleState().equals(LifeCycleState.FAILURE))
                return CaseState.同步跑批失败;
        }
        if (code[6] == '2')
            return CaseState.渠道退回;

        if (code[5] == '2')
            return CaseState.审核不通过;

        if (code[5] == '1' && caseLifeCycle != null && caseLifeCycle.getRunDocLifeCycleState() != null) {
            if (caseLifeCycle.getRunDocLifeCycleState().equals(LifeCycleState.SUBMITTED))
                return CaseState.文案跑批中;
            if (caseLifeCycle.getRunDocLifeCycleState().equals(LifeCycleState.SUCCESS))
                return CaseState.待放行;
            if (caseLifeCycle.getRunDocLifeCycleState().equals(LifeCycleState.FAILURE))
                return CaseState.文案跑批失败;
        }
        if (code[4] == '1')
            return CaseState.待审核;

        if (code[2] == '1')
            return CaseState.名单已检核;

        if (code[1] == '1' && caseLifeCycle != null && caseLifeCycle.getRunNamelistLifeCycleState() != null) {
            if (caseLifeCycle.getRunNamelistLifeCycleState().equals(LifeCycleState.SUBMITTED))
                return CaseState.名单跑批中;
            if (caseLifeCycle.getRunNamelistLifeCycleState().equals(LifeCycleState.SUCCESS))
                return CaseState.名单待检核;
            if (caseLifeCycle.getRunNamelistLifeCycleState().equals(LifeCycleState.FAILURE))
                return CaseState.名单跑批失败;
        }
        if (code[0] == '0' || code[1] == '0')
            return CaseState.草稿;

        return CaseState.草稿;
    }


    public abstract CaseType getCaseType();

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getStatusCode() {
        return getNaviCode();
    }

    //活动的导航栏
    private String getNaviCode() {
        char[] naviCode = {'1', '0', '0', '0', '0', '0', '0', '0'};
        CaseLifeCycle caseLifeCycle = this.getCurrentCaseLifeCycle();
        Audit audit = this.currentAudit;
        FinishCaseRecord finish = this.currentFinishCaseRecord;
        Release release = caseLifeCycle == null ? null : caseLifeCycle.getCurrentRelease();
        if (nameListSetting != null && this.nameListSetting.isConfigured()) {
            naviCode[1] = '1';
        }
        if (this.getCaseType().equals(CaseType.SINGLE_REACH) && caseLifeCycle != null && caseLifeCycle.getReviewLifeCycleState() != null && caseLifeCycle.getReviewLifeCycleState().equals(LifeCycleState.SUCCESS)) {
            naviCode[2] = '1';
        }
        if (this.getCaseType().equals(CaseType.MULTIPLE_REACH) && nameListSetting != null && nameListSetting.isConfigured()) {
            naviCode[2] = '1';
        }
        if (this.getCaseType().equals(CaseType.REGION_SINGLE_REACH) && nameListSetting != null && nameListSetting.isConfigured()) {
            naviCode[2] = '1';
        }
        if (this.content != null && this.content.isConfiged()) {
            naviCode[3] = '1';
        }
        if (audit != null) {
            naviCode[4] = '1';
        }
        if (audit != null && audit.getAuditState().equals(AuditState.PASS)) {
            naviCode[5] = '1';
        }
        if (audit != null && audit.getAuditState().equals(AuditState.WITHDRAW)) {
            naviCode[5] = '2';
        }
        if (release != null && release.getReleaseState() != null && release.getReleaseState().equals(ReleaseState.PASS)) {
            naviCode[6] = '1';
        }
        if (release != null && release.getReleaseState() != null && release.getReleaseState().equals(ReleaseState.WITHDRAW)) {
            naviCode[6] = '2';
        }
        if (finish != null)
            naviCode[7] = '1';
        return String.valueOf(naviCode);
    }

    public CaseStage getCaseStage() {
        return caseStage;
    }

    public void setCaseStage(CaseStage caseStage) {
        this.caseStage = caseStage;
    }

}
