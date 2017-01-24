/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case6;

import com.refactor.case6.util.DataType;
import com.refactor.case6.util.RtagDefinition;
import com.refactor.case6.util.TagDefinition;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HBaseTableRequest {

    private static final String HBASE_SCHEMA = "tag";
    private static final String HBASE_TABLE_SUFFIX = "_V2";
    private static final String CF_NAME = "CF";
    private static final String RTAG_HBASE_TABLE_NAME = "udr_client_rt";

    private String targetTableName;
    private Map<String, TagDefinition> columnToTagDefinitionMap;
    private Map<String, RtagDefinition> columnToRtagDefinitionMap;

    public HBaseTableRequest(String hbaseTableName, List<TagDefinition> tags) {
        this.targetTableName = HBASE_SCHEMA + ":" + hbaseTableName + HBASE_TABLE_SUFFIX;
        this.columnToTagDefinitionMap = tags.stream()
                .collect(Collectors.toMap(tag ->
                        CF_NAME + ":" + tag.getBatchFieldName() + "_" + tag.getTableId(), tag -> tag));
    }

    public HBaseTableRequest(List<RtagDefinition> rtags) {
        this.targetTableName = HBASE_SCHEMA + ":" + RTAG_HBASE_TABLE_NAME + HBASE_TABLE_SUFFIX;
        this.columnToRtagDefinitionMap = rtags.stream()
                .collect(Collectors.toMap(rtag ->
                        CF_NAME + ":" + rtag.getId(), rtag -> rtag));
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public String[] getTargetColumns() {
        if (columnToTagDefinitionMap != null)
            return columnToTagDefinitionMap.keySet().stream().toArray(String[]::new);
        else
            return columnToRtagDefinitionMap.keySet().stream().toArray(String[]::new);
    }

    public DataType getDataTypeByColumn(String column) {
        if (columnToTagDefinitionMap != null)
            return columnToTagDefinitionMap.get(column).getDataType();
        else
            return columnToRtagDefinitionMap.get(column).getDataType();
    }
    public String getDateFormatByColumn(String column) {
        if (columnToTagDefinitionMap != null)
            return columnToTagDefinitionMap.get(column).getDateFormat();
        else
            return columnToRtagDefinitionMap.get(column).getDateFormat();
    }
    public String getIdByColumn(String column) {
        if (columnToTagDefinitionMap != null)
            return columnToTagDefinitionMap.get(column).getId();
        else
            return columnToRtagDefinitionMap.get(column).getId();
    }

    public String getVersion() {
        return targetTableName.replace(HBASE_TABLE_SUFFIX,"")
                .replaceAll(".*_","");
    }

}
