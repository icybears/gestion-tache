package com.saber.jee.service;

import java.util.List;

import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;

public interface ITacheService {
	public int add(Tache tache);
	public Tache getById(int id);
	public void edit(Tache tache);
	public void delete(Tache tache);
	public List<Tache> getAll();
	void desassocierTaches(Utilisateur utilisateur);
}
