package com.refactor.case4.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public enum CaseStage {
    INVALID, DRAFT, AUDIT, RUN, FINISHED {
        public String toString() {
            return "已结案";
        }
    },AUTOCLOSED{
        public String toString() {
            return "自动关闭";
        }
    }
}
