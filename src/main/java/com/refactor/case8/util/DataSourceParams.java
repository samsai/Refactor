package com.refactor.case8.util;

/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */
public interface DataSourceParams {
    String getId();

    String getName();

    Boolean getIsProduct();

    String getNote();

    DataSource.DataSourceType getDataSourceType();

    String getCategory();
}
