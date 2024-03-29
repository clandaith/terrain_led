package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class ShortBuildingTankLight {
	private static GpioPinDigitalOutput tankLight = null;

	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		tankLight = gpio.provisionDigitalOutputPin(Commons.getPin(16), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, tankLight);

		while (true) {
			for (int counter = 0; counter < 10; counter++) {
				blinkLight();
			}

			tankLight.high();
			Thread.sleep(2000l);
		}
	}

	private static void blinkLight() throws InterruptedException {
		tankLight.high();
		Thread.sleep(Commons.getRandomSecond());
		tankLight.low();
		Thread.sleep(200l);
	}
}
