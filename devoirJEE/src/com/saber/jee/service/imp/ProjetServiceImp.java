package com.saber.jee.service.imp;

import java.util.List;

import com.saber.jee.dao.IProjetDao;
import com.saber.jee.dao.imp.ProjetDaoImp;
import com.saber.jee.model.Projet;
import com.saber.jee.service.IProjetService;

public class ProjetServiceImp implements IProjetService{

	static private IProjetDao projetDao;

	public ProjetServiceImp() {
		projetDao = new ProjetDaoImp();
	}

	@Override
	public String add(Projet projet) {
		projetDao.openTransactionalSession();
		projetDao.persist(projet);
		projetDao.closeTransactionalSession();
		
		return projet.getId();
	}

	@Override
	public Projet getById(String id) {
		projetDao.openSession();
		Projet Projet = projetDao.findById(id);
		projetDao.closeSession();
		return Projet;
	}

	@Override
	public void edit(Projet projet) {
		projetDao.openTransactionalSession();
		projetDao.update(projet);
		projetDao.closeTransactionalSession();

	}

	@Override
	public void delete(Projet projet) {
		projetDao.openTransactionalSession();
		projetDao.delete(projet);
		projetDao.closeTransactionalSession();

	}

	@Override
	public List<Projet> getAll() {
		projetDao.openSession();
		List<Projet> projets = projetDao.findAll();
		projetDao.closeSession();
		return projets;
	}
	
	
}
