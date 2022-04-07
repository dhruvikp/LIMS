package com.simplilearn.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.simplilearn.entity.Author;
import com.simplilearn.entity.Book;
import com.simplilearn.entity.Student;

public class HibernateUtil {

	static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() {

		if (sessionFactory == null) {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			cfg.addAnnotatedClass(Student.class);
			cfg.addAnnotatedClass(Book.class);
			cfg.addAnnotatedClass(Author.class);
			sessionFactory = cfg.buildSessionFactory();
		}
		return sessionFactory;
	}

}