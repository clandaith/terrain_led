package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class SkyTower {
	private static GpioPinDigitalOutput led = null;

	private static final Long DIT = 300l;

	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, led);

		while (true) {
			// K
			blink(DIT * 3);
			blink(DIT);
			blink(DIT * 3);

			// F
			blink(DIT);
			blink(DIT);
			blink(DIT * 3);
			blink(DIT);

			// 7
			blink(DIT * 3);
			blink(DIT * 3);
			blink(DIT);
			blink(DIT);
			blink(DIT);

			// M
			blink(DIT * 3);
			blink(DIT * 3);

			// T
			blink(DIT * 3);

			// E
			blink(DIT);

			Thread.sleep(DIT * 6);
		}
	}

	private static void blink(Long amount) {

		try {
			led.high();
			Thread.sleep(amount);
			led.low();
			Thread.sleep(DIT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
