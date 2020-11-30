package com.saber.jee.dao.imp;

import java.util.List;

import com.saber.jee.dao.ITacheDao;
import com.saber.jee.model.Tache;
import com.saber.jee.model.Utilisateur;


public class TacheDaoImp extends DaoImp implements ITacheDao{

	@Override
	public int persist(Tache tache) {
		return (int) getSession().save(tache);
		
	}

	@Override
	public void update(Tache tache) {
		getSession().merge(tache);
		
	}

	@Override
	public Tache findById(int id) {
		return (Tache) getSession().get(Tache.class, id);
	}

	@Override
	public void delete(Tache tache) {
		getSession().delete(tache);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tache> findAll() {
		return (List<Tache>) getSession().createQuery("FROM Tache").list();
		
	}

//	@Override
//	public void desassocierTaches(int idUtilisateur) {
//		// TODO Auto-generated method stub
//		
//	}
//	
	public void desassocierTaches(int id) {
		String sql = "UPDATE Tache t SET t.utilisateur = NULL WHERE t.utilisateur.id = :id";
		getSession().createQuery(sql).setParameter("id", id).executeUpdate();
	}



}
