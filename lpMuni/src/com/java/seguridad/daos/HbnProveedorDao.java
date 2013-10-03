package com.java.seguridad.daos;

import java.util.List;

import org.hibernate.Session;

import com.java.beans.ProveedorDTO;
import com.java.solicitudcotiz.daos.ProveedorDAO;
import com.java.util.HbnConexion;

public class HbnProveedorDao implements ProveedorDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorDTO> listadoProveedor() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		List<ProveedorDTO> listaProveedores=(List<ProveedorDTO>)s.createCriteria(ProveedorDTO.class).list();
		s.getTransaction().commit();
		return listaProveedores;
	}

	@Override
	public void registraProveedor(ProveedorDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
	}

	@Override
	public void eliminaProveedor(int cod) {
		ProveedorDTO prov=new ProveedorDTO();
		prov.setCodProveedor(cod);
		
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.delete(prov);
		s.getTransaction().commit();
		
	}

	@Override
	public void actualizaProveedor(ProveedorDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
	}

}
