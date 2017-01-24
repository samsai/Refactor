package com.refactor.case4.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public enum CaseState {
    草稿,
    名单跑批中 {
        public String toString() {
            return "草稿（客群生成中）";
        }
    },
    名单跑批失败 {
        public String toString() {
            return "草稿（客群生成失败）";
        }
    },
    名单待检核 {
        public String toString() {
            return "草稿（待检核）";
        }
    },
    名单已检核 {
        public String toString() {
            return "草稿（已检核）";
        }
    },
    待审核,
    文案跑批中 {
        public String toString() {
            return "审核通过（文案生成中）";
        }
    },
    文案跑批失败 {
        public String toString() {
            return "审核通过（文案生成失败）";
        }
    },
    审核不通过,
    待放行,
    同步跑批中 {
         public String toString() {
            return "验证通过";
        }
    },
    同步跑批失败 {
        public String toString() {
            return "上传失败";
        }
    },
    同步跑批成功 {
        public String toString() {
            return "上传成功";
        }
    },
    渠道退回, 已删除,已结案,自动关闭
}
