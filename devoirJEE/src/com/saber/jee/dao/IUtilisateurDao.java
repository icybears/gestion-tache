package com.saber.jee.dao;

import java.util.List;

import com.saber.jee.model.Utilisateur;

public interface IUtilisateurDao extends IDao{

	public int persist(Utilisateur utilisateur);

	public void update(Utilisateur utilisateur);

	public Utilisateur findById(int id);

	public void delete(Utilisateur utilisateur);

	public List<Utilisateur> findAll();
	
	public Utilisateur findByEmailAndPassword(String email,String password);

}
