package com.saber.jee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero")
	private int id;

	private String nom;

	private String prenom;

	@Column(unique = true)
	private String email;

	@Column(name = "mot_passe")
	private String motdepasse;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "utilisateur", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Tache> taches = new ArrayList<>();

	public Utilisateur() {

	}

	public Utilisateur(String email, String motdepasse) {
		super();
		this.email = email;
		this.motdepasse = motdepasse;
	}

	public Utilisateur(String nom, String prenom, String email, String motdepasse, Role role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motdepasse = motdepasse;
		this.role = role;
	}
	
	public void addTache(Tache tache) {
		tache.setUtilisateur(this);
	}

	public void removeTache(Tache tache) {
		tache.setUtilisateur(null);
	}
	
	public String getFullName() {
		
		return this.prenom +" "+ this.nom;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motdepasse="
				+ motdepasse + ", role=" + role + "]";
	}

}
