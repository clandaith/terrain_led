package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ShortBuildingSecurityLight {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, led);

		BlinkingLED blinkingLED = new BlinkingLED(led, 1000l, 200l);
		blinkingLED.start();
	}

}
