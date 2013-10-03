package com.java.expediente.daos;

import java.util.List;

import com.java.beans.ExpedienteCompraDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;

public interface ExpedienteCompraDAO {
	public List<ExpedienteCompraDTO> listarExpedienteCompraDAO(int etapa);
	
	//Persistencia : 1 x 3
	public void persistProcesoExpedienteCompra(ExpedienteCompraDTO ec);
	public void procesarLogExpediente(RegistroFormatoExpedienteDTO registro_f_e);
	public void registrarExpedienteCompraDAO(ExpedienteCompraDTO ec);	
	public String obtenerUrlSolicitudCotizacion(int codExpediente);
	public ExpedienteCompraDTO obtenerExpedienteCompraDAO(int codExpediente);
	public int cambiarExpXForm(int codExpediente);
	
	//Proceso para el cuerpo del expediente
	public List<String> listarTodasLasUrlDeDocumentosPorExpediente(int codExpediente); 
}
