package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class ShadyBuilding {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput floodLight = gpio.provisionDigitalOutputPin(Commons.getPin(29), PinState.LOW);
		GpioPinDigitalOutput securityPanel = gpio.provisionDigitalOutputPin(Commons.getPin(28), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, floodLight, securityPanel);

		new BlinkingLED(floodLight, -1l, 100l).start();
		new BlinkingLED(securityPanel, 1000l, 100l).start();
	}
}
