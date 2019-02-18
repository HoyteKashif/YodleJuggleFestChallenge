package com.easytech.application.jugglefest.matcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import com.easytech.application.jugglefest.file.data.model.Circuit;
import com.easytech.application.jugglefest.file.data.model.CircuitPreference;
import com.easytech.application.jugglefest.file.data.model.JuggleFest;
import com.easytech.application.jugglefest.file.data.model.JuggleFestFile;
import com.easytech.application.jugglefest.file.data.model.Juggler;
import com.easytech.application.util.JuggleFestHelper;

public class CircuitMatcher {
	private final Set<Integer> circuitWithVacancy;
	private final LinkedBlockingQueue<Integer> jugglerQueue;
	private final Map<Integer, Circuit> circuitMap;
	private final Map<Integer, Juggler> jugglerMap;

	public CircuitMatcher(final JuggleFestFile p_file) {
		Objects.requireNonNull(p_file);

		circuitMap = new HashMap<>(p_file.getCircuitMap());
		circuitWithVacancy = new HashSet<>(p_file.getCircuitMap().keySet());

		jugglerMap = new HashMap<>(p_file.getJugglerMap());
		jugglerQueue = new LinkedBlockingQueue<>(p_file.getJugglerMap().keySet());
	}

	public JuggleFest match() {
		while (availableJugglers()) {
			for (Iterator<Integer> itr = jugglerQueue.iterator(); itr.hasNext();) {

				final Juggler juggler = jugglerMap.get(itr.next());

				if (juggler.hasUncheckedPreference()) {
					if (joinByPreference(juggler)) {
						itr.remove();
					}
				} else {
					if (joinByCircuitVacancy(juggler)) {
						itr.remove();
					}
				}
			}
		}

		return new JuggleFest(circuitMap);
	}

	public boolean joinByCircuitVacancy(final Juggler p_Juggler) {
		final Iterator<Integer> itr = circuitWithVacancy.iterator();
		if (itr.hasNext()) {
			final Circuit circuit = circuitMap.get(itr.next());

			circuit.add(p_Juggler);

			if (circuit.isFull()) {
				circuitWithVacancy.remove(circuit.getId());
			}

			return true;
		}
		return false;
	}

	public boolean joinByPreference(final Juggler p_juggler) {

		boolean joined = false;
		while (p_juggler.hasUncheckedPreference() && !joined) {
			final CircuitPreference preference = p_juggler.getNextPreference();
			final Circuit circuit = circuitMap.get(preference.getCircuitId());
			final Integer dotProduct = JuggleFestHelper.calculteDotProduct(p_juggler, circuit);

			if (dotProduct == null) {
				System.out.println("dot product returned");
			}

			if (circuit.hasVacancy()) {
				joined = circuit.add(dotProduct, p_juggler);
			} else {
				if (dotProduct > circuit.getWeakestDotProduct()) {
					Integer weakestJugglerId = circuit.replaceWeakestJuggler(dotProduct, p_juggler);

					jugglerQueue.add(weakestJugglerId);

					joined = true;
				}
			}

			if (circuit.isFull()) {
				circuitWithVacancy.remove(circuit.getId());
			}

			preference.setDotProduct(dotProduct);
			preference.setChecked(true);
		}
		return joined;
	}

	private boolean availableJugglers() {
		return !jugglerQueue.isEmpty();
	}
}
