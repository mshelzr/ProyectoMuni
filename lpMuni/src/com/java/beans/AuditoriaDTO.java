package com.java.beans;

// Generated 05-sep-2013 3:08:07 by Hibernate Tools 4.0.0

/**
 * AuditoriaDTO generated by hbm2java
 */
public class AuditoriaDTO implements java.io.Serializable {

	private int codAuditoria;
	private String fechaLogin;
	private String fechaLogout;
	private String usuario;
	private String hostname;
	private String tiempo;

	public AuditoriaDTO() {
	}

	public AuditoriaDTO(String fechaLogin, String fechaLogout, String usuario,
			String hostname, String tiempo) {
		this.fechaLogin = fechaLogin;
		this.fechaLogout = fechaLogout;
		this.usuario = usuario;
		this.hostname = hostname;
		this.tiempo = tiempo;
	}

	public int getCodAuditoria() {
		return this.codAuditoria;
	}

	public void setCodAuditoria(int codAuditoria) {
		this.codAuditoria = codAuditoria;
	}

	public String getFechaLogin() {
		return this.fechaLogin;
	}

	public void setFechaLogin(String fechaLogin) {
		this.fechaLogin = fechaLogin;
	}

	public String getFechaLogout() {
		return this.fechaLogout;
	}

	public void setFechaLogout(String fechaLogout) {
		this.fechaLogout = fechaLogout;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

}
