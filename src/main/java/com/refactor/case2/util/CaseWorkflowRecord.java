/**
 * @company China Merchants Bank
 * @copyright Copyright 2015 China Merchants Bank. All rights reserved.
 */
package com.refactor.case2.util;

public class CaseWorkflowRecord {

	public enum CaseOperation {
		提交审核, 领导审核, 提交IT审核, IT审核, 提交验证, 上传通知平台, 结案,提交检核
	}

	public enum CaseOperationResult {
		PASS, FAIL, WAITING
	}

}
