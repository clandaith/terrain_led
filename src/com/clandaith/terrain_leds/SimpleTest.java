package com.clandaith.terrain_leds;

public class SimpleTest {
	public static void main(String[] args) {
		String gameName = "1234567890 1234567890";
		System.out.println("Length: " + gameName.length());
		while (true) {
			try {
				Thread.sleep(1000l);

				if (gameName.length() > 16) {
					int counter = 0;
					while (counter < gameName.length()) {
						// lcd.displayStringPos(gameName.substring(counter), LINE_1, 0);
						System.out.println(gameName.substring(counter));
						counter++;
						Thread.sleep(200l);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
