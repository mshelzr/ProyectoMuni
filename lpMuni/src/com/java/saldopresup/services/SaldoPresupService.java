package com.java.saldopresup.services;

import com.java.expediente.daos.ExpedienteCompraDAO;
import com.java.factoria.DAOFactory;
import com.java.saldopresup.daos.SaldoPresupDAO;
import com.java.util.Constantes;

public class SaldoPresupService {
	DAOFactory factory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	SaldoPresupDAO spDAO=factory.getSaldoPresupDAO();
	ExpedienteCompraDAO expedienteCompraDao=factory.getExpedienteCompraDAO();
	
	public double valorEnUit(int codFormato){
		return spDAO.getValorDelPacEnUitPorFormato(codFormato);
	}
	public double uitInicial(){
		return spDAO.getRangoUnoDeUit();
	}
	public double uitFinal(){
		return spDAO.getRangoDosDeUit();
	}
	public double getSaldoBase() {
		return spDAO.getSaldoBase();
	}

}
