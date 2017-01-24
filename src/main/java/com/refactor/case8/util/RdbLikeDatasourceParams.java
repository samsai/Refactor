/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case8.util;

public interface RdbLikeDatasourceParams extends DataSourceParams{

    String getServerIp();

    String getServerPort();

    String getPassword();

    String getUsername();

    String getDatabaseName();

}
