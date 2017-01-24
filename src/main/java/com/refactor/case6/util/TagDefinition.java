/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case6.util;

public interface TagDefinition {
    String getBatchFieldName();

    String getTableId();

    DataType getDataType();

    String getDateFormat();

    String getId();
}
