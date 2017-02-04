package com.refactor.case5.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public enum CaseStage {
    INVALID {
        public String toString() {
            return "无效";
        }
    },
    DRAFT {
        public String toString() {
            return "草稿";
        }
    },
    AUDIT {
        public String toString() {
            return "审核";
        }
    },
    RUN {
        public String toString() {
            return "上线";
        }
    },
    FINISHED {
        public String toString() {
            return "已结案";
        }
    },
    AUTOCLOSED {
        public String toString() {
            return "自动关闭";
        }
    },
    FORCED_FINISH {
        public String toString() {
            return "强制下线";
        }
    },
    ERROR {
        public String toString() {
            return "状态异常";
        }
    }
}
