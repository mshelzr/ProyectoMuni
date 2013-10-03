package com.java.formato.daos;

import java.util.List;

import com.java.beans.DetallePacDTO;


public interface DetallePacDAO {
	
	public List<DetallePacDTO> listadoDetallePac();
	public void registraDetallePac(DetallePacDTO obj);
	public void eliminaDetallePac(int cod);
	public void actualizaDetallePac(DetallePacDTO obj);

}
