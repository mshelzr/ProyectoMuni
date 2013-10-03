package com.java.solicitudcotiz.daos;

import java.util.List;

import com.java.beans.ProveedorDTO;
import com.java.beans.SolicitudCotizacionHasProveedorDTO;

public interface SolicitudCotizDAO {

	public int obtenerCodSolicitudCotizDAO(int codExpediente);
	public List<ProveedorDTO> listarProveedoresInvitadosExpediente(int codExpediente);
	public void modificarDetalleProveedorSolicitud(SolicitudCotizacionHasProveedorDTO scHasprov);
	public void registrarAlDetalleLosProveedoresInvitados(SolicitudCotizacionHasProveedorDTO scHasprov);

}
