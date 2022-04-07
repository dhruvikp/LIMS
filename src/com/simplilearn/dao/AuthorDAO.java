package com.simplilearn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Author;
import com.simplilearn.entity.Book;
import com.simplilearn.util.HibernateUtil;

public class AuthorDAO {
	
	
	public static List<Author> listAuthors() {
		Session session = null;
		List<Author> authors = null;
		try {
			// 1. Build session factory
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			// 2. Create session object
			session = sessionFactory.openSession();

			// 3. Define Query and execute it
			authors = session.createQuery("from Author").list();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return authors;
	}
}
