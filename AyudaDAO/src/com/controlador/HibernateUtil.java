package com.controlador;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * PATRON SINGLETON PARA CREAR UNA F�BRICA DE CONEXIONES A LA BD
 * es un patron singleton porque s�lo puede haber una �nica conexi�n 
 * a la BD, por tanto, nos aseguramos con esta forma de codificar que
 * simpre tendremos �nicamente una sola conexi�n.
 * 
 * @author Eva Royo
 * @version 1.0 
 *
 */
public class HibernateUtil { 
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure() ;          	
			return configuration.buildSessionFactory(builder.build());
		}
		catch (Throwable ex) {
			// Nos aseguramos de que vamos a registrar la excepci�n
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

