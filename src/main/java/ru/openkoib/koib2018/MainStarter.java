package ru.openkoib.koib2018;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainStarter {
	public static void main(String[] args) {
		System.out.println("Программа запустилась " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		// TODO: добавить проверку аргументов
		MainThread mt = new MainThread(args[0]);
		mt.start();
		try {
			mt.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Выход из программы " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
	}

}
