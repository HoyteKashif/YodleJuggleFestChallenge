package com.easytech.application.jugglefest.file.data.model;

public abstract class JugglefestModel {
	protected final int id;
	protected final int handToEye;
	protected final int endurance;
	protected final int pizzazz;
	
	public JugglefestModel(final int p_Id, final int p_iHandToEye, final int p_iEndurance, final int p_iPizzazz){
		this.id = p_Id;
		this.handToEye = p_iHandToEye;
		this.endurance = p_iEndurance;
		this.pizzazz = p_iPizzazz;
	}
	
	public int getId(){
		return id;
	}
	
	public int getHandToEye(){
		return handToEye;
	}
	
	public int getEndurance(){
		return endurance;
	}
	
	public int getPizzazz(){
		return pizzazz;
	}
}
