package com.saber.jee.service;

import java.util.List;

import com.saber.jee.model.Utilisateur;

public interface IUtilisateurService {

	public int add(Utilisateur utilisateur);
	public Utilisateur getById(int id);
	public void edit(Utilisateur utilisateur);
	public void delete(Utilisateur utilisateur);
	public List<Utilisateur> getAll();
	public Utilisateur authenticate(String email, String motdepasse);
}
