package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ShortBuildingTankLight {
	private static GpioPinDigitalOutput led = null;

	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, led);

		while (true) {
			for (int counter = 0; counter < 10; counter++) {
				blinkLight();
			}

			led.high();
			Thread.sleep(2000l);
		}
	}

	private static void blinkLight() throws InterruptedException {
		led.high();
		Thread.sleep(Commons.getRandomSecond());
		led.low();
		Thread.sleep(200l);
	}
}
