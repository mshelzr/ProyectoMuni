package com.java.seguridad.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.java.beans.ProveedorDTO;
import com.java.beans.SolicitudCotizacionHasProveedorDTO;
import com.java.solicitudcotiz.daos.SolicitudCotizDAO;
import com.java.util.HbnConexion;

public class HbnSolicitudCotizDao implements SolicitudCotizDAO {

	@Override
	public void registrarAlDetalleLosProveedoresInvitados(SolicitudCotizacionHasProveedorDTO scHasprov) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(scHasprov);
		s.getTransaction().commit();
	}
	
	@Override
	public int obtenerCodSolicitudCotizDAO(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT sc.codSolicitudCotizacion FROM SolicitudCotizacionDTO sc " +
							  "WHERE sc.expedienteCompraDTO.codExpediente=:codExpediente")
							  .setParameter("codExpediente", codExpediente);
		int codSolicitudCotizacion=(int)q.uniqueResult();
		s.getTransaction().commit();
		
		return codSolicitudCotizacion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorDTO> listarProveedoresInvitadosExpediente(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT pv FROM ProveedorDTO pv JOIN pv.solicitudCotizacionHasProveedorDTOs has " +
							  "JOIN has.solicitudCotizacionDTO sc " +
							  "WHERE sc.expedienteCompraDTO.codExpediente=:codExpediente")
							  .setParameter("codExpediente", codExpediente);
		List<ProveedorDTO> obj=(List<ProveedorDTO>)q.list();
		s.getTransaction().commit();
		return obj;
	}

	@Override
	public void modificarDetalleProveedorSolicitud(SolicitudCotizacionHasProveedorDTO scHasprov) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(scHasprov);
		s.getTransaction().commit();
	}

}
