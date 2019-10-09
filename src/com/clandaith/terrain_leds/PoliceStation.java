package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class PoliceStation {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();

		GpioPinDigitalOutput frontBlue = gpio.provisionDigitalOutputPin(Commons.getPin(2), PinState.LOW);
		GpioPinDigitalOutput frontRed = gpio.provisionDigitalOutputPin(Commons.getPin(3), PinState.LOW);
		GpioPinDigitalOutput topBlue = gpio.provisionDigitalOutputPin(Commons.getPin(5), PinState.LOW);
		GpioPinDigitalOutput topRed = gpio.provisionDigitalOutputPin(Commons.getPin(4), PinState.LOW);
		GpioPinDigitalOutput yellow = gpio.provisionDigitalOutputPin(Commons.getPin(0), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, frontBlue, frontRed, topBlue, topRed, yellow);

		new BlinkingLED(yellow, 300l, 400l).start();
		new BlinkingLED(frontBlue, 500l, 500l).start();

		Thread.sleep(500l);
		new BlinkingLED(frontRed, 500l, 500l).start();

		Thread.sleep(700l);
		new BlinkingLED(topBlue, 500l, 500l).start();

		Thread.sleep(500l);
		new BlinkingLED(topRed, 500l, 500l).start();
	}
}
