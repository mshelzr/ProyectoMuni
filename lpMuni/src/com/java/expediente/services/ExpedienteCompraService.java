package com.java.expediente.services;

import java.util.List;

import com.java.beans.ExpedienteCompraDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.expediente.daos.ExpedienteCompraDAO;
import com.java.factoria.DAOFactory;
import com.java.util.Constantes;

public class ExpedienteCompraService {
	
	DAOFactory factory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	ExpedienteCompraDAO ecDAO=factory.getExpedienteCompraDAO();
	
	public List<ExpedienteCompraDTO> listarExpedienteCompraServices(int etapa){
		return ecDAO.listarExpedienteCompraDAO(etapa);
	}
	
	public void procesarExpedienteCompraServices(ExpedienteCompraDTO ec){
		ecDAO.persistProcesoExpedienteCompra(ec);
	}
	
	public void procesarLogExpediente(RegistroFormatoExpedienteDTO registro_f_e){
		ecDAO.procesarLogExpediente(registro_f_e);
	}
	
	public String obtenerUrlSolicitudCotizacionService(int codExpediente){
		return ecDAO.obtenerUrlSolicitudCotizacion(codExpediente);
	}
	
	//body expediente compra
	public ExpedienteCompraDTO obtenerExpedienteCompraService(int codExpediente){
		return ecDAO.obtenerExpedienteCompraDAO(codExpediente);
	}
	
	public int cambiarExpXformService(int codExpediente){
		return ecDAO.cambiarExpXForm(codExpediente);
	}
	
	//parte del body del Expediente
	public List<String> listarDocumentosPorExpediente(int codExpediente){
		return ecDAO.listarTodasLasUrlDeDocumentosPorExpediente(codExpediente);
	}
}
