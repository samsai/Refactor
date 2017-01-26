package com.refactor.case2;

import com.refactor.case2.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by armysheng on 2015/11/20.
 * 实时达标控制器
 */
@Controller("realTimeQualController")
@RequestMapping("/ams/cases/realtimeQual/")
public class RealTimeQualController {

    @Resource
    private CasesService casesService;


    @RequestMapping(value = "/it/auditor", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //营销活动的IT部门审核，记录审核结果和流水，修改活动和活动下Action对象的状态
    public void itAudit(@RequestBody AuditForm form) {
        Case remarkCase = casesService.queryById(Long.valueOf(form.getCaseId()));
        if (form.isAuditPass()) {
            remarkCase.setState(CaseState.上线运行);
            CaseWorkflowRecord.CaseOperation op = CaseWorkflowRecord.CaseOperation.IT审核;
            Set<Action> actions = remarkCase.getActions();
            Iterator<Action> it = actions.iterator();
            Action action = it.next();
            if (remarkCase.isReach()) {
                action.setActionState(ActionConstant.ActionState.READY_TO_RUN_BATCH.getState());
            } else {
                action.setActionState(ActionConstant.ActionState.HAS_CHECKED.getState());
            }
            remarkCase.addWorkflowRecordWithRemark(form.getUserId(), "", op, form.getNoticeRemark(), 1);
        }
        else {
            remarkCase.setState(CaseState.IT审核退回);
            CaseWorkflowRecord.CaseOperation op = CaseWorkflowRecord.CaseOperation.IT审核;
            remarkCase.addWorkflowRecordWithRemark(form.getUserId(), "", op, form.getNoticeRemark(), 2);
        }
        casesService.updateCaseCode(remarkCase);
    }

}
