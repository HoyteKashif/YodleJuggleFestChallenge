package com.easytech.application.util;

import com.easytech.application.jugglefest.file.data.model.Circuit;
import com.easytech.application.jugglefest.file.data.model.Juggler;

public final class JuggleFestHelper {
	/**
	 * Calculates the Dot of a Juggler and Circuit.
	 * 
	 * @param p_jugglerID
	 *            Juggler
	 * @param p_circuitID
	 *            Circuit ID of circuit
	 * @return An integer value
	 */
	public static Integer calculteDotProduct(final Juggler juggler, final Circuit circuit) {
		if (juggler == null) {
			throw new IllegalArgumentException("non-null Juggler required.");
		}

		if (circuit == null) {
			throw new IllegalArgumentException("non-null Circuit required.");
		}

		final int iHandToEye = juggler.getHandToEye() * circuit.getHandToEye();

		final int iEndurance = juggler.getEndurance() * circuit.getEndurance();

		final int iPizzazz = juggler.getPizzazz() * circuit.getPizzazz();

		return iHandToEye + iEndurance + iPizzazz;
	}

	private JuggleFestHelper() {
	}
}
