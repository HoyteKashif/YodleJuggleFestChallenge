package com.easytech.application.jugglefest.file.parser;

import java.io.File;

import javax.swing.JFileChooser;

final public class JuggleFestFileChooser {
	final private static JFileChooser fc = new JFileChooser();

	public static File getInputFile() {
		int returnState = JFileChooser.CANCEL_OPTION;

		do {
			returnState = fc.showOpenDialog(fc);
		} while (!fc.isVisible());

		if (returnState == JFileChooser.CANCEL_OPTION) {
			System.exit(0);
		}

		return fc.getSelectedFile();
	}

	public static File getOutputFile() {
		int returnState = fc.showSaveDialog(fc);

		if (returnState == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}

		return null;
	}
}