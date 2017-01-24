/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case9.service;

import com.refactor.case9.operation.*;
import com.refactor.case9.util.UdrEvent;
import com.refactor.case9.util.UdrPlainAttr;
import com.refactor.case9.util.UdrTag;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by z672783 on 2015/10/13.
 */
@Service
public class TagServiceImpl {
    private final Logger LOGGER = Logger.getLogger(TagServiceImpl.class);

    private Operation<Boolean> booleanOperation = new BoolOperation();
    private Operation<Long> dateOperation = new DateOperation();
    private Operation<Long> longOperation = new LongOperation();
    private Operation<Double> doubleOperation = new DoubleOperation();
    private Operation<String> stringOperation = new StringOperation();

    public void parseContentToJson(UdrEvent eventDef, Map<String, Object> contentMap, JSONObject resultJson) {
        List<UdrPlainAttr> uiPlainAttrsList = eventDef.getPlainAttrs();

        try {
            for (Map.Entry<String, Object> contentEntry : contentMap.entrySet()) {
                for (UdrPlainAttr udrPlainAttr : uiPlainAttrsList) {
                    if (!(udrPlainAttr.getPosition().equals(contentEntry.getKey())))
                        continue;

                    String key = "A" + udrPlainAttr.getId();
                    switch (udrPlainAttr.getDataType()) {
                        case "STRING":
                            stringOperation.operate(
                                    resultJson,
                                    contentEntry.getValue().toString(),
                                    udrPlainAttr);
                            break;
                        case "BOOLEAN":
                            booleanOperation.operate(
                                    resultJson,
                                    contentEntry.getValue().toString(),
                                    udrPlainAttr);
                            break;
                        case "DATE":
                            dateOperation.operate(
                                    resultJson,
                                    contentEntry.getValue().toString(),
                                    udrPlainAttr);
                            break;
                        case "DOUBLE":
                            doubleOperation.operate(
                                    resultJson,
                                    contentEntry.getValue().toString(),
                                    udrPlainAttr);
                            break;
                        case "LONG":
                            longOperation.operate(
                                    resultJson,
                                    contentEntry.getValue().toString(),
                                    udrPlainAttr);
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("parseContentToJson error.", e);
        }
    }

    private void addTagValues(Map<String, Object> tagValueMap,
                              UdrTag udrTag,
                              Object value) {

        String dataType = udrTag.getDataType();
        switch (dataType) {
            case "STRING":
                stringOperation.operateForTag(tagValueMap, udrTag, value);
                break;
            case "BOOLEAN":
                booleanOperation.operateForTag(tagValueMap, udrTag, value);
                break;
            case "DOUBLE":
                doubleOperation.operateForTag(tagValueMap, udrTag, value);
                break;
            case "LONG":
                longOperation.operateForTag(tagValueMap, udrTag, value);
                break;
            case "DATE":
                dateOperation.operateForTag(tagValueMap, udrTag, value);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

}