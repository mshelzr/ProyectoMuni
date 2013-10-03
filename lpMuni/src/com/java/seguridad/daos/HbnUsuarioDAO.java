package com.java.seguridad.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.java.beans.MenuDTO;
import com.java.beans.UsuarioDTO;
import com.java.util.HbnConexion;

public class HbnUsuarioDAO implements UsuarioDAO{

	@Override
	public UsuarioDTO buscaPorUsuario(String vUsuario) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		UsuarioDTO obj=new UsuarioDTO();
		try {
			s.beginTransaction();

			Criteria criteria=s.createCriteria(UsuarioDTO.class)
					.add(Restrictions.eq("usuario", vUsuario)).
					setFetchMode("perfilDTO", FetchMode.JOIN);
			obj=(UsuarioDTO)criteria.uniqueResult();

			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(s.isOpen()){
				s.close();
			}
		}

		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDTO> buscarMenu_DAO(String vUsuario) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT m " +
				"FROM MenuDTO m join m.perfilDTO p join p.usuarioDTOs u " +
				"WHERE u.usuario=:usuario");
		q.setString("usuario", vUsuario);
		List<MenuDTO> objresult=(List<MenuDTO>)q.list();
		s.getTransaction().commit();

		return objresult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioDTO> listadoUsuarios() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		List<UsuarioDTO> u=(List<UsuarioDTO>)s.createCriteria(UsuarioDTO.class).list();		
		s.getTransaction().commit();
		return u;
	}

	@Override
	public void registraUsuario(UsuarioDTO objUsuario) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(objUsuario);
		s.getTransaction().commit();
	}

	@Override
	public void eliminaUsuario(int codUsuario) {
		UsuarioDTO user=new UsuarioDTO();
		user.setCodUsuario(codUsuario);

		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.delete(user);
		s.getTransaction().commit();
	}

	@Override
	public void actualizaUsuario(UsuarioDTO objUsua) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(objUsua);
		s.getTransaction().commit();
	}

}
