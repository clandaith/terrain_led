package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class AptPanel {
	public static void main(final String[] args) throws InterruptedException {
		Thread.sleep(Commons.getRandomSecond());

		GpioController gpio = GpioFactory.getInstance();

		GpioPinDigitalOutput redApt1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, PinState.LOW);
		GpioPinDigitalOutput redApt2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, PinState.LOW);
		GpioPinDigitalOutput redApt3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, PinState.LOW);
		GpioPinDigitalOutput redApt4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_31, PinState.LOW);

		gpio.setShutdownOptions(false, PinState.LOW, redApt1, redApt2, redApt3, redApt4);

		BlinkingLED led1Thread = new BlinkingLED(redApt1);
		BlinkingLED led2Thread = new BlinkingLED(redApt2);
		BlinkingLED led3Thread = new BlinkingLED(redApt3);
		BlinkingLED led4Thread = new BlinkingLED(redApt4);

		led1Thread.start();
		led2Thread.start();
		led3Thread.start();
		led4Thread.start();
	}
}
