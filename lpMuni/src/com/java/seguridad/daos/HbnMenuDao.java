package com.java.seguridad.daos;

import java.util.List;

import org.hibernate.Session;

import com.java.beans.MenuDTO;
import com.java.util.HbnConexion;

public class HbnMenuDao implements MenuDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDTO> listadoMenu() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		List<MenuDTO> menu=(List<MenuDTO>)s.createCriteria(MenuDTO.class).list();
		s.getTransaction().commit();
		return menu;
	}

	@Override
	public void registraMenu(MenuDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
	}

	@Override
	public void eliminaMenu(int cod) {
		MenuDTO menu=new MenuDTO();
		menu.setCodMenu(cod);
		
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.delete(menu);
		s.getTransaction().commit();
	}

	@Override
	public void actualizaMenu(MenuDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
	}

}
