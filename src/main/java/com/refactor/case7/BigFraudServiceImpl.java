/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case7;

import com.refactor.case7.util.TypeConversionUtil;
import com.refactor.case7.util.UdrEvent;
import com.refactor.case7.util.UdrPlainAttr;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by z672775 on 2016/1/19.
 */
@Service
public class BigFraudServiceImpl {

    private final Logger LOGGER = Logger.getLogger(BigFraudServiceImpl.class);


    private void parseContentToJson(UdrEvent eventDef, Map<String, Object> contentMap, JSONObject resultJson) {
        List<UdrPlainAttr> uiPlainAttrsList = eventDef.getPlainAttrs();

        try {
            for (UdrPlainAttr udrPlainAttr : uiPlainAttrsList) {
                String jsonKey = udrPlainAttr.getAttrDesc();
                Object jsonValue = "";
                for (Map.Entry<String, Object> contentEntry : contentMap.entrySet()) {

                    if (!(udrPlainAttr.getPosition().equals(contentEntry.getKey())))
                        continue;

                    String isSet = String.valueOf(udrPlainAttr.isSet());
                    switch (udrPlainAttr.getDataType()) {
                        case "STRING": {
                            if ("true".equals(isSet.toLowerCase())) {
                                List<String> value = new ArrayList<>();
                                String[] contentValueList = contentEntry.getValue().toString().split(",");
                                for (String contentValue : contentValueList) {
                                    value.add(contentValue);
                                }
                                jsonValue = value;
                            } else {
                                String value = contentEntry.getValue().toString();
                                jsonValue = value;
                            }
                            break;
                        }
                        case "BOOLEAN": {
                            if ("true".equals(isSet.toLowerCase())) {
                                List<Boolean> value = new ArrayList<>();
                                String[] contentValueList = contentEntry.getValue().toString().split(",");
                                for (String contentValue : contentValueList) {
                                    if ("true".equals(contentValue.toLowerCase())) {
                                        value.add(true);
                                    }
                                    if ("false".equals(contentValue.toLowerCase())) {
                                        value.add(false);
                                    }
                                }
                                jsonValue = value;
                            } else {
                                Boolean value = null;
                                if ("true".equals(contentEntry.getValue().toString().toLowerCase())) {
                                    value = true;
                                }
                                if ("false".equals(contentEntry.getValue().toString().toLowerCase())) {
                                    value = false;
                                }
                                jsonValue = value;
                            }
                            break;
                        }
                        case "DATE": {
                            String dateFormat = udrPlainAttr.getDateFormat();
                            if ("true".equals(isSet.toLowerCase())) {
                                List<String> value = new ArrayList<>();
                                String[] contentValueList = contentEntry.getValue().toString().split(",");
                                for (String contentValue : contentValueList) {
                                    try {
                                        value.add(parseDate(dateFormat, contentValue));
                                    } catch (ParseException e) {
                                        LOGGER.error("date parse error: " + contentEntry.getValue(), e);
                                    }
                                }
                                jsonValue = value;
                            } else {
                                String value = null;
                                try {
                                    value = parseDate(dateFormat, contentEntry.getValue().toString());
                                } catch (ParseException e) {
                                    LOGGER.error("date parse error: " + contentEntry.getValue(), e);
                                }
                                jsonValue = value;
                            }
                            break;
                        }
                        case "DOUBLE": {
                            if ("true".equals(isSet.toLowerCase())) {
                                List<Double> value = new ArrayList<>();
                                String[] contentValueList = contentEntry.getValue().toString().split(",");
                                for (String contentValue : contentValueList) {
                                    value.add(Double.valueOf(contentValue));
                                }
                                jsonValue = value;
                            } else {
                                Double value = Double.valueOf(contentEntry.getValue().toString());
                                jsonValue = value;
                            }
                            break;
                        }
                        case "LONG": {
                            if ("true".equals(isSet.toLowerCase())) {
                                List<Long> value = new ArrayList<>();
                                String[] contentValueList = contentEntry.getValue().toString().split(",");
                                for (String contentValue : contentValueList) {
                                    value.add(TypeConversionUtil.parseStringToLong(contentValue));
                                }
                                jsonValue = value;
                            } else {
                                Long value = TypeConversionUtil.parseStringToLong(contentEntry.getValue().toString());
                                jsonValue = value;
                            }
                            break;
                        }
                        default:
                            String value = contentEntry.getValue().toString();
                            jsonValue = value;
                            break;
                    }
                }

                resultJson.put(jsonKey, jsonValue);
            }

        } catch (RuntimeException e) {
            LOGGER.error("parseContentToJson error.", e);
        } catch (Exception e) {
            LOGGER.error("parseContentToJson error.", e);
        }
    }

    private String parseDate(String dateFormat, String value) throws ParseException {

        Date result;

        SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

        try {
            result = df.parse(value);
        } catch (Exception e) {
            if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}")) {
                dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
            } else {
                dateFormat = "yyyy-MM-dd HH:mm:ss";
            }

            df = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            result = df.parse(value);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        return simpleDateFormat.format(result);
    }

}