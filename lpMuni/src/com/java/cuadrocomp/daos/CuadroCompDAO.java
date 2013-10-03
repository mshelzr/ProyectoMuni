package com.java.cuadrocomp.daos;

import com.java.beans.CuadroComparativoDTO;

public interface CuadroCompDAO {

	public void adjuntaCuadroCompDAO(CuadroComparativoDTO cc);
	public String obtenerUrlCuadroComparativo(int codExpediente);
}
