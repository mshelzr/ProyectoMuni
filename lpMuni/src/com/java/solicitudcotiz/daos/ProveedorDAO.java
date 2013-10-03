package com.java.solicitudcotiz.daos;

import java.util.List;

import com.java.beans.ProveedorDTO;

public interface ProveedorDAO {

	public List<ProveedorDTO> listadoProveedor();
	public void registraProveedor(ProveedorDTO obj);
	public void eliminaProveedor(int cod);
	public void actualizaProveedor(ProveedorDTO obj);
	
}
