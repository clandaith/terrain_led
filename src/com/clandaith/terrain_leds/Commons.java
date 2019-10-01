package com.clandaith.terrain_leds;

import java.util.Date;
import java.util.Random;

public class Commons {
	private static final Random random = new Random(new Date().getTime());

	private Commons() {
		// forces to only use static methods
	}

	public static long getRandomSecond() {
		return getRandomInt(10) * 1000l;
	}

	public static long getRandomSecond(int bound) {
		return getRandomInt(bound) * 1000l;
	}

	public static int getRandomInt() {
		return getRandomInt(10);
	}

	public static int getRandomInt(int bound) {
		return random.nextInt(bound + 1);
	}

}
