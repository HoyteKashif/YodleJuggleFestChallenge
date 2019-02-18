package com.easytech.application.jugglefest.file.data.model;

public class CircuitPreference {
	private boolean checked;
	private Integer dotProduct;

	private final Integer circuitId;

	public CircuitPreference(final Integer p_circuitId) {
		circuitId = p_circuitId;
	}

	public Integer getDotProduct() {
		return dotProduct;
	}

	public void setDotProduct(final Integer p_dotProduct) {
		this.dotProduct = p_dotProduct;
	}

	public Integer getCircuitId() {
		return circuitId;
	}

	public void setChecked(final boolean p_checked) {
		this.checked = p_checked;
	}

	public boolean isChecked() {
		return checked;
	}
}
