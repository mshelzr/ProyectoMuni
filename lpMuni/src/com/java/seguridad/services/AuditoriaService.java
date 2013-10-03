package com.java.seguridad.services;

import com.java.beans.AuditoriaDTO;
import com.java.factoria.DAOFactory;
import com.java.seguridad.daos.AuditoriaDAO;
import com.java.util.Constantes;

public class AuditoriaService {

	DAOFactory fabrica=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	AuditoriaDAO objAuditoriaDAO = fabrica.getAuditoriaDAO();	
	
	public void registraUsuarioLogin(AuditoriaDTO audi) {
		objAuditoriaDAO.registraUsuarioLogueado(audi);
	}

	public void actualizaLogout(AuditoriaDTO audi) {
		objAuditoriaDAO.actualizaLogout(audi);		
	}
}
