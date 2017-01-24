/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case3.util;

public interface Condition {

    public enum OperationType {
        WHOLE, FUZZY, FRONT, LATTER, BETWEEN,
        MORE, LESS, PER, IN;
    }
    String getOpValue();

    String getTagId();

    String getSymbol();

    OperationType getOpType();
}
