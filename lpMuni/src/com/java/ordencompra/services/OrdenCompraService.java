package com.java.ordencompra.services;

import com.java.beans.OrdenCompraDTO;
import com.java.beans.ProveedorDTO;
import com.java.factoria.DAOFactory;
import com.java.ordencompra.daos.OrdenCompraDAO;
import com.java.util.Constantes;

public class OrdenCompraService {
	DAOFactory factory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	OrdenCompraDAO ocDAO=factory.getOrdenCompraDAO();
	
	public ProveedorDTO obtenerCodProveedorElegido(int codExpediente){
		return ocDAO.obtenerCodProveedorElegido(codExpediente);
	}
	public void procesarOrdenCompra(OrdenCompraDTO oc) {
		ocDAO.adjuntarOrdenCompra(oc);
	}
	public String obtenerUrlOrdenCompraParaEnviarEmail(int codExpediente) {
		return ocDAO.obtenerUrlOrdenCompra(codExpediente);
	}
}
