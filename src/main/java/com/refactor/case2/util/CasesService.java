/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case2.util;

import com.refactor.case2.Case;

public interface CasesService {
    void updateCaseCode(Case remarkCase);

    Case queryById(Long aLong);
}
