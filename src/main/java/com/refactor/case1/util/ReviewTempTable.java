/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case1.util;

import java.util.Set;

public interface ReviewTempTable {
    String getReviewTableName();

    String getTableName();

    String getIdTypeColumn();

    Set<String> getChannelColumns();

    String generateSql();
}
