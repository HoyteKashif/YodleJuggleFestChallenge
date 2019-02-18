package com.easytech.application.jugglefest.file.data.model;

import java.util.ArrayList;
import java.util.List;

public final class Juggler extends JugglefestModel {
	private final List<CircuitPreference> preferences;

	public Juggler(final Integer id, final int handToEye, final int endurance, final int pizzazz,
			final List<CircuitPreference> preferences) {
		super(id, handToEye, endurance, pizzazz);

		this.preferences = new ArrayList<>(preferences);
	}

	public CircuitPreference getNextPreference() {
		return preferences.stream().filter(p -> !p.isChecked()).findFirst().orElse(null);
	}

	public List<CircuitPreference> getPreferences() {
		return preferences;
	}

	public boolean hasUncheckedPreference() {
		for (CircuitPreference p : preferences) {
			if (!p.isChecked()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format(" J%s H:%s E:%s P:%s ;", id, handToEye, endurance, pizzazz);
	}
}