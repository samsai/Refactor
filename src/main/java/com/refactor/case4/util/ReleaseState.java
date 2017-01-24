package com.refactor.case4.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public enum ReleaseState {
    SUBMITTED{
        public String toString() {
            return "提交放行";
        }
    },PASS{
        public String toString() {
            return "放行通过";
        }
    }
    ,WITHDRAW{
        public String toString() {
            return "放行退回";
        }
    }
}
