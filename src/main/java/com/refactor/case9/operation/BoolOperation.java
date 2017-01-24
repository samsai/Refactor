/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case9.operation;

/**
 * Created by yanfei on 2/24/16.
 */
public class BoolOperation extends Operation<Boolean> {
    @Override
    public Boolean getRealValue(String value, String dateFormat) {
        if (value.toLowerCase().equals("true")) {
            return true;
        }

        return false;
    }
}
