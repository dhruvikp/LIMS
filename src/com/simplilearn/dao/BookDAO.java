package com.simplilearn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.simplilearn.entity.Book;
import com.simplilearn.util.HibernateUtil;

public class BookDAO {

	public static List<Book> listBooks(String bookName) {
		Session session = null;
		List<Book> books = null;
		try {
			// 1. Build session factory
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			// 2. Create session object
			session = sessionFactory.openSession();

			// 3. Define Query and execute it
			books = session.createQuery("from Book b where b.bookName like '%"+  bookName +"%'").list();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return books;

	}
}
