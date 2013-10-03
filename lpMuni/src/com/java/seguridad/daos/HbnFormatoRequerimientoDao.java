package com.java.seguridad.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.java.beans.DetallePacDTO;
import com.java.beans.FormatoRequerimientoDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.formato.daos.FormatoRequerimientoDAO;
import com.java.util.HbnConexion;

public class HbnFormatoRequerimientoDao implements FormatoRequerimientoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<FormatoRequerimientoDTO> listarFormatoRequerimientoDAO(int etapa) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT fr FROM RegistroFormatoExpedienteDTO rfe JOIN rfe.formatoRequerimientoDTO fr " +
				"WHERE rfe.posicion=:posicion " +
				"AND fr.codFormato NOT IN (SELECT rfesub.formatoRequerimientoDTO.codFormato FROM RegistroFormatoExpedienteDTO rfesub WHERE rfesub.posicion=:posiNext) " +
				"AND fr.codFormato NOT IN (SELECT ec.formatoRequerimientoDTO.codFormato FROM ExpedienteCompraDTO ec) " +
				"AND (rfe.aprobado='y' OR rfe.firmado='y') AND rfe.formatoRequerimientoDTO.codFormato like '%'");
		q.setParameter("posicion", etapa);
		q.setParameter("posiNext", etapa+1);
		List<FormatoRequerimientoDTO> listafrDtos=(List<FormatoRequerimientoDTO>)q.list();
		s.getTransaction().commit();

		return listafrDtos;
	}

	@Override
	public void registrarFormatoRequerimientoDAO(FormatoRequerimientoDTO fr) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.persist(fr);
		s.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetallePacDTO> listarDetallePacDAO() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		List<DetallePacDTO> listaDetallePac=(List<DetallePacDTO>)s.createCriteria(DetallePacDTO.class).list();
		s.getTransaction().commit();
		return listaDetallePac;
	}

	@Override
	public FormatoRequerimientoDTO obtenerFormatoReqDAO(int codFormato) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		FormatoRequerimientoDTO formatoReq=(FormatoRequerimientoDTO)s.get(FormatoRequerimientoDTO.class, codFormato);
		s.getTransaction().commit();

		return formatoReq;
	}

	//Lista Object[]
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarFirmaUsuarioDAO(int codFormato) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT u.firma, p.descripcion, u.nombre ||' '|| u.ape_paterno ||' '|| u.ape_materno " +
				"FROM RegistroFormatoExpedienteDTO rfe JOIN rfe.usuarioDTO u JOIN u.perfilDTO p " +
				"WHERE rfe.formatoRequerimientoDTO.codFormato=:codFormato AND rfe.firmado='y'")
				.setParameter("codFormato", codFormato);
		List<Object[]> iterator=q.list();
		s.getTransaction().commit();
		return iterator;
	}

	@Override
	public DetallePacDTO obtieneDetallePacPorFormato(int codFormato) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT dp FROM FormatoRequerimientoDTO fr JOIN fr.detallePacDTO dp " +
				"WHERE fr.codFormato=:formato");
		q.setParameter("formato", codFormato);
		DetallePacDTO detallePac=(DetallePacDTO)q.uniqueResult();
		s.getTransaction().commit();
		return detallePac;
	}

	@Override
	public void procesarLogFormato(RegistroFormatoExpedienteDTO instance) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(instance);
		s.getTransaction().commit();
	}
	
	//En Construcción...
	//Manejarlo con salida de Objectos y no entidad
	@Override
	public List<FormatoRequerimientoDTO> obtenerEstadosDeFormatosReq(int codUsuario) {

		return null;
	}

	//Manejarlo con salida de Objectos y no entidad
	@Override
	public List<FormatoRequerimientoDTO> obtenerEstadosDeExpedienteCompra(
			int codUsuario) {
		return null;
	}
	//En mantenimiento
	@Override
	public void modificarEstadoDelFormato(int codFormato, String posicion,
			String aprobado, String firmado) {
	}

	//En mantenimiento
	@Override
	public void modificarEstadoDelExpediente(int codExpediente,
			String posicion, String aprobado, String firmado) {
	}

}
