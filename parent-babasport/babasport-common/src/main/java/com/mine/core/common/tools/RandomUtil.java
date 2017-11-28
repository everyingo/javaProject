package com.mine.core.common.tools;

import java.util.Random;

public class RandomUtil {

	/**
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		if (min >= max) {
			return 0;
		}
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}
}
