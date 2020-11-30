package com.saber.jee.model;

public enum StatutProjet {

	A_FAIRE("A faire"), 
	EN_COURS("En cours"),
	REALISE("Réalisé");
	
	private String statutName;
	
	private StatutProjet(String statutName) {
		this.statutName = statutName;
	}
	
	public String getStatutName() {
		return this.statutName;
	}
}
