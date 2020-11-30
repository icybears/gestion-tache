package com.saber.jee.dao.imp;

import java.util.List;

import javax.persistence.Query;

import com.saber.jee.dao.IUtilisateurDao;
import com.saber.jee.model.Utilisateur;

public class UtilisateurDaoImp extends DaoImp implements IUtilisateurDao {

	@Override
	public int persist(Utilisateur utilisateur) {
		return (int) getSession().save(utilisateur);
		
	}

	@Override
	public void update(Utilisateur utilisateur) {
		getSession().merge(utilisateur);
		
	}

	@Override
	public Utilisateur findById(int id) {
		return (Utilisateur) getSession().get(Utilisateur.class, id);
	}

	@Override
	public void delete(Utilisateur utilisateur) {
		getSession().delete(utilisateur);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAll() {
		return (List<Utilisateur>) getSession().createQuery("FROM Utilisateur").list();
		
	}

	@SuppressWarnings("unchecked")
	public Utilisateur findByEmailAndPassword(String email,String password) {
		String sql = "FROM Utilisateur u WHERE u.email = :email AND u.motdepasse = :password";
		Query query = getSession().createQuery(sql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		List<Utilisateur> result = (List<Utilisateur>)query.getResultList();
		
		return result.isEmpty() ? null : result.get(0);
		
	}
}
