package com.java.solicitudcotiz.services;

import java.util.List;

import com.java.beans.ProveedorDTO;
import com.java.beans.SolicitudCotizacionHasProveedorDTO;
import com.java.expediente.daos.ExpedienteCompraDAO;
import com.java.factoria.DAOFactory;
import com.java.solicitudcotiz.daos.ProveedorDAO;
import com.java.solicitudcotiz.daos.SolicitudCotizDAO;
import com.java.util.Constantes;

public class SolicitudCotizService {
	DAOFactory factory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	SolicitudCotizDAO scDAO=factory.getSolicitudCotizDAO();
	ExpedienteCompraDAO expedienteCompraDao=factory.getExpedienteCompraDAO();
	ProveedorDAO provDao=factory.getProveedorDao();
	
	public List<ProveedorDTO> listaProveedorService(){
		return provDao.listadoProveedor();
	}
		
	public int obtenerCodSolicitudCotizService(int codExpediente){
		return scDAO.obtenerCodSolicitudCotizDAO(codExpediente);
	}
	
	public List<ProveedorDTO> listaProveedorPorExpediente(int codExpediente){
		return scDAO.listarProveedoresInvitadosExpediente(codExpediente);
	}
	
	public void modificarDetalleProveedorSolicitudService(SolicitudCotizacionHasProveedorDTO scHasprov){
		scDAO.modificarDetalleProveedorSolicitud(scHasprov);
	}

	public void registrarAlDetalleLosProveedoresInvitados(SolicitudCotizacionHasProveedorDTO scHasprov) {
		scDAO.registrarAlDetalleLosProveedoresInvitados(scHasprov);
	}
	
//	public List<ProveedorDTO> listaCorreoProveedorService(String codProveedor){
//		return scDAO.listaCorreoProveedorDAO(codProveedor);
//	}
}
