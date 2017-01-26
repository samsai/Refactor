/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case2.util;

public interface AuditForm {
    boolean isAuditPass();

    long getCaseId();

    String getUserId();

    String getNoticeRemark();
}
