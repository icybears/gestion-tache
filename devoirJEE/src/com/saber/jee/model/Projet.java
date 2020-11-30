package com.saber.jee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Projet {

	@Id
	private String id;
	
	private String intitule;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private StatutProjet statut;
	
	@Column(name="charge_horaire")
	private int chargeHoraire;
	
	@OneToMany(mappedBy="projet",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Tache> taches = new ArrayList<>();

	public Projet() {
		super();
	}

	public Projet(String id, String intitule) {
		super();
		this.id = id;
		this.intitule = intitule;
		
	}

	public Projet(String intitule, String description, StatutProjet statut, int chargeHoraire) {
		super();
		this.intitule = intitule;
		this.description = description;
		this.statut = statut;
		this.chargeHoraire = chargeHoraire;
	}

	public Projet(String id, String intitule, String description, StatutProjet statut, int chargeHoraire) {
		super();
		this.id = id;
		this.intitule = intitule;
		this.description = description;
		this.statut = statut;
		this.chargeHoraire = chargeHoraire;
	}

	public void addTache(Tache tache) {
		tache.setProjet(this);
	}
	
	public void removeTache(Tache tache) {
		tache.setProjet(null);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatutProjet getStatut() {
		return statut;
	}

	public void setStatut(StatutProjet statut) {
		this.statut = statut;
	}

	public int getChargeHoraire() {
		return chargeHoraire;
	}

	public void setChargeHoraire(int chargeHoraire) {
		this.chargeHoraire = chargeHoraire;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
}
