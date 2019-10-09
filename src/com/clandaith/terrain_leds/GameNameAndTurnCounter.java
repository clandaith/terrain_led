package com.clandaith.terrain_leds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 *
 * @author user
 */
public class GameNameAndTurnCounter {
	private static final Integer LINE_1 = 1;
	private static final Integer LINE_2 = 2;

	public static void main(String[] args) {
		String gameName = args[0];

		try {
			I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
			I2CDevice device = bus.getDevice(0x27);
			I2CLCD lcd = new I2CLCD(device);

			TurnCounterListener turnCounterListener = new TurnCounterListener(lcd);

			lcd.init();
			lcd.backlight(true);
			lcd.displayStringPos(gameName, LINE_1, 0);
			lcd.displayStringPos(Commons.padMiddle("Turn: 0", getTemp(), 16), LINE_2, 0);

			final GpioController gpio = GpioFactory.getInstance();
			final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_15,
					PinPullResistance.PULL_DOWN);

			gpio.setShutdownOptions(false, myButton);
			myButton.setDebounce(100);
			myButton.addListener(turnCounterListener);

			while (true) {
				Thread.sleep(1000);
				lcd.displayStringPos(Commons.padMiddle("Turn: " + turnCounterListener.getTurn(), getTemp(), 16), LINE_2,
						0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static String getTemp() throws IOException {
		String line = "";
		Process process;
		process = Runtime.getRuntime().exec("bash -c /home/pi/java/temp.sh");

		BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String b;
		while ((b = output.readLine()) != null) {
			line = b + "º";
		}

		output.close();
		return line;
	}
}