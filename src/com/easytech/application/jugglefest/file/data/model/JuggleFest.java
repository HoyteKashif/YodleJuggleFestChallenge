package com.easytech.application.jugglefest.file.data.model;

import java.util.HashMap;
import java.util.Map;

public class JuggleFest {
	private final Map<Integer, Circuit> circuitMap;

	public JuggleFest(final Map<Integer, Circuit> p_circuitMap) {
		circuitMap = new HashMap<>(p_circuitMap);
	}

	public Map<Integer, Circuit> getCircuitMap() {
		return circuitMap;
	}
}
