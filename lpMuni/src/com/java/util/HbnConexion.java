package com.java.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HbnConexion {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    
    private static Logger log=Logger.getLogger(HbnConexion.class);
    static {
        try {
        	log.info("Comenzando la clase HIBERNATE-CONEXION");
        	Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            log.info("Saliendo de la clase HIBERNATE-CONEXION");
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
        	log.error("SURGIO UN PROBLEMA CON LA HIBERNATE-CONEXION");
        	ex.printStackTrace();
       }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}