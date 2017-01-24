/**
 * @company China Merchants Bank
 * @copyright Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case2.util;

public class ActionConstant {

	/**
	 * 动作状态
	 * @author z72144
	 *
	 */	
	public enum ActionState{
		READY_TO_CHECK("0"),
		HAS_CHECKED("1"),
		READY_TO_RUN_BATCH("2");
		ActionState(String state){
			this.state = state;
		}
		
		private String state;

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
	}
	
}
