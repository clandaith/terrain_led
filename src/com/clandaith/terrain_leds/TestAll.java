package com.clandaith.terrain_leds;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class TestAll {
	private static GpioController gpio = null;
	private static GpioPinDigitalOutput whiteBldgBlueSec = null;
	private static GpioPinDigitalOutput whiteBldgDoorUnderTank = null;
	private static GpioPinDigitalOutput whiteBldgTank = null;
	private static GpioPinDigitalOutput redApt1 = null;
	private static GpioPinDigitalOutput redApt2 = null;
	private static GpioPinDigitalOutput redApt3 = null;
	private static GpioPinDigitalOutput redApt4 = null;
	private static GpioPinDigitalOutput skyYellow1 = null;
	private static GpioPinDigitalOutput skyYellow2 = null;
	private static GpioPinDigitalOutput skyTower = null;
	private static GpioPinDigitalOutput shadySecurityDoor = null;
	private static GpioPinDigitalOutput shadyFloodLight = null;

	public static void main(final String[] args) throws InterruptedException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (gpio != null) {
					allOff();
					gpio.shutdown();
				}
			}
		});

		gpio = GpioFactory.getInstance();
		whiteBldgBlueSec = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);
		whiteBldgDoorUnderTank = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, PinState.LOW);
		whiteBldgTank = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, PinState.LOW);
		redApt1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, PinState.LOW);
		redApt2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, PinState.LOW);
		redApt3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, PinState.LOW);
		redApt4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_31, PinState.LOW);
		skyYellow1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, PinState.LOW);
		skyYellow2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, PinState.LOW);
		skyTower = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);
		shadySecurityDoor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);
		shadyFloodLight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, PinState.LOW);

		while (true) {
			allOn();
			Thread.sleep(500l);
			allOff();
			Thread.sleep(500l);
		}
	}

	private static void allOn() {
		whiteBldgBlueSec.high();
		whiteBldgDoorUnderTank.high();
		whiteBldgTank.high();
		redApt1.high();
		redApt2.high();
		redApt3.high();
		redApt4.high();
		skyYellow1.high();
		skyYellow2.high();
		skyTower.high();
		shadySecurityDoor.high();
		shadyFloodLight.high();
	}

	private static void allOff() {
		whiteBldgBlueSec.low();
		whiteBldgDoorUnderTank.low();
		whiteBldgTank.low();
		redApt1.low();
		redApt2.low();
		redApt3.low();
		redApt4.low();
		skyYellow1.low();
		skyYellow2.low();
		skyTower.low();
		shadySecurityDoor.low();
		shadyFloodLight.low();
	}
}
