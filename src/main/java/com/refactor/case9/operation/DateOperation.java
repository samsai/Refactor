/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case9.operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by yanfei on 2/25/16.
 */
public class DateOperation extends Operation<Long> {
    @Override
    public Long getRealValue(String value, String dateFormat) {
        return parseDate(dateFormat, value);
    }

    private Long parseDate(String dateFormat, String value) {

        Long result;

        SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

        try {
            result = df.parse(value).getTime();
        } catch (Exception e) {
            if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}")) {
                dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
            } else {
                dateFormat = "yyyy-MM-dd HH:mm:ss";
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            try {
                result = simpleDateFormat.parse(value).getTime();
            } catch (ParseException e1) {
                result = 0L;
            }
        }

        return result;
    }
}
