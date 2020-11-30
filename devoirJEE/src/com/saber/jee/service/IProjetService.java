package com.saber.jee.service;

import java.util.List;

import com.saber.jee.model.Projet;

public interface IProjetService {

	public String add(Projet projet);
	public Projet getById(String id);
	public void edit(Projet projet);
	public void delete(Projet projet);
	public List<Projet> getAll();
}
