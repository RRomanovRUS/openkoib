package ru.openkoib.koib2018;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScannerThread extends Thread {

	public boolean isScanerAlive = true;
	private Path path;

	public ScannerThread(Path path) {
		this.path = path;

	}

	public void run() {
		boolean isExit = false;
		while (!isExit) {
			try {
				if (!isDirEmpty(path)) {
					// XXX: сканировать здесь
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static boolean isDirEmpty(final Path directory) throws IOException {
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
			return !dirStream.iterator().hasNext();
		}
	}
}
