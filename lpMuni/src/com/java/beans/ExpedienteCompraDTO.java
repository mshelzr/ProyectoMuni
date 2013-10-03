package com.java.beans;

// Generated 05-sep-2013 3:08:07 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * ExpedienteCompraDTO generated by hbm2java
 */
public class ExpedienteCompraDTO implements java.io.Serializable {

	private int codExpediente;
	private FormatoRequerimientoDTO formatoRequerimientoDTO;
	private String descripcion;
	private String fecha;
	private Set<OrdenCompraDTO> ordenCompraDTOs = new HashSet<OrdenCompraDTO>(0);
	private Set<CuadroComparativoDTO> cuadroComparativoDTOs = new HashSet<CuadroComparativoDTO>(
			0);
	private Set<SolicitudCotizacionDTO> solicitudCotizacionDTOs = new HashSet<SolicitudCotizacionDTO>(
			0);
	private Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDTOs = new HashSet<RegistroFormatoExpedienteDTO>(
			0);

	public ExpedienteCompraDTO() {
	}

	public ExpedienteCompraDTO(FormatoRequerimientoDTO formatoRequerimientoDTO) {
		this.formatoRequerimientoDTO = formatoRequerimientoDTO;
	}

	public ExpedienteCompraDTO(FormatoRequerimientoDTO formatoRequerimientoDTO,
			String descripcion, String fecha,
			Set<OrdenCompraDTO> ordenCompraDTOs,
			Set<CuadroComparativoDTO> cuadroComparativoDTOs,
			Set<SolicitudCotizacionDTO> solicitudCotizacionDTOs,
			Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDTOs) {
		this.formatoRequerimientoDTO = formatoRequerimientoDTO;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.ordenCompraDTOs = ordenCompraDTOs;
		this.cuadroComparativoDTOs = cuadroComparativoDTOs;
		this.solicitudCotizacionDTOs = solicitudCotizacionDTOs;
		this.registroFormatoExpedienteDTOs = registroFormatoExpedienteDTOs;
	}

	public int getCodExpediente() {
		return this.codExpediente;
	}

	public void setCodExpediente(int codExpediente) {
		this.codExpediente = codExpediente;
	}

	public FormatoRequerimientoDTO getFormatoRequerimientoDTO() {
		return this.formatoRequerimientoDTO;
	}

	public void setFormatoRequerimientoDTO(
			FormatoRequerimientoDTO formatoRequerimientoDTO) {
		this.formatoRequerimientoDTO = formatoRequerimientoDTO;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Set<OrdenCompraDTO> getOrdenCompraDTOs() {
		return this.ordenCompraDTOs;
	}

	public void setOrdenCompraDTOs(Set<OrdenCompraDTO> ordenCompraDTOs) {
		this.ordenCompraDTOs = ordenCompraDTOs;
	}

	public Set<CuadroComparativoDTO> getCuadroComparativoDTOs() {
		return this.cuadroComparativoDTOs;
	}

	public void setCuadroComparativoDTOs(
			Set<CuadroComparativoDTO> cuadroComparativoDTOs) {
		this.cuadroComparativoDTOs = cuadroComparativoDTOs;
	}

	public Set<SolicitudCotizacionDTO> getSolicitudCotizacionDTOs() {
		return this.solicitudCotizacionDTOs;
	}

	public void setSolicitudCotizacionDTOs(
			Set<SolicitudCotizacionDTO> solicitudCotizacionDTOs) {
		this.solicitudCotizacionDTOs = solicitudCotizacionDTOs;
	}

	public Set<RegistroFormatoExpedienteDTO> getRegistroFormatoExpedienteDTOs() {
		return this.registroFormatoExpedienteDTOs;
	}

	public void setRegistroFormatoExpedienteDTOs(
			Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDTOs) {
		this.registroFormatoExpedienteDTOs = registroFormatoExpedienteDTOs;
	}

}