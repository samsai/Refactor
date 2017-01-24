/*
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2016 China Merchants Bank. All rights reserved.
 */

package com.refactor.case9.operation;

/**
 * Created by yanfei on 2/25/16.
 */
public class DoubleOperation extends Operation<Double> {
    @Override
    public Double getRealValue(String value, String dateFormat) {
        return Double.valueOf(value);
    }
}
