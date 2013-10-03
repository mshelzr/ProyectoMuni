package com.java.seguridad.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.java.beans.ExpedienteCompraDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.expediente.daos.ExpedienteCompraDAO;
import com.java.util.HbnConexion;

public class HbnExpedienteCompraDao implements ExpedienteCompraDAO {

	@Override
	public void persistProcesoExpedienteCompra(ExpedienteCompraDTO ec){
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.persist(ec);
		s.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedienteCompraDTO> listarExpedienteCompraDAO(int etapa) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT ec FROM RegistroFormatoExpedienteDTO rfe JOIN rfe.expedienteCompraDTO ec " +
				"WHERE rfe.posicion=:posicion " +
				"AND ec.codExpediente NOT IN (SELECT rfesub.expedienteCompraDTO.codExpediente FROM RegistroFormatoExpedienteDTO rfesub WHERE rfesub.posicion=:posiNext) " +
				"AND (rfe.aprobado='y' OR rfe.firmado='y') AND rfe.expedienteCompraDTO.codExpediente like '%'")
				.setParameter("posicion", etapa)
				.setParameter("posiNext", etapa+1);
		List<ExpedienteCompraDTO> lista_e_c=(List<ExpedienteCompraDTO>)q.list();
		s.getTransaction().commit();
		return lista_e_c;
	}

	//Deprecated
	@Override
	public void registrarExpedienteCompraDAO(ExpedienteCompraDTO ec) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(ec);
		s.getTransaction().commit();
	}

	@Override
	public void procesarLogExpediente(RegistroFormatoExpedienteDTO registro_f_e) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(registro_f_e);
		s.getTransaction().commit();
	}

	@Override
	public String obtenerUrlSolicitudCotizacion(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT sc.url FROM SolicitudCotizacionDTO sc JOIN sc.expedienteCompraDTO ec " +
							  "WHERE ec.codExpediente=:codExpediente")
							  .setParameter("codExpediente", codExpediente);
		String url=(String)q.uniqueResult();
		s.getTransaction().commit();
		return url;
	}
	
	//expediente
	@Override
	public ExpedienteCompraDTO obtenerExpedienteCompraDAO(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		ExpedienteCompraDTO expediente=(ExpedienteCompraDTO)s.get(ExpedienteCompraDTO.class, codExpediente);
		s.getTransaction().commit();
		return expediente;
	}

	@Override
	public int cambiarExpXForm(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT fr.codFormato FROM FormatoRequerimientoDTO fr JOIN fr.expedienteCompraDTOs ec " +
							  "WHERE ec.codExpediente=:codExpediente");
		q.setInteger("codExpediente", codExpediente);
		int codFormato=(int)q.uniqueResult();
		s.getTransaction().commit();
		
		return codFormato;
	}

	@Override
	public List<String> listarTodasLasUrlDeDocumentosPorExpediente(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		String urlSolicitudCotizacion=(String)s.createQuery("SELECT sc.url FROM SolicitudCotizacionDTO sc " +
															"WHERE sc.expedienteCompraDTO.codExpediente=:codExpediente")
															.setParameter("codExpediente", codExpediente).uniqueResult();
		
		String urlCuadroComparativo=(String)s.createQuery("SELECT cc.url FROM CuadroComparativoDTO cc " +
														  "WHERE cc.expedienteCompraDTO.codExpediente=:codExpediente")
														  .setParameter("codExpediente", codExpediente).uniqueResult();
		
		String urlOrdenCompra=(String)s.createQuery("SELECT oc.url FROM OrdenCompraDTO oc WHERE oc.expedienteCompraDTO.codExpediente=:codExpediente")
													.setParameter("codExpediente",codExpediente).uniqueResult();
		
		List<String> urls=new ArrayList<String>();
		urls.add(urlSolicitudCotizacion);
		if(urlCuadroComparativo!=null)
			urls.add(urlCuadroComparativo);
		if(urlOrdenCompra!=null)
			urls.add(urlOrdenCompra);
		s.getTransaction().commit();
		
		return urls;
	}

}
