package com.refactor.case4.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
public enum CaseType {
    QUAL {
        public String toString() {
            return "达标";
        }
    },
    SINGLE_REACH {
        public String toString() {
            return "单次触达";
        }
    },
    MULTIPLE_REACH {
        public String toString() {
            return "多次触达";
        }
    },
    REGION_SINGLE_REACH {
        public String toString() {
            return "区域单次触达";
        }
    }
}
