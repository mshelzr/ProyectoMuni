package com.java.seguridad.daos;

import java.util.List;

import com.java.beans.PerfilDTO;

public interface PerfilDAO {

	public List<PerfilDTO> listadoPerfil();
	public void registraPerfil(PerfilDTO obj);
	public void eliminaPerfil(int cod);
	public void actualizaPerfil(PerfilDTO obj);
}
