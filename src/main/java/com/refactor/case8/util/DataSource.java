package com.refactor.case8.util;

public abstract class DataSource {
    public enum DataSourceType {
        SQL_SERVER, ORACLE, DB2, MYSQL,
        FTP, SFTP, HIVE, HBASE, HDFS;
    }

    protected DataSourceId datasourceId = new DataSourceId();

    protected String name;

    protected String serverIp;

    protected String serverPort;

    protected String password;

    protected String username;

    protected  Boolean isProduct = false;

    protected String note;

    protected DataSourceType dataSourceType;

    public DataSource() {
    }
    public DataSourceId datasourceId() {return datasourceId;}
    public String name() {
        return name;
    }

    public String serverIp() {
        return serverIp;
    }

    public String serverPort() {
        return serverPort;
    }

    public String username() {
        return username;
    }
    public String password() {
        return password;
    }

    public Boolean isProduct() {
        return isProduct;
    }

    public String note() {
        return note;
    }

    public DataSourceType dataSourceType() {
        return dataSourceType;
    }

    public abstract String category();
}




