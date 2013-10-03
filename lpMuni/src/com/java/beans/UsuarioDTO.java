package com.java.beans;

// Generated 05-sep-2013 3:08:07 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * UsuarioDTO generated by hbm2java
 */
public class UsuarioDTO implements java.io.Serializable {

	private int codUsuario;
	private PerfilDTO perfilDTO;
	private String nombre;
	private String ape_paterno;
	private String ape_materno;
	private String usuario;
	private String password;
	private byte[] foto;
	private byte[] firma;
	private Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDTOs = new HashSet<RegistroFormatoExpedienteDTO>(
			0);

	public UsuarioDTO() {
	}

	public UsuarioDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public UsuarioDTO(PerfilDTO perfilDTO, String nombre, String ape_paterno,
			String ape_materno, String usuario, String password, byte[] foto,
			byte[] firma,
			Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDTOs) {
		this.perfilDTO = perfilDTO;
		this.nombre = nombre;
		this.ape_paterno = ape_paterno;
		this.ape_materno = ape_materno;
		this.usuario = usuario;
		this.password = password;
		this.foto = foto;
		this.firma = firma;
		this.registroFormatoExpedienteDTOs = registroFormatoExpedienteDTOs;
	}

	public int getCodUsuario() {
		return this.codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public PerfilDTO getPerfilDTO() {
		return this.perfilDTO;
	}

	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApe_paterno() {
		return this.ape_paterno;
	}

	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}

	public String getApe_materno() {
		return this.ape_materno;
	}

	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public byte[] getFirma() {
		return this.firma;
	}

	public void setFirma(byte[] firma) {
		this.firma = firma;
	}

	public Set<RegistroFormatoExpedienteDTO> getRegistroFormatoExpedienteDTOs() {
		return this.registroFormatoExpedienteDTOs;
	}

	public void setRegistroFormatoExpedienteDTOs(
			Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDTOs) {
		this.registroFormatoExpedienteDTOs = registroFormatoExpedienteDTOs;
	}

}