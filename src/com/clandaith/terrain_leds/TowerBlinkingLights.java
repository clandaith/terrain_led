package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class TowerBlinkingLights {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput yellow1 = gpio.provisionDigitalOutputPin(Commons.getPin(6), PinState.LOW);
		GpioPinDigitalOutput yellow2 = gpio.provisionDigitalOutputPin(Commons.getPin(10), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, yellow1, yellow2);

		new BlinkingLED(yellow1, 500l, 500l).start();
		Thread.sleep(500l);
		new BlinkingLED(yellow2, 500l, 500l).start();
	}
}
