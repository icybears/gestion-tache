package com.saber.jee.service.imp;

import java.util.List;

import com.saber.jee.dao.ITacheDao;
import com.saber.jee.dao.IUtilisateurDao;
import com.saber.jee.dao.imp.TacheDaoImp;
import com.saber.jee.dao.imp.UtilisateurDaoImp;
import com.saber.jee.model.Utilisateur;
import com.saber.jee.service.ITacheService;
import com.saber.jee.service.IUtilisateurService;


public class UtilisateurServiceImp implements IUtilisateurService{

	static private IUtilisateurDao utilisateurDao;
	static private ITacheService tacheService;
	static private ITacheDao tacheDao;
	public UtilisateurServiceImp() {
		utilisateurDao = new UtilisateurDaoImp();
		tacheDao = new TacheDaoImp();
		tacheService = new TacheServiceImp();
	}

	@Override
	public int add(Utilisateur utilisateur) {
		utilisateurDao.openTransactionalSession();
		int id = utilisateurDao.persist(utilisateur);
		utilisateurDao.closeTransactionalSession();
		return id;
	}

	@Override
	public Utilisateur getById(int id) {
		utilisateurDao.openSession();
		Utilisateur Utilisateur = utilisateurDao.findById(id);
		utilisateurDao.closeSession();
		return Utilisateur;
	}

	@Override
	public void edit(Utilisateur utilisateur) {
		utilisateurDao.openTransactionalSession();
		utilisateurDao.update(utilisateur);
		utilisateurDao.closeTransactionalSession();

	}

	@Override
	public void delete(Utilisateur utilisateur) {
		

		tacheDao.openTransactionalSession();
		tacheDao.desassocierTaches(utilisateur.getId());
		tacheDao.closeTransactionalSession();
	
		
		utilisateurDao.openTransactionalSession();
		
		utilisateurDao.delete(utilisateur);
		
		utilisateurDao.closeTransactionalSession();

	}

	@Override
	public List<Utilisateur> getAll() {
		utilisateurDao.openSession();
		List<Utilisateur> utilisateurs = utilisateurDao.findAll();
		utilisateurDao.closeSession();
		return utilisateurs;
	}
	
	public Utilisateur authenticate(String email, String motdepasse) {
		utilisateurDao.openSession();
		Utilisateur utilisateur = utilisateurDao.findByEmailAndPassword(email, motdepasse);
		utilisateurDao.closeSession();
		return utilisateur;
	}
}
