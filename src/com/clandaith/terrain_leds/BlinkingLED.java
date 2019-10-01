package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class BlinkingLED extends Thread {

	private final GpioPinDigitalOutput ledLight;
	private final Long highDelay;
	private final Long lowDelay;

	public BlinkingLED(GpioPinDigitalOutput ledLight, Long highDelay, Long lowDelay) {
		this.ledLight = ledLight;
		this.highDelay = highDelay;
		this.lowDelay = lowDelay;
	}

	public BlinkingLED(GpioPinDigitalOutput ledLight) {
		this.ledLight = ledLight;
		this.highDelay = -1l;
		this.lowDelay = -1l;
	}

	private Long getHighDelay() {
		return highDelay > 0 ? highDelay : Commons.getRandomSecond();
	}

	private Long getLowDelay() {
		return lowDelay > 0 ? lowDelay : Commons.getRandomSecond();
	}

	@Override
	public void run() {
		while (true) {
			try {
				ledLight.high();

				Thread.sleep(getHighDelay());

				ledLight.low();

				Thread.sleep(getLowDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
