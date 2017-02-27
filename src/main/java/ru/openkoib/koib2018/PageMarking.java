package ru.openkoib.koib2018;

import java.util.ArrayList;
import java.util.List;
import ru.openkoib.koib2018.RectangleWithAdditionalData;

public class PageMarking {

	private static final double SENSITIVITY_VALUE = 10000;
	private List<RectangleWithAdditionalData> marksPostion;
	private final int size;

	public PageMarking(int size) {
		this.size = size;
		marksPostion = new ArrayList<RectangleWithAdditionalData>(size);
		// TODO: вынести в файл конфигруации
		// Ниже установка значения для чистого листа
		marksPostion.add(new RectangleWithAdditionalData(1390, 631, 42, 42, 439831.5048000023));
		marksPostion.add(new RectangleWithAdditionalData(1390, 947, 42, 42, 436792.2440000105));
		marksPostion.add(new RectangleWithAdditionalData(1390, 1227, 42, 42, 440032.47440000775));
		marksPostion.add(new RectangleWithAdditionalData(1390, 1513, 42, 42, 439261.4424000082));
		marksPostion.add(new RectangleWithAdditionalData(1390, 1830, 42, 42, 436777.78460000985));
	}

	public RectangleWithAdditionalData getPoint(int pos) {
		return marksPostion.get(pos);
	}

	public List<RectangleWithAdditionalData> getmarksPostion() {
		return marksPostion;
	}

	public void compare(PageMarking other) {
		for (int i = 0; i < marksPostion.size(); i++) {
			double diff = marksPostion.get(i).getBrightness() - other.marksPostion.get(i).getBrightness();
			// Сверка с образцом, если значение отличается от образца, значит
			// стоит знак в ячейке
			if (Math.abs(diff) > SENSITIVITY_VALUE) {
				System.out.println("Выбран - " + (i + 1));
			}
		}

	}
}
