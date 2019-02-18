package com.easytech.application.jugglefest.file.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.easytech.application.jugglefest.file.data.model.Circuit;
import com.easytech.application.jugglefest.file.data.model.CircuitPreference;
import com.easytech.application.jugglefest.file.data.model.Juggler;

abstract class LineParser {
	protected final Map<Integer, Circuit> circuitMap;
	protected final Map<Integer, Juggler> jugglerMap;

	protected LineParser() {
		this.circuitMap = new HashMap<>();
		this.jugglerMap = new HashMap<>();
	}

	private enum Attribute {
		HAND_TO_EYE("H:"), ENDURANCE("E:"), PIZZAZZ("P:");
		public final String identifier;

		private Attribute(String p_identifier) {
			this.identifier = p_identifier;
		}

	}

	private enum LineHeader {
		CIRCUIT("C"), JUGGLER("J");

		public final String identifier;

		private LineHeader(String p_identifier) {
			this.identifier = p_identifier;
		}
	}

	private static List<Integer> toIntegerList(final String p_strData) {
		String validText = p_strData.replace(LineHeader.CIRCUIT.identifier, "");
		validText = validText.replace(",", " ");

		final List<Integer> ints = new ArrayList<>();
		for (String s : validText.split("\\s+")) {
			ints.add(Integer.valueOf(s));
		}
		return ints;
	}

	private List<CircuitPreference> getPreferences(final String p_strPreferences) {
		final List<CircuitPreference> preferences = new ArrayList<>();
		for (Integer circuitId : toIntegerList(p_strPreferences)) {
			preferences.add(new CircuitPreference(circuitId));
		}
		return preferences;
	}

	protected final void parse(String p_strLine) {
		Objects.requireNonNull(p_strLine);

		final String[] data = p_strLine.split("\\s+");

		final String type = data[0];
		final int id = Integer.valueOf(data[1].replace(type, ""));
		final int handToEye = parse(Attribute.HAND_TO_EYE, data[2]);
		final int endurance = parse(Attribute.ENDURANCE, data[3]);
		final int pizzazz = parse(Attribute.PIZZAZZ, data[4]);

		if (LineHeader.CIRCUIT.identifier.equals(type)) {
			circuitMap.put(id, new Circuit(id, handToEye, endurance, pizzazz));
		} else {
			jugglerMap.put(id, new Juggler(id, handToEye, endurance, pizzazz, getPreferences(data[5])));
		}
	}

	private static int parse(final Attribute p_eAttribute, final String p_strData) {
		Objects.requireNonNull(p_strData);
		return Integer.valueOf(p_strData.replace(p_eAttribute.identifier, ""));
	}
}