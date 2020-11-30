package com.saber.jee.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tache {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero")
	private int id;

	private String description;

	private int duree;

	@Column(name = "date_debut")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Column(name = "date_fin")
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Enumerated(EnumType.STRING)
	private StatutTache statut;

	@ManyToOne
	@JoinColumn(nullable=true)
	private Utilisateur utilisateur;

	@ManyToOne
	private Projet projet;

	public Tache() {
		super();
	}

	public Tache(String description) {
		super();
		this.description = description;
	}

	public Tache(String description, int duree, Date dateDebut, Date dateFin, StatutTache statut) {
		super();
		this.description = description;
		this.duree = duree;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.statut = statut;
	}

	public void setProjet(Projet projet) {

		if (this.projet != null) {
			this.projet.getTaches().remove(this);
		}

		this.projet = projet;

		if (projet != null) {

			this.projet.getTaches().add(this);
		}
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		if (this.utilisateur != null) {
			this.utilisateur.getTaches().remove(this);
		}

		this.utilisateur = utilisateur;

		if (utilisateur != null) {
			this.utilisateur.getTaches().add(this);
		}
	}

	public Projet getProjet() {
		return projet;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public StatutTache getStatut() {
		return statut;
	}

	public void setStatut(StatutTache statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "Tache [id=" + id + ", description=" + description + ", duree=" + duree + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", statut=" + statut ;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tache )) return false;
        return id != 0 && id == ((Tache) o).getId();
    }
 
    @Override
    public int hashCode() {
        return 31;
    }

}
