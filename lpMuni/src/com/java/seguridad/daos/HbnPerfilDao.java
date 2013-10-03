package com.java.seguridad.daos;

import java.util.List;

import org.hibernate.Session;

import com.java.beans.PerfilDTO;
import com.java.util.HbnConexion;

public class HbnPerfilDao implements PerfilDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilDTO> listadoPerfil() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		List<PerfilDTO> listaPerfil=(List<PerfilDTO>)s.createCriteria(PerfilDTO.class).list();
		s.getTransaction().commit();
		return listaPerfil;
	}

	@Override
	public void registraPerfil(PerfilDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
	}

	@Override
	public void eliminaPerfil(int cod) {
		PerfilDTO obj=new PerfilDTO();
		obj.setCodPerfil(cod);
		
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
	}

	@Override
	public void actualizaPerfil(PerfilDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
	}

}
