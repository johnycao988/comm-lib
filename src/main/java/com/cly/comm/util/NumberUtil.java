package com.cly.comm.util;

import com.cly.logging.CLYLoggerManager;

public class NumberUtil {

	private NumberUtil() {

	}

	public static long parserLong(String mfs, long defaultValue) {

		try {
			if (mfs == null)
				return defaultValue;
			else
				return Long.parseLong(mfs);
		} catch (Exception e) {

			CLYLoggerManager.getRootLogger().errorException(e);

			return defaultValue;
		}

	}

}
