/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case5.util;

public enum QualCaseSubState {
    待领导审核 ,
    领导审核退回 ,
    待IT审核 ,
    IT审核退回 ,
    状态异常,
    跑批中 ,
    待检核 ,
    待放行 ,
    放行通过 ,
    待发送 ,
}
