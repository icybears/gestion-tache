package com.saber.jee.dao;

import org.hibernate.Session;

public interface IDao {

	Session openSession();

	Session openTransactionalSession();

	void closeSession();

	void closeTransactionalSession();

	Session getSession();

}