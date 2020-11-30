package com.saber.jee.service.imp;

import java.util.List;

import com.saber.jee.dao.ITacheDao;
import com.saber.jee.dao.imp.TacheDaoImp;
import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.ITacheService;

public class TacheServiceImp implements ITacheService {

	static private ITacheDao tacheDao;

	public TacheServiceImp() {
		tacheDao = new TacheDaoImp();
	}

	@Override
	public int add(Tache tache) {
		tacheDao.openTransactionalSession();
		int id = tacheDao.persist(tache);
		tacheDao.closeTransactionalSession();
		return id;
	}

	@Override
	public Tache getById(int id) {
		tacheDao.openSession();
		Tache tache = tacheDao.findById(id);
		tacheDao.closeSession();
		return tache;
	}

	@Override
	public void edit(Tache tache) {
		tacheDao.openTransactionalSession();
		tacheDao.update(tache);
		tacheDao.closeTransactionalSession();

	}

	@Override
	public void delete(Tache tache) {
		tacheDao.openTransactionalSession();
		tacheDao.delete(tache);
		tacheDao.closeTransactionalSession();

	}

	@Override
	public List<Tache> getAll() {
		tacheDao.openSession();
		List<Tache> taches = tacheDao.findAll();
		tacheDao.closeSession();
		return taches;
	}



	@Override
	public void desassocierTaches(Utilisateur utilisateur) {
		tacheDao.desassocierTaches(utilisateur.getId());
		
	}
}
