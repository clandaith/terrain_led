package com.clandaith.terrain_leds;

import com.pi4j.io.i2c.I2CDevice;

/**
 * 
 * https://github.com/Poduzov/PI4J-I2C-LCD/blob/master/I2CLCD.java
 *
 * @author user
 */
public class I2CLCD {

	private static final byte LCD_CLEARDISPLAY = (byte) 0x01;
	private static final byte LCD_RETURNHOME = (byte) 0x02;
	private static final byte LCD_ENTRYMODESET = (byte) 0x04;
	private static final byte LCD_DISPLAYCONTROL = (byte) 0x08;
	private static final byte LCD_CURSORSHIFT = (byte) 0x10;
	private static final byte LCD_FUNCTIONSET = (byte) 0x20;
	private static final byte LCD_SETCGRAMADDR = (byte) 0x40;
	private static final byte LCD_SETDDRAMADDR = (byte) 0x80;

	// flags for display entry mode
	private static final byte LCD_ENTRYRIGHT = (byte) 0x00;
	private static final byte LCD_ENTRYLEFT = (byte) 0x02;
	private static final byte LCD_ENTRYSHIFTINCREMENT = (byte) 0x01;
	private static final byte LCD_ENTRYSHIFTDECREMENT = (byte) 0x00;

	// flags for display on/off control
	private static final byte LCD_DISPLAYON = (byte) 0x04;
	private static final byte LCD_DISPLAYOFF = (byte) 0x00;
	private static final byte LCD_CURSORON = (byte) 0x02;
	private static final byte LCD_CURSOROFF = (byte) 0x00;
	private static final byte LCD_BLINKON = (byte) 0x01;
	private static final byte LCD_BLINKOFF = (byte) 0x00;

	// flags for display/cursor shift
	private static final byte LCD_DISPLAYMOVE = (byte) 0x08;
	private static final byte LCD_CURSORMOVE = (byte) 0x00;
	private static final byte LCD_MOVERIGHT = (byte) 0x04;
	private static final byte LCD_MOVELEFT = (byte) 0x00;

	// flags for function set
	private static final byte LCD_8BITMODE = (byte) 0x10;
	private static final byte LCD_4BITMODE = (byte) 0x00;
	private static final byte LCD_2LINE = (byte) 0x08;
	private static final byte LCD_1LINE = (byte) 0x00;
	private static final byte LCD_5x10DOTS = (byte) 0x04;
	private static final byte LCD_5x8DOTS = (byte) 0x00;

	// flags for backlight control
	private static final byte LCD_BACKLIGHT = (byte) 0x08;
	private static final byte LCD_NOBACKLIGHT = (byte) 0x00;

	private static final byte ENABLE = (byte) 0b00000100; // Enable bit
	private static final byte READ_WRITE_BIT = (byte) 0b00000010; // Read/Write bit
	private static final byte REGESTER_SELECT_BIT = (byte) 0b00000001; // Register select bit

	private I2CDevice device;

	public I2CLCD(I2CDevice device) {
		this.device = device;
	}

	// initializes objects and lcd
	public void init() {
		try {
			lcdWrite((byte) 0x03);
			lcdWrite((byte) 0x03);
			lcdWrite((byte) 0x03);
			lcdWrite((byte) 0x02);

			lcdWrite((byte) (LCD_FUNCTIONSET | LCD_2LINE | LCD_5x8DOTS | LCD_4BITMODE));
			lcdWrite((byte) (LCD_DISPLAYCONTROL | LCD_DISPLAYON));
			lcdWrite((byte) (LCD_CLEARDISPLAY));
			lcdWrite((byte) (LCD_ENTRYMODESET | LCD_ENTRYLEFT));
			Thread.sleep(0, 200000);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Write a single command
	private void writeCmd(byte cmd) {
		try {
			device.write(cmd);
			Thread.sleep(0, 100000);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Write a command and argument
	private void writeCmdArg(byte cmd, byte[] data) {
		try {
			device.write(cmd, data);
			Thread.sleep(0, 100000);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Write a block of data
	private void writeBlockData(byte cmd, byte[] data) {
		try {
			device.write(cmd, data);
			Thread.sleep(0, 100000);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Read a single byte def
	private byte read() {
		try {
			return (byte) device.read();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return (byte) 0;
	}

	// Read
	private byte[] readData(byte cmd) {
		byte[] buffer = new byte[cmd];
		try {
			device.read(buffer, 0, cmd);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return buffer;
	}

	// Read a block of data
	private byte[] readBlockData(byte cmd) {
		byte[] buffer = new byte[cmd];
		try {
			device.read(buffer, 0, cmd);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return buffer;
	}

	// clocks EN to latch command
	private void lcdStrobe(byte data) {
		try {
			device.write((byte) (data | ENABLE | LCD_BACKLIGHT));
			Thread.sleep(0, 500000);
			device.write((byte) ((data & ~ENABLE) | LCD_BACKLIGHT));
			Thread.sleep(0, 100000);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void lcdWriteFourBits(byte data) {
		try {
			device.write((byte) (data | LCD_BACKLIGHT));
			lcdStrobe(data);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void lcdWrite(byte cmd, byte mode) {
		lcdWriteFourBits((byte) (mode | (cmd & 0xF0)));
		lcdWriteFourBits((byte) (mode | ((cmd << 4) & 0xF0)));
	}

	// write a command to lcd
	private synchronized void lcdWrite(byte cmd) {
		lcdWrite(cmd, (byte) 0);
	}

	// write a character to lcd
	public void writeChar(byte charvalue) {
		byte mode = 1;
		lcdWriteFourBits((byte) (mode | (charvalue & 0xF0)));
		lcdWriteFourBits((byte) (mode | ((charvalue << 4) & 0xF0)));
	}

	// put string function
	public void displayString(String string, int line) {
		switch (line) {
		case 1:
			lcdWrite((byte) 0x80);
			break;
		case 2:
			lcdWrite((byte) 0xC0);
			break;
		case 3:
			lcdWrite((byte) 0x94);
			break;
		case 4:
			lcdWrite((byte) 0xD4);
			break;
		}

		for (int i = 0; i < string.length(); i++) {
			lcdWrite((byte) string.charAt(i), REGESTER_SELECT_BIT);
		}
	}

	// clear lcd and set to home
	private void clear() {
		lcdWrite((byte) LCD_CLEARDISPLAY);
		lcdWrite((byte) LCD_RETURNHOME);

	}

	// define backlight on / off(lcd.backlight(1) off = lcd.backlight(0)
	public void backlight(boolean state) {
		// for state, 1 = on, 0 = off
		if (state) {
			writeCmd(LCD_BACKLIGHT);

		} else {
			writeCmd(LCD_NOBACKLIGHT);
		}
	}

	// add custom characters(0 - 7)
	private void loadCustomChars(byte[][] fontdata) {

		lcdWrite((byte) 0x40);
		for (int i = 0; i < fontdata.length; i++) {
			for (int j = 0; j < fontdata[i].length; j++) {
				writeChar(fontdata[i][j]);
			}
		}
	}

	// define precise positioning (addition from the forum)
	public synchronized void displayStringPos(String string, int line, int pos) {
		byte posNew = 0;

		if (line == 1) {
			posNew = (byte) pos;
		} else if (line == 2) {
			posNew = (byte) (0x40 + pos);
		} else if (line == 3) {
			posNew = (byte) (0x14 + pos);
		} else if (line == 4) {
			posNew = (byte) (0x54 + pos);
		}

		lcdWrite((byte) (0x80 + posNew));

		for (int i = 0; i < string.length(); i++) {
			lcdWrite((byte) string.charAt(i), REGESTER_SELECT_BIT);
		}
	}
}
