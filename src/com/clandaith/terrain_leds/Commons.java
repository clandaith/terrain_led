package com.clandaith.terrain_leds;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Commons {
	private static final Random random = new Random(new Date().getTime());

	private static Map<Integer, Pin> pins = new HashMap<>();
	static {
		pins.put(0, RaspiPin.GPIO_00);
		pins.put(1, RaspiPin.GPIO_01);
		pins.put(2, RaspiPin.GPIO_02);
		pins.put(3, RaspiPin.GPIO_03);
		pins.put(4, RaspiPin.GPIO_04);
		pins.put(5, RaspiPin.GPIO_05);
		pins.put(6, RaspiPin.GPIO_06);
		pins.put(7, RaspiPin.GPIO_07);
		pins.put(8, RaspiPin.GPIO_08);
		pins.put(9, RaspiPin.GPIO_09);

		pins.put(10, RaspiPin.GPIO_10);
		pins.put(11, RaspiPin.GPIO_11);
		pins.put(12, RaspiPin.GPIO_12);
		pins.put(13, RaspiPin.GPIO_13);
		pins.put(14, RaspiPin.GPIO_14);
		pins.put(15, RaspiPin.GPIO_15);
		pins.put(16, RaspiPin.GPIO_16);

		pins.put(21, RaspiPin.GPIO_21);
		pins.put(22, RaspiPin.GPIO_22);
		pins.put(23, RaspiPin.GPIO_23);
		pins.put(24, RaspiPin.GPIO_24);
		pins.put(25, RaspiPin.GPIO_25);
		pins.put(26, RaspiPin.GPIO_26);
		pins.put(27, RaspiPin.GPIO_27);
		pins.put(28, RaspiPin.GPIO_28);
		pins.put(29, RaspiPin.GPIO_29);

		pins.put(30, RaspiPin.GPIO_30);
		pins.put(31, RaspiPin.GPIO_31);
	}

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

	public static Pin getPin(Integer pin) {
		return pins.keySet().contains(pin) ? pins.get(pin) : null;
	}

}
