package com.java.beans;

// Generated 05-sep-2013 3:08:07 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * ProveedorDTO generated by hbm2java
 */
public class ProveedorDTO implements java.io.Serializable {

	private int codProveedor;
	private String nombre;
	private Integer ruc;
	private Integer telefono;
	private String correo;
	private Set<SolicitudCotizacionHasProveedorDTO> solicitudCotizacionHasProveedorDTOs = new HashSet<SolicitudCotizacionHasProveedorDTO>(
			0);

	public ProveedorDTO() {
	}

	public ProveedorDTO(
			String nombre,
			Integer ruc,
			Integer telefono,
			String correo,
			Set<SolicitudCotizacionHasProveedorDTO> solicitudCotizacionHasProveedorDTOs) {
		this.nombre = nombre;
		this.ruc = ruc;
		this.telefono = telefono;
		this.correo = correo;
		this.solicitudCotizacionHasProveedorDTOs = solicitudCotizacionHasProveedorDTOs;
	}

	public int getCodProveedor() {
		return this.codProveedor;
	}

	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getRuc() {
		return this.ruc;
	}

	public void setRuc(Integer ruc) {
		this.ruc = ruc;
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Set<SolicitudCotizacionHasProveedorDTO> getSolicitudCotizacionHasProveedorDTOs() {
		return this.solicitudCotizacionHasProveedorDTOs;
	}

	public void setSolicitudCotizacionHasProveedorDTOs(
			Set<SolicitudCotizacionHasProveedorDTO> solicitudCotizacionHasProveedorDTOs) {
		this.solicitudCotizacionHasProveedorDTOs = solicitudCotizacionHasProveedorDTOs;
	}

}
