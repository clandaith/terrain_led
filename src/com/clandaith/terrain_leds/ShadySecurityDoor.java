package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class ShadySecurityDoor {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(Commons.getPin(28), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, led);

		new BlinkingLED(led, 1000l, 100l).start();
	}
}
