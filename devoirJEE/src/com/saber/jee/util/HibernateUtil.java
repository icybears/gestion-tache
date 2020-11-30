package com.saber.jee.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory factory = null;

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		return factory;
	}

}
