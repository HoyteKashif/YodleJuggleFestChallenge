package com.easytech.application.jugglefest.file.data.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.easytech.application.util.JuggleFestHelper;

public final class Circuit extends JugglefestModel {
	public static int maxJugglerLimit = 0;

	private static boolean bSetJugglerLimit = false;
	private final TreeSet<CircuitMember> members;

	private static final Comparator<CircuitMember> DESCENDING_BY_DOT_PRODUCT = new Comparator<CircuitMember>() {
		@Override
		public int compare(final CircuitMember member1, final CircuitMember member2) {
			return (-1) * member1.getDotProduct().compareTo(member2.getDotProduct());
		}
	};

	public Circuit(final Integer p_id, final int p_handToEye, final int p_endurance, final int p_pizzazz) {
		super(p_id, p_handToEye, p_endurance, p_pizzazz);

		this.members = new TreeSet<>(DESCENDING_BY_DOT_PRODUCT);
	}

	public Integer replaceWeakestJuggler(final Integer p_dotProduct, final Juggler p_juggler) {
		add(p_dotProduct, p_juggler);
		return removeWeakestJuggler();
	}

	public Integer removeWeakestJuggler() {
		if (members.isEmpty()) {
			return null;
		}

		final CircuitMember member = getLastMember();
		members.remove(member);
		return member.getId();
	}

	public CircuitMember getLastMember() {
		return members.isEmpty() ? null : members.last();
	}

	public boolean add(final Juggler p_juggler) {
		final Integer dotProduct = JuggleFestHelper.calculteDotProduct(p_juggler, this);
		return add(dotProduct, p_juggler);
	}

	public boolean add(final Integer p_dotProduct, final Juggler p_juggler) {
		return members.add(new CircuitMember(p_dotProduct, p_juggler));
	}

	public Set<CircuitMember> getMembers() {
		return members;
	}

	public int getMemberCount() {
		return members.size();
	}

	public boolean isFull() {
		return !hasVacancy();
	}

	public boolean hasVacancy() {
		return members.size() < maxJugglerLimit;
	}

	public Integer getWeakestDotProduct() {
		if (members.isEmpty()) {
			return null;
		}

		return getLastMember().getDotProduct();
	}

	public static void setJugglerLimit(final int p_iLimit) throws Exception {
		if (bSetJugglerLimit) {
			throw new Exception("Not Allowed to change the Juggler limit once it is set.");
		}

		maxJugglerLimit = p_iLimit;
		bSetJugglerLimit = true;
	}

	public String toString() {
		return String.format("C%s H:%s E:%s P:%s", id, handToEye, endurance, pizzazz);
	}
}
