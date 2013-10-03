package com.java.formato.services;

import java.util.List;

import com.java.beans.DetallePacDTO;
import com.java.beans.FormatoRequerimientoDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.factoria.DAOFactory;
import com.java.formato.daos.FormatoRequerimientoDAO;
import com.java.util.Constantes;

public class FormatoRequerimientoService {

	DAOFactory factory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	FormatoRequerimientoDAO frDAO=factory.getFormatoRequerimientoDAO();
		
	public void registrarFormatoRequerimientoService(FormatoRequerimientoDTO fr){		
		frDAO.registrarFormatoRequerimientoDAO(fr);		
	}
	
	//Por cada proceso de formulario se registra en la tabla detalle_trabajadorformato
	public void procesarLogFormato(RegistroFormatoExpedienteDTO registro_f_e){
		frDAO.procesarLogFormato(registro_f_e);
		
	}
	//Para la grilla
	public List<FormatoRequerimientoDTO> listarFormatoRequerimientoServices(int etapa){
		return frDAO.listarFormatoRequerimientoDAO(etapa);
	}
	//para el headFormato
	public FormatoRequerimientoDTO obtenerFormatoReqService(int codFormato){
		return frDAO.obtenerFormatoReqDAO(codFormato);
	}
	//para generar el formato req
	public List<DetallePacDTO> listarDetallePac(){
		return frDAO.listarDetallePacDAO();
	}
	//para el headFormato
	public List<Object[]> listarFirmaUsuarioService(int codFormato){
		return frDAO.listarFirmaUsuarioDAO(codFormato);
	}
	public DetallePacDTO obtenerDetallePacService(int codFormato){
		return frDAO.obtieneDetallePacPorFormato(codFormato);
	}
	
	public List<FormatoRequerimientoDTO> obtenerEstadosDeFormatosReq(int codUsuario){
		return frDAO.obtenerEstadosDeFormatosReq(codUsuario);
	}
	
	public List<FormatoRequerimientoDTO> obtenerEstadosDeExpedienteCompra(int codUsuario){
		return frDAO.obtenerEstadosDeExpedienteCompra(codUsuario);
	}
	
	public void modificarEstadoDelFormato(int codFormato,String posicion,String aprobado,String firmado){
		frDAO.modificarEstadoDelFormato(codFormato, posicion, aprobado, firmado);
	}
	
	public void modificarEstadoDelExpediente(int codExpediente,String posicion,String aprobado,String firmado){
		frDAO.modificarEstadoDelExpediente(codExpediente, posicion, aprobado, firmado);
	}

	
}
