package mg.majungaville.taximototricycle.util;

public final class StringUtil {
	private StringUtil() {
		throw new AssertionError();
	}

	public static boolean isEmpty(final String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNotEmpty(final String str) {
		return !isEmpty(str);
	}
}
