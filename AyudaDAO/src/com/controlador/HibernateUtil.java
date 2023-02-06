package com.controlador;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * PATRON SINGLETON PARA CREAR UNA F�BRICA DE CONEXIONES A LA BD
 * es un patr�n singleton porque s�lo puede haber una �nica conexi�n 
 * a la BD, por tanto, nos aseguramos con esta forma de codificar que
 * simpre tendremos �nicamente una sola conexi�n.
 * 
 * @author Eva Royo
 * @version 1.0 
 *
 */
public class HibernateUtil { 

	// DEFINIMOS UNA VARIABLE QUE ES FINAL
	private static final SessionFactory sessionFactory = buildSessionFactory();

	// DEFINIMOS UN M�TODO EST�TICO para construir la sesi�n
	private static SessionFactory buildSessionFactory() {
		try {
			//creamos la f�brica de sesiones utilizando hibernate.cfg.xml
			Configuration configuration = new Configuration().configure("ayuda/cfg/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure() ;          	
			return configuration.buildSessionFactory(builder.build());
			
			//lo anterior tambien lo podemos poner asi    	       	
			//return new Configuration().configure().buildSessionFactory(
			//			new StandardServiceRegistryBuilder().configure().build() );		
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

