/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case8;

import com.refactor.case8.util.DataSource;
import com.refactor.case8.util.RdbLikeDatasourceParams;

public class RdbLikeDataSource extends DataSource {
    private String databaseName;

    public String databaseName() {
        return databaseName;
    }

    @Override
    public String category() {
        return "RDB";
    }

    public void update(RdbLikeDatasourceParams params) {
        setParams(params);
    }

    private void setParams(RdbLikeDatasourceParams params) {
        this.dataSourceType = params.getDataSourceType();
        this.name = params.getName();
        this.serverIp = params.getServerIp();
        this.serverPort = params.getServerPort();
        this.username = params.getUsername();
        this.password = params.getPassword();
        this.isProduct = params.getIsProduct();
        this.note = params.getNote();
        this.databaseName = params.getDatabaseName();
    }

    public RdbLikeDataSource() {
    }

    public String jdbcType() {
        if (dataSourceType() == DataSourceType.DB2) {
            return "com.ibm.db2.jcc.DB2Driver";
        } else if (dataSourceType() == DataSourceType.SQL_SERVER) {
            return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        } else if (dataSourceType() == DataSourceType.MYSQL) {
            return "com.mysql.jdbc.Driver";
        } else if (dataSourceType() == DataSourceType.ORACLE) {
            return "oracle.jdbc.driver.OralceDriver";
        } else
            throw new IllegalArgumentException();
    }

    public String jdbcString() {
        String dbhost = serverIp();
        String dbport = serverPort();
        String dbname = databaseName();
        StringBuilder sb = new StringBuilder();
        if (dataSourceType() == DataSourceType.DB2) {
            sb.append("jdbc:db2://").append(dbhost)
                    .append(":").append(dbport)
                    .append("/").append(dbname);
        } else if (dataSourceType() == DataSourceType.SQL_SERVER) {
            sb.append("jdbc:sqlserver://").append(dbhost)
                    .append(":").append(dbport)
                    .append(";DatabaseName=").append(dbname);
        } else if (dataSourceType() == DataSourceType.MYSQL) {
            sb.append("jdbc:mysql://").append(dbhost)
                    .append(":").append(dbport)
                    .append("/").append(dbname);
        } else if (dataSourceType() == DataSourceType.ORACLE) {
            sb.append("jdbc:oracle:thin:@").append(dbhost)
                    .append(":").append(dbport)
                    .append(":").append(dbname);
        } else
            throw new IllegalArgumentException();
        return sb.toString();
    }
}
