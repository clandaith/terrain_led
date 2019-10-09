package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class TurnCounterListener implements GpioPinListenerDigital {

	int turnCounter = 0;
	I2CLCD lcd;

	public TurnCounterListener(I2CLCD lcd) {
		this.lcd = lcd;
	}

	public int getTurn() {
		return turnCounter;
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		if (event.getState() == PinState.HIGH) {
			lcd.displayStringPos("Turn: " + (++turnCounter), 2, 0);
		}
	}
}
