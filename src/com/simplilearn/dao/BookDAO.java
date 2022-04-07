package com.simplilearn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Book;
import com.simplilearn.util.HibernateUtil;

public class BookDAO {
	
	public static void issueBook(String bookId, String userName) {
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx= session.beginTransaction();
		List<Book> books = session.createQuery("from Book b where b.bookId ="+bookId).list();
		Book book = books.get(0);
		book.setIssueBy(userName);
		session.save(book);
		tx.commit();
		session.close();
	}

	public static List<Book> listBooks(String bookName) {
		Session session = null;
		List<Book> books = null;
		try {
			// 1. Build session factory
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			// 2. Create session object
			session = sessionFactory.openSession();

			// 3. Define Query and execute it
			books = session.createQuery("from Book b where b.bookName like '%"+  bookName +"%' and b.issueBy is null").list();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return books;
	}
	
	public static List<Book> listBooks() {
		Session session = null;
		List<Book> books = null;
		try {
			// 1. Build session factory
			SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
			// 2. Create session object
			session = sessionFactory.openSession();

			// 3. Define Query and execute it
			books = session.createQuery("from Book").list();

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
