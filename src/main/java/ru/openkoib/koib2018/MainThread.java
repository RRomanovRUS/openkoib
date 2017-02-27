package ru.openkoib.koib2018;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class MainThread extends Thread {
	private File scanFile;
	private final PageMarking emptyBlankMarking;
	private PageMarking currentBlank;

	public MainThread(String fileName) {

		scanFile = new File(fileName);

		// TODO: ниже 5 это количество клеток, нужно будет вынести в конфиг
		emptyBlankMarking = new PageMarking(5);
		currentBlank = new PageMarking(5);

	}

	public void run() {
		boolean isExit = false;
		while (!isExit) {
			try {

				if (scanFile.exists()) {
					BufferedImage img = null;
					try {
						System.out.println("Получили скан         "
								+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

						/*
						 * DataInputStream datainputstream = new
						 * DataInputStream(
						 * getClass().getResourceAsStream(scanFile.
						 * getAbsolutePath())); byte abyte0[] = new
						 * byte[datainputstream.available()];
						 * datainputstream.readFully(abyte0);
						 * datainputstream.close(); Image tt =
						 * Toolkit.getDefaultToolkit().createImage(abyte0);
						 */

						img = ImageIO.read(scanFile);
						System.out.println("Начало обработки скана "
								+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
						int counter = 0;
						for (Rectangle rect : emptyBlankMarking.getmarksPostion()) {

							BufferedImage dest = img.getSubimage(rect.x, rect.y, rect.width, rect.height);
							// BufferedImage dest = (BufferedImage) tt;
							currentBlank.getPoint(counter).setBrightness(getBrightnessFromImage(dest));

							counter++;
							// XXX: ниже пример сохранения ячеек
							/*
							 * File outputfile = new File("out" + File.separator
							 * + "image " + counter + ".png"); try {
							 * ImageIO.write(dest, "png", outputfile); } catch
							 * (IOException e) { // TODO Auto-generated catch
							 * block e.printStackTrace(); }
							 */
						}

						emptyBlankMarking.compare(currentBlank);
						isExit = true;
					} catch (IOException e) {
					}
				} else {
					Thread.sleep(1000);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(
				"Закончили обработку выходим " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

	}

	private double getBrightnessFromImage(BufferedImage image) {
		int width;
		int height;

		width = image.getWidth();
		height = image.getHeight();

		double res_brightness = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				Color c = new Color(image.getRGB(j, i));
				// XXX: тут могут быть другие варианты рассчета
				res_brightness += (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue());

			}
		}
		return res_brightness;

	}
}
