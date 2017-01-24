/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case9.operation;

/**
 * Created by yanfei on 2/24/16.
 */
public class StringOperation extends Operation<String> {

    @Override
    public String getRealValue(String value, String dateFormat) {
        return value;
    }
}
