package com.java.ordencompra.daos;

import com.java.beans.OrdenCompraDTO;
import com.java.beans.ProveedorDTO;

public interface OrdenCompraDAO {

	public ProveedorDTO obtenerCodProveedorElegido(int codExpediente);
	public void adjuntarOrdenCompra(OrdenCompraDTO oc);
	public String obtenerUrlOrdenCompra(int codExpediente);
}
