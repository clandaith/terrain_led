package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class GoGoClub {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();

		GpioPinDigitalOutput topDoor = gpio.provisionDigitalOutputPin(Commons.getPin(31), PinState.LOW);
		GpioPinDigitalOutput bottomDoor = gpio.provisionDigitalOutputPin(Commons.getPin(26), PinState.LOW);
		GpioPinDigitalOutput securityPanel = gpio.provisionDigitalOutputPin(Commons.getPin(27), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, topDoor, bottomDoor, securityPanel);

		new BlinkingLED(topDoor, 300l, 300l).start();
		new BlinkingLED(bottomDoor, 300l, 300l).start();
		new BlinkingLED(securityPanel, 100l, 100l).start();
	}
}
