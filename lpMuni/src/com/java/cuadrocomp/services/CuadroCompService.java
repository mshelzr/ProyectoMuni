package com.java.cuadrocomp.services;

import com.java.beans.CuadroComparativoDTO;
import com.java.cuadrocomp.daos.CuadroCompDAO;
import com.java.factoria.DAOFactory;
import com.java.util.Constantes;

public class CuadroCompService {
	DAOFactory factory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	CuadroCompDAO ccDAO=factory.getCuadroCompDAO();
	
	public void procesarCuadroCompService(CuadroComparativoDTO cc){
		ccDAO.adjuntaCuadroCompDAO(cc);
	}
	public String obtenerCuadroCompService(int codExpediente){
		return ccDAO.obtenerUrlCuadroComparativo(codExpediente);
	}
}
