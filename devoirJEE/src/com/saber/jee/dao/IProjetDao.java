package com.saber.jee.dao;

import java.util.List;

import com.saber.jee.model.Projet;

public interface IProjetDao extends IDao{
	
	public void persist(Projet projet);

	public void update(Projet projet);

	public Projet findById(String id);

	public void delete(Projet projet);

	public List<Projet> findAll();

}
