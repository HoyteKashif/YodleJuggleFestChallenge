package com.easytech.application.jugglefest.file.data.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JuggleFestFile {
	private final Map<Integer, Circuit> circuitMap;
	private final Map<Integer, Juggler> jugglerMap;

	public JuggleFestFile(final Map<Integer, Circuit> p_circuitMap, final Map<Integer, Juggler> p_jugglerMap) {
		this.circuitMap = new HashMap<>(p_circuitMap);
		this.jugglerMap = new HashMap<>(p_jugglerMap);
	}

	public Map<Integer, Circuit> getCircuitMap() {
		return circuitMap;
	}

	public Map<Integer, Juggler> getJugglerMap() {
		return jugglerMap;
	}

	public Circuit getCircuit(final Integer p_id) {
		return circuitMap.get(p_id);
	}

	public List<Circuit> getCircuits() {
		return new ArrayList<>(circuitMap.values());
	}

	public Juggler getJuggler(final Integer p_id) {
		return jugglerMap.get(p_id);
	}

	public List<Juggler> getJugglers() {
		return new ArrayList<>(jugglerMap.values());
	}
}
