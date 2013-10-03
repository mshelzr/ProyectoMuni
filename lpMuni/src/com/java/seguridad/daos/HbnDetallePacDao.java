package com.java.seguridad.daos;

import java.util.List;

import org.hibernate.Session;

import com.java.beans.DetallePacDTO;
import com.java.formato.daos.DetallePacDAO;
import com.java.util.HbnConexion;

public class HbnDetallePacDao implements DetallePacDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DetallePacDTO> listadoDetallePac() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		List<DetallePacDTO> listaDetallePAc=(List<DetallePacDTO>)s.createCriteria(DetallePacDTO.class).list();		
		s.getTransaction().commit();
		return listaDetallePAc;
	}

	@Override
	public void registraDetallePac(DetallePacDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
	}

	@Override
	public void eliminaDetallePac(int cod) {
		DetallePacDTO detallePac=new DetallePacDTO();
		detallePac.setCodDetallePac(cod);
		
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.delete(detallePac);
		s.getTransaction().commit();
	}

	@Override
	public void actualizaDetallePac(DetallePacDTO obj) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
	}

}
