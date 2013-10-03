package com.java.seguridad.daos;

import org.hibernate.Session;

import com.java.beans.AuditoriaDTO;
import com.java.util.HbnConexion;

public class HbnAuditoriaDao implements AuditoriaDAO {

	@Override
	public void registraUsuarioLogueado(AuditoriaDTO audi) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(audi);
		s.getTransaction().commit();
	}

	@Override
	public void actualizaLogout(AuditoriaDTO audi) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(audi);
		s.getTransaction().commit();
	}

}
