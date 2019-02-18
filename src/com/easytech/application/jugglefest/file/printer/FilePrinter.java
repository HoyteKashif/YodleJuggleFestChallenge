package com.easytech.application.jugglefest.file.printer;

import java.io.IOException;
import java.io.PrintWriter;

import com.easytech.application.jugglefest.file.data.model.Circuit;
import com.easytech.application.jugglefest.file.data.model.CircuitMember;
import com.easytech.application.jugglefest.file.data.model.CircuitPreference;
import com.easytech.application.jugglefest.file.data.model.JuggleFest;
import com.easytech.application.jugglefest.file.data.model.Juggler;
import com.easytech.application.jugglefest.file.parser.JuggleFestFileChooser;
import com.easytech.application.util.JuggleFestHelper;

public class FilePrinter {
	public static void print(JuggleFest p_juggleFest) {

		try (PrintWriter out = new PrintWriter(JuggleFestFileChooser.getOutputFile())) {

			int sum_c1970_jugglers = 0;

			for (Circuit circuit : p_juggleFest.getCircuitMap().values()) {
				out.print("C" + circuit.getId());

				int counter = 0;
				for (CircuitMember member : circuit.getMembers()) {
					/* count the number of team members visited */
					counter++;
					Juggler juggler = member.getJuggler();
					out.print(" J" + juggler.getId());

					if (circuit.getId() == 1970) {
						sum_c1970_jugglers += juggler.getId();
					}

					// print the identification number of the Juggler
					// then print the dot product for that circuit
					for (CircuitPreference preference : juggler.getPreferences()) {
						final int pref_cId = preference.getCircuitId();
						if (preference.isChecked()) {
							// preference has been checked
							out.print(" C" + pref_cId + ":" + preference.getDotProduct());
						} else {
							// preference has not been checked
							Integer dotProduct = JuggleFestHelper.calculteDotProduct(juggler,
									p_juggleFest.getCircuitMap().get(pref_cId));
							out.print(" C" + pref_cId + ":" + dotProduct);
						}
					}
					if (counter < circuit.getMemberCount() - 1)
						out.print(",");

				}

				// skip down to the next line in the file
				out.println();
			}

			System.out.println("sum of Juggler ids assigned to C1970 is " + sum_c1970_jugglers + ".");

		} catch (NullPointerException e) {
			System.out.println(e);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error: Cannot create that file");
			System.exit(0);
		}
	}
}
