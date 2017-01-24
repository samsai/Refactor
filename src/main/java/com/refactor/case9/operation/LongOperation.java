/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case9.operation;


import com.refactor.case9.util.TypeConversionUtil;

/**
 * Created by yanfei on 2/25/16.
 */
public class LongOperation extends Operation<Long> {
    @Override
    public Long getRealValue(String value, String dateFormat) {
        return TypeConversionUtil.parseStringToLong(value);
    }
}
