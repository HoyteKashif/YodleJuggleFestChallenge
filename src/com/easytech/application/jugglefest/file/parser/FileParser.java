package com.easytech.application.jugglefest.file.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import com.easytech.application.jugglefest.file.data.model.Circuit;
import com.easytech.application.jugglefest.file.data.model.JuggleFestFile;

public final class FileParser extends LineParser {
	private final static String ATTRIBUTES = "H:\\d+ E:\\d+ P:\\d+";
	private final static String PREFERENCES = "(C\\d+,?)+";
	private final static String CIRCUIT_PATTERN = "C C\\d+ " + ATTRIBUTES;
	private final static String JUGGLER_PATTERN = "J J\\d+ " + ATTRIBUTES + " " + PREFERENCES;

	// private boolean bIsFileParsed = false;
	private final File file;

	private FileParser(final File p_File) throws Exception {
		this.file = p_File;
	}

	private void parse() throws Exception {
		Objects.requireNonNull(file, "Illegal Argument: invalid Juggle Fest File.");

		try (final Scanner fileScanner = new Scanner(file)) {

			while (fileScanner.hasNextLine()) {

				final String line = fileScanner.nextLine();

				if (!line.isEmpty()) {
					if (line.matches(CIRCUIT_PATTERN) || line.matches(JUGGLER_PATTERN)) {
						parse(line);
					} else {
						throw new Exception("Found bad data line.");
					}
				}
			}

			Circuit.setJugglerLimit(jugglerMap.size() / circuitMap.size());

		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static final JuggleFestFile parse(final File file) throws Exception {
		Objects.requireNonNull(file, "Illegal Argument: invalid Juggle Fest File.");

		final FileParser parser = new FileParser(file);
		parser.parse();
		return new JuggleFestFile(parser.circuitMap, parser.jugglerMap);
	}
}
