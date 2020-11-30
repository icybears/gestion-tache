package com.saber.jee.dao.imp;

import java.util.List;

import com.saber.jee.dao.IProjetDao;
import com.saber.jee.model.Projet;


public class ProjetDaoImp extends DaoImp implements IProjetDao {

	@Override
	public void persist(Projet Projet) {
		getSession().save(Projet);
		
	}

	@Override
	public void update(Projet Projet) {
		getSession().merge(Projet);
		
	}

	@Override
	public Projet findById(String id) {
		return (Projet) getSession().get(Projet.class, id);
	}

	@Override
	public void delete(Projet Projet) {
		getSession().delete(Projet);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projet> findAll() {
		return (List<Projet>) getSession().createQuery("FROM Projet").list();
		
	}

}
