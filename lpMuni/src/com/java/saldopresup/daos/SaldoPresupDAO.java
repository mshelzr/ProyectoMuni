package com.java.saldopresup.daos;

public interface SaldoPresupDAO {

	public double getValorDelPacEnUitPorFormato(int codFormato);
	public double getRangoUnoDeUit();
	public double getRangoDosDeUit();
	public double getSaldoBase();
	
}
