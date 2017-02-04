package com.refactor.case5.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public enum AuditState {
    SUBMITTED{
        public String toString() {
            return "提交审核";
        }
    },PASS{
        public String toString() {
            return "审核通过";
        }
    }
    ,WITHDRAW{
        public String toString() {
            return "审核退回";
        }
    }
}
