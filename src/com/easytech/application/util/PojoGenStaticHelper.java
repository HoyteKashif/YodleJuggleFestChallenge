package com.easytech.application.util;

import java.util.Objects;

import com.easytech.application.jugglefest.file.data.model.Circuit;
import com.easytech.application.jugglefest.file.data.model.Juggler;

public class PojoGenStaticHelper {
	/**
	 * Calculates the Dot of a Juggler and Circuit.
	 * 
	 * @param p_jugglerID
	 * 			Juggler
	 * @param p_circuitID
	 * 			Circuit ID of circuit
	 * @return
	 * 		An integer value
	 */
	public static Integer calculteDotProduct( final Juggler p_oJuggler, final Circuit p_oCircuit){
		return 
			Objects.requireNonNull(
					p_oJuggler.getHandToEye() * p_oCircuit.getHandToEye() +
					p_oJuggler.getEndurance() * p_oCircuit.getEndurance() +
					p_oJuggler.getPizzazz() * p_oCircuit.getPizzazz());
	}
}
