/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case3.util;

import java.util.List;

public interface ConditionNameList extends NameList{
    List<Condition> getConditions();

    String getLogic_exp();
}
