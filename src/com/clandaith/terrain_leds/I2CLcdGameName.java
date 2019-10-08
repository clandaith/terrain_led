package com.clandaith.terrain_leds;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 *
 * @author user
 */
public class I2CLcdGameName {
	private static final Integer LINE_1 = 1;
	private static final Integer LINE_2 = 2;

	public static void main(String[] args) {
		String gameName = args[0];

		try {
			I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
			I2CDevice device = bus.getDevice(0x27);
			I2CLCD lcd = new I2CLCD(device);
			lcd.init();
			lcd.backlight(true);
			lcd.displayStringPos(gameName, LINE_1, 0);
			lcd.displayStringPos("Turn: 0", LINE_2, 0);

			final GpioController gpio = GpioFactory.getInstance();
			final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_15,
					PinPullResistance.PULL_DOWN);

			gpio.setShutdownOptions(false, myButton);

			myButton.addListener(new GpioPinListenerDigital() {
				int turnCounter = 1;

				@Override
				public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
					if (event.getState() == PinState.HIGH) {
						System.out.println("Turn: " + (turnCounter++));
						lcd.displayStringPos("Turn: " + turnCounter, LINE_2, 0);
					}
				}
			});

			while (true) {
				Thread.sleep(1000);
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}