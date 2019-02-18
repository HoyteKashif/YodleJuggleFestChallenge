package com.easytech.application.jugglefest.file.data.model;

public class CircuitMember {
	private final Juggler juggler;
	private final Integer dotProduct;

	public CircuitMember(final Integer dotProduct, final Juggler juggler) {
		this.dotProduct = dotProduct;
		this.juggler = juggler;
	}

	public Juggler getJuggler() {
		return juggler;
	}

	public Integer getId() {
		return juggler.getId();
	}

	public Integer getDotProduct() {
		return dotProduct;
	}

}
