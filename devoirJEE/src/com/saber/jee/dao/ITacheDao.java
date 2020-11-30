package com.saber.jee.dao;

import java.util.List;

import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;

public interface ITacheDao extends IDao {
	
	public int persist(Tache tache);

	public void update(Tache tache);

	public Tache findById(int id);

	public void delete(Tache tache);

	public List<Tache> findAll();
	
	public void desassocierTaches(int id);

}
