package haw.ai.common;

public class Log {
	public static void log(String className, String... messages) {
		String output = className + ":";
		for (String string : messages) {
			output += (" " + string);
		}
		System.out.println(output);
	}
}
