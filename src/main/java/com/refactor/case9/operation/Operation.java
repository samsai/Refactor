/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case9.operation;


import com.refactor.case9.util.UdrPlainAttr;
import com.refactor.case9.util.UdrTag;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yanfei on 2/24/16.
 */
public abstract class Operation<T> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void operate(JSONObject resultJson,
                        String contentValue, UdrPlainAttr udrPlainAttr) {

        String key = "A" + udrPlainAttr.getId();
        String isSet = String.valueOf(udrPlainAttr.isSet()).toLowerCase();
        String dateFormat = udrPlainAttr.getDateFormat() == null ?
                StringUtils.EMPTY :
                udrPlainAttr.getDateFormat().trim();
        String attrName = udrPlainAttr.getName();

        if (isSet.equals("true")) {
            List<T> values = new ArrayList<>();
            String[] contentValueList = contentValue.split(",");

            for (String value : contentValueList) {
                values.add(getRealValue(value.trim(), dateFormat));
            }

            resultJson.put(key, values);
        } else {
            T realValue = getRealValue(contentValue, dateFormat);
            resultJson.put(key, realValue);

            // Date -> Long in HBase
            if ("事件时间".equals(attrName) && realValue instanceof Long
                    && StringUtils.isNotBlank(dateFormat)) {
                resultJson.put("messageTimestamp", realValue.toString());
            }
        }
    }

    public void operateForTag(Map<String, Object> tagValueMap,
                              UdrTag udrTag,
                              Object value) {

        String id = udrTag.getId();
        String dateFormat = udrTag.getDateFormat() == null ?
                "" : udrTag.getDateFormat().trim();
        String isSet = String.valueOf(udrTag.isSet()).toLowerCase();

        if ("true".equals(isSet)) {
            List<T> result = new ArrayList<>();
            String[] valueList = value.toString().split(",");
            for (String tagValue : valueList) {
                result.add(getRealValue(tagValue.trim(), dateFormat));
            }
            tagValueMap.put(id, result);
        } else {
            T realValue = getRealValue(value.toString(), dateFormat);
            tagValueMap.put(id, realValue);

            if (realValue instanceof Long && dateFormat.length() > 0) {
                tagValueMap.put(id + "_DATE", simpleDateFormat.format(new Date((Long) realValue)));
            }
        }
    }

    public abstract T getRealValue(String value, String dateFormat);
}
