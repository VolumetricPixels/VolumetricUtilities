package com.volumetricpixels.utils;

/**
 * @author VolumetricPixels
 */
public class Utilities {
	public static boolean classExists(String clazz) {
		try {
			Class.forName(clazz);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClass(String clazz) {
		try {
			return (Class<T>) Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public static String bytes2Str(byte[] bytes) {
		return new String(bytes);
	}

	public static boolean intArrayContains(int[] removed, int index) {
		for (int i : removed) {
			if (i == index) {
				return true;
			}
		}
		return false;
	}

	public static String toString(String[] split) {
		return toString(split, 0);
	}

	public static String toString(String[] split, int start) {
		return toString(split, start, ' ');
	}

	public static String toString(String[] split, int start, char splitC) {
		StringBuilder builder = new StringBuilder();
		for (int i = start; i < split.length; i++) {
			builder.append(split[i]).append(splitC);
		}
		builder.setLength(builder.length() - 1);
		return builder.toString();
	}

	public static void invokeLater(Runnable runnable) {
		InvokeLaterThread thread = new InvokeLaterThread();
		thread.runnable = runnable;
		thread.start();
	}

	private static final class InvokeLaterThread extends Thread {
		private Runnable runnable;

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				runnable.run();
			} catch (Exception e) {
			}
		}
	}
}
