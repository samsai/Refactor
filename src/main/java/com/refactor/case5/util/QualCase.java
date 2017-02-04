/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case5.util;

public interface QualCase {
    int getrId();

    String getId();

    QualCaseBasicProfile getQualCaseBasicProfile();

    CaseType getCaseType();

    CaseStage getCaseStage();

    QualBatch getQualBatch();
}
