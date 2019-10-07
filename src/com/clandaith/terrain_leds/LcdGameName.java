package com.clandaith.terrain_leds;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 *
 * @author user
 */
public class LcdGameName {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		I2CDevice device = null;
		I2CLCD lcd = null;

		try {
			I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
			device = bus.getDevice(0x27);
			lcd = new I2CLCD(device);
			lcd.init();
			lcd.backlight(true);
			lcd.displayStringPos("Hello, world!", 1, 2);
			lcd.displayStringPos("Blork!!!!", 2, 2);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}