package ru.openkoib.koib2018;

import java.awt.Rectangle;

public class RectangleWithAdditionalData extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6224562096402220491L;

	private double brightness;

	
	public RectangleWithAdditionalData(int x, int y, int width, int height, double brightness) {
		super(x, y, width, height);
		this.setBrightness(brightness);
	}

	public double getBrightness() {
		return brightness;
	}

	public void setBrightness(double brightness) {
		this.brightness = brightness;
	}

}
