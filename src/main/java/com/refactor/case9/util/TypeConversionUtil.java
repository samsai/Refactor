/**
*@Company: China Merchants Bank
*@Copyright: Copyright 2015 China Merchants Bank. All rights reserved.
*/

package com.refactor.case9.util;

import org.apache.log4j.Logger;

public class TypeConversionUtil {
	private static final Logger LOGGER = Logger.getLogger(TypeConversionUtil.class);

	final private static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	/**
	 * Parse String to Long
	 * @param str
	 * @return
	 */
	public static Long parseStringToLong(String str) {
		Long result = null;
		
		try {
			result = Long.valueOf((long)(Double.parseDouble(str)));
		} catch (Exception e) {
			LOGGER.error("parse string to Long error.", e);
		}
		
		return result;
	}
	
	/**
	 * Parse String to Integer
	 * @param str
	 * @return
	 */
	public static Integer parseStringToInteger(String str) {
		Integer result = null;
		
		try {
			result = Integer.valueOf((int)(Double.parseDouble(str)));
		} catch (Exception e) {
			LOGGER.error("parse string to Integer error.", e);
		}
		
		return result;
	}

	/**
	 * Convert a byte array to a Hex String.
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {

		if (null == bytes || bytes.length == 0)
			return "";

		char[] hexChars = new char[bytes.length * 2];
		int v;
		for ( int j = 0; j < bytes.length; j++ ) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
