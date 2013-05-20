package haw.ai.server.common;

import java.util.Date;

public class DateUtil {
	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	public static Date daysFromNow(int i) {
		Date now = now();
		return new Date(now.getDate() + i);
	}
}
