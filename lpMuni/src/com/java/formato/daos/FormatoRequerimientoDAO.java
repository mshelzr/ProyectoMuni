package com.java.formato.daos;

import java.util.List;

import com.java.beans.DetallePacDTO;
import com.java.beans.FormatoRequerimientoDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;

public interface FormatoRequerimientoDAO {
	
	public List<FormatoRequerimientoDTO> listarFormatoRequerimientoDAO(int etapa);

	public void registrarFormatoRequerimientoDAO(FormatoRequerimientoDTO fr);
	public List<DetallePacDTO> listarDetallePacDAO();

	public FormatoRequerimientoDTO obtenerFormatoReqDAO(int codFormato);
	public List<Object[]> listarFirmaUsuarioDAO(int codFormato);
	public DetallePacDTO obtieneDetallePacPorFormato(int codFormato);

	public void procesarLogFormato(RegistroFormatoExpedienteDTO instance);

	public List<FormatoRequerimientoDTO> obtenerEstadosDeFormatosReq(int codUsuario);
	public List<FormatoRequerimientoDTO> obtenerEstadosDeExpedienteCompra(int codUsuario);
	public void modificarEstadoDelFormato(int codFormato,String posicion,String aprobado,String firmado);
	public void modificarEstadoDelExpediente(int codExpediente,String posicion,String aprobado,String firmado);

}
