package com.java.seguridad.daos;

import com.java.beans.AuditoriaDTO;

public interface AuditoriaDAO {
	// Método que recibe un objeto AUDITORIA y registra
	// sus datos en la tabla tb_Auditoria(fechaLogin, fechaLogout, etc)
	public void registraUsuarioLogueado(AuditoriaDTO audi);

	public void actualizaLogout(AuditoriaDTO audi);
	
	
}
