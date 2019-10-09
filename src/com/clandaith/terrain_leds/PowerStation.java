package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class PowerStation {
	private static GpioPinDigitalOutput tankLight = null;

	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput floodLight = gpio.provisionDigitalOutputPin(Commons.getPin(15), PinState.LOW);
		GpioPinDigitalOutput securityLight = gpio.provisionDigitalOutputPin(Commons.getPin(1), PinState.LOW);
		tankLight = gpio.provisionDigitalOutputPin(Commons.getPin(16), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, floodLight, securityLight);

		new BlinkingLED(securityLight, 1000l, 200l).start();
		new BlinkingLED(floodLight, -1l, 100l).start();

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
