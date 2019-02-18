package com.easytech.application.jugglefest.main;

import java.io.File;

import com.easytech.application.jugglefest.file.data.model.JuggleFest;
import com.easytech.application.jugglefest.file.data.model.JuggleFestFile;
import com.easytech.application.jugglefest.file.parser.FileParser;
import com.easytech.application.jugglefest.file.parser.JuggleFestFileChooser;
import com.easytech.application.jugglefest.file.printer.FilePrinter;
import com.easytech.application.jugglefest.matcher.CircuitMatcher;

public class JuggleFestApplication {
	public static void main(String[] args) {
		try {
			System.out.println("Get file.");
			final File file = JuggleFestFileChooser.getInputFile();

			System.out.println("Parse Juggle Fest input File.");
			final JuggleFestFile jfestFile = FileParser.parse(file);

			System.out.println("Match Jugglers to Circuits and create JuggleFest.");
			final CircuitMatcher circuitMatcher = new CircuitMatcher(jfestFile);
			final JuggleFest juggleFest = circuitMatcher.match();

			System.out.println("printing JuggleFest to a file.");
			FilePrinter.print(juggleFest);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
