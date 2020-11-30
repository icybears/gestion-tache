package com.saber.jee.dao.imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.saber.jee.dao.IDao;
import com.saber.jee.util.HibernateUtil;

public class DaoImp implements IDao {
	
	 private Session session;

	 private SessionFactory factory;
	 	
	 	@Override
		public Session openSession() {
		session = getSessionFactory().openSession();
			return session;
	 	}
	 	
	 	@Override
		public Session openTransactionalSession() {
			session = getSessionFactory().openSession();
			session.beginTransaction();
			return session;
	 	}	
	 	
	 
	 	@Override
		public void closeSession() {
	 		session.close();
	 	}
	 	
	 
	 	@Override
		public void closeTransactionalSession() {
	 		session.getTransaction().commit();
	 		session.close();
	 	}
	     
	    private static SessionFactory getSessionFactory() {
	    	
	    	return HibernateUtil.getSessionFactory();
	    	
	         
	    }
	 
	 
	    @Override
		public Session getSession() {
	        return session;
	    }
}
