package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class AptPanel {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();

		GpioPinDigitalOutput redApt1 = gpio.provisionDigitalOutputPin(Commons.getPin(22), PinState.LOW);
		GpioPinDigitalOutput redApt2 = gpio.provisionDigitalOutputPin(Commons.getPin(23), PinState.LOW);
		GpioPinDigitalOutput redApt3 = gpio.provisionDigitalOutputPin(Commons.getPin(24), PinState.LOW);
		GpioPinDigitalOutput redApt4 = gpio.provisionDigitalOutputPin(Commons.getPin(25), PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, redApt1, redApt2, redApt3, redApt4);

		new BlinkingLED(redApt1).start();
		new BlinkingLED(redApt2).start();
		new BlinkingLED(redApt3).start();
		new BlinkingLED(redApt4).start();
	}
}
