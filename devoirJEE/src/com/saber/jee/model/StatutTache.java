package com.saber.jee.model;

public enum StatutTache {
	
	A_FAIRE("A faire"),
	EN_COURS("En cours"),
	LIVRE("Livré");
	
	private String statutName;
	
	private StatutTache(String statutName) {
		this.statutName = statutName;
	}
	
	public String getStatutName() {
		return this.statutName;
	}
}
