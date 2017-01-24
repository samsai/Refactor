/**
 * @Company: China Merchants Bank
 * @Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case4.util;

public interface CaseLifeCycle {
    LifeCycleState getSendOutLifeCycleState();

    LifeCycleState getRunDocLifeCycleState();

    LifeCycleState getRunNamelistLifeCycleState();

    Release getCurrentRelease();

    LifeCycleState getReviewLifeCycleState();
}
