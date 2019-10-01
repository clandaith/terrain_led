package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class TowerBlinkingLights {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());
		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput yellow1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, PinState.LOW);
		GpioPinDigitalOutput yellow2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, yellow1, yellow2);

		while (true) {
			yellow1.high();
			yellow2.low();
			Thread.sleep(500l);
			yellow1.low();
			yellow2.high();
			Thread.sleep(500l);
		}
	}
}
