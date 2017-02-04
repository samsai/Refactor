package com.refactor.case5.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public enum QualBatchStage {
    RUN_FEATURE {
        public String toString() {
            return "名单跑批阶段";
        }
    }, RUN_EVENT {
        public String toString() {
            return "事件跑批阶段";
        }
    }, RUN_POINT {
        public String toString() {
            return "积分跑批阶段";
        }
    }, RUN_BALANCE {
        public String toString() {
            return "调账跑批阶段";
        }
    }, REVIEW {
        public String toString() {
            return "检核阶段";
        }
    }, RUN_SENDOUT {
        public String toString() {
            return "发送阶段";
        }
    }, FINISH {
        public String toString() {
            return "结案";
        }
    }, RUN_DATACLEAN {
        public String toString() {
            return "数据清理阶段";
        }
    }, INVALID {
        public String toString() {
            return "无效";
        }
    }
}
