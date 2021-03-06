package com.java.beans;

// Generated 05-sep-2013 3:08:07 by Hibernate Tools 4.0.0

/**
 * OrdenCompraDTO generated by hbm2java
 */
public class OrdenCompraDTO implements java.io.Serializable {

	private int ordencompra;
	private ExpedienteCompraDTO expedienteCompraDTO;
	private String url;
	private String fecha;

	public OrdenCompraDTO() {
	}

	public OrdenCompraDTO(ExpedienteCompraDTO expedienteCompraDTO) {
		this.expedienteCompraDTO = expedienteCompraDTO;
	}

	public OrdenCompraDTO(ExpedienteCompraDTO expedienteCompraDTO, String url,
			String fecha) {
		this.expedienteCompraDTO = expedienteCompraDTO;
		this.url = url;
		this.fecha = fecha;
	}

	public int getOrdencompra() {
		return this.ordencompra;
	}

	public void setOrdencompra(int ordencompra) {
		this.ordencompra = ordencompra;
	}

	public ExpedienteCompraDTO getExpedienteCompraDTO() {
		return this.expedienteCompraDTO;
	}

	public void setExpedienteCompraDTO(ExpedienteCompraDTO expedienteCompraDTO) {
		this.expedienteCompraDTO = expedienteCompraDTO;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
