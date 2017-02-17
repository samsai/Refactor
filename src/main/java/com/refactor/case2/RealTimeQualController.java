package com.refactor.case2;

import com.refactor.case2.util.AuditForm;
import com.refactor.case2.util.CasesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;

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
        String userId = form.getUserId();
        String remark = form.getNoticeRemark();
        if (form.isAuditPass()) {
            remarkCase.passItAudit(userId, remark);
        }
        else {
            remarkCase.withdrawItAudit(userId, remark);
        }
        casesService.updateCaseCode(remarkCase);
    }

}
