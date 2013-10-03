package com.java.seguridad.daos;

import org.hibernate.Query;
import org.hibernate.Session;

import com.java.beans.OrdenCompraDTO;
import com.java.beans.ProveedorDTO;
import com.java.ordencompra.daos.OrdenCompraDAO;
import com.java.util.HbnConexion;

public class HbnOrdenCompraDao implements OrdenCompraDAO {

	@Override
	public ProveedorDTO obtenerCodProveedorElegido(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT pv FROM ProveedorDTO pv JOIN pv.solicitudCotizacionHasProveedorDTOs has " +
				  "JOIN has.solicitudCotizacionDTO sc " +
				  "WHERE sc.expedienteCompraDTO.codExpediente=:codExpediente AND has.elegido='y'")
				  .setParameter("codExpediente", codExpediente);
		ProveedorDTO prov=(ProveedorDTO)q.uniqueResult();
		s.getTransaction().commit();
		return prov;
	}

	@Override
	public void adjuntarOrdenCompra(OrdenCompraDTO oc) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(oc);
		s.getTransaction().commit();
	}

	@Override
	public String obtenerUrlOrdenCompra(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT oc.url FROM OrdenCompraDTO oc JOIN oc.expedienteCompraDTO ec " +
				  "WHERE ec.codExpediente=:codExpediente")
				  .setParameter("codExpediente", codExpediente);
		String url=(String)q.uniqueResult();
		s.getTransaction().commit();
		return url;
	}

}
