package com.clandaith.terrain_leds;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 *
 * @author user
 */
public class ConditionsTicker {
	private static final Integer LINE_1 = 1;
	private static final Integer LINE_2 = 2;

	public static void main(String[] args) {
		String condition1 = args[0];
		String condition2 = args[1];

		try {
			I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
			I2CDevice device = bus.getDevice(0x27);
			I2CLCD lcd = new I2CLCD(device);

			lcd.init();
			lcd.backlight(true);

			new ConditionsTicker().new Ticker(lcd, condition1, LINE_1).start();
			new ConditionsTicker().new Ticker(lcd, condition2, LINE_2).start();

			while (true) {
				Thread.sleep(1000);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private class Ticker extends Thread {
		private I2CLCD lcd;
		private String message;
		private Integer line;

		public Ticker(I2CLCD lcd, String message, Integer line) {
			this.lcd = lcd;
			this.message = message;
			this.line = line;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000l);

					if (message.length() > 16) {
						int counter = 0;
						while (counter < message.length()) {
							lcd.displayStringPos(Commons.padRight(message.substring(counter), 16), line, 0);
							counter++;
							Thread.sleep(250l);
						}
					}

					lcd.displayStringPos(Commons.padRight(" ", 16), line, 0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}