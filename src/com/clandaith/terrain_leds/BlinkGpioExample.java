package com.clandaith.terrain_leds;
//START SNIPPET: blink-gpio-snippet

/*
* #%L
* **********************************************************************
* ORGANIZATION  :  Pi4J
* PROJECT       :  Pi4J :: Java Examples
* FILENAME      :  BlinkGpioExample.java
*
* This file is part of the Pi4J project. More information about
* this project can be found here:  http://www.pi4j.com/
* **********************************************************************
* %%
* Copyright (C) 2012 - 2019 Pi4J
* %%
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation, either version 3 of the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Lesser Public License for more details.
*
* You should have received a copy of the GNU General Lesser Public
* License along with this program.  If not, see
* <http://www.gnu.org/licenses/lgpl-3.0.html>.
* #L%
*/

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

/**
 * This example code demonstrates how to perform simple blinking LED logic of a
 * GPIO pin on the Raspberry Pi using the Pi4J library.
 *
 * @author Robert Savage
 */
public class BlinkGpioExample {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("<--Pi4J--> GPIO Blink Example ... started.");

		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #01 & #03 as an output pins and blink
		final GpioPinDigitalOutput led2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);

		// continuously blink the led every 1 second
		led2.blink(1000);

		System.out.println(" ... the LED will continue blinking until the program is terminated.");
		System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

		// keep program running until user aborts (CTRL-C)
		while (true) {
			Thread.sleep(500);
		}

		// stop all GPIO activity/threads
		// (this method will forcefully shutdown all GPIO monitoring threads and
		// scheduled tasks)
		// gpio.shutdown(); <--- implement this method call if you wish to terminate the
		// Pi4J GPIO controller
	}
}

// END SNIPPET: blink-gpio-snippet
