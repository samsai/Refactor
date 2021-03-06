/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case1;


import com.refactor.case1.util.ChannelId;
import com.refactor.case1.util.Constant;
import com.refactor.case1.util.ReviewTempTable;
import com.refactor.case1.util.StringUtil;

import java.util.Set;
import java.util.TreeSet;

public class GenerateReviewTable {
    private String tableName;
    private String sourceTableName;
    private String idTypeColumn;
    private Set<String> channelColumns;
    private String reviewTempTableSql;

    public GenerateReviewTable(ReviewTempTable reviewTempTable) {
        this.tableName = reviewTempTable.getReviewTableName();
        this.sourceTableName = reviewTempTable.getTableName();
        this.idTypeColumn = reviewTempTable.getIdTypeColumn();
        this.channelColumns = reviewTempTable.getChannelColumns();
        this.reviewTempTableSql = reviewTempTable.generateSql();
    }

    //按配置动态拼出一个sql语句
    public String generateSql() {
        String channelColumnClauseTemp = StringUtil.flat(channelColumns, ",", "", "");
        String channelColumnClause;
        Set<String> columns = new TreeSet<>();
        for (String str : channelColumns) {
            if (ChannelId.CLT_BUS_EML_ADR.toString().equals(str)) {
                columns.add(ChannelId.CLT_EML_ADR.toString());
            } else {
                columns.add(str);
            }
        }
        channelColumnClause = StringUtil.flat(columns, ",", "", "");

        String channelColumnsReviewTemp = "";
        String channelColumnsReview = "";
        if (!channelColumns.isEmpty()) {
            channelColumnsReviewTemp = channelColumnClauseTemp +
                    (channelColumns.contains(idTypeColumn) ? "" : ("," + idTypeColumn)) + ",batch_id";
            channelColumnsReview = channelColumnClause +
                    (channelColumns.contains(idTypeColumn) ? "" : ("," + idTypeColumn)) + ",batch_id";
        } else {
            channelColumnsReviewTemp = idTypeColumn + ",batch_id";
            channelColumnsReview = idTypeColumn + ",batch_id";
        }

        StringBuffer vsql = new StringBuffer();
        vsql.append("insert into ").append(Constant.DB_SCHEMA).append(".").append(tableName)
                .append(" (").append(channelColumnsReview).append(")")
                .append(" select distinct ").append(channelColumnsReviewTemp.replace(Constant.CITYNAME_COLUMN, "isnull(" + Constant.CITYNAME_COLUMN + ",'未知城市') as " + Constant.CITYNAME_COLUMN))
                .append(" from ").append(Constant.DB_SCHEMA).append(".").append(sourceTableName).append(";\n");

        return reviewTempTableSql + vsql.toString();
    }
}
