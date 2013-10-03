package com.java.seguridad.daos;

import java.util.List;

import com.java.beans.MenuDTO;
import com.java.beans.UsuarioDTO;

public interface UsuarioDAO {

	public UsuarioDTO buscaPorUsuario(String vUsuario);
	public List<MenuDTO> buscarMenu_DAO(String vUsuario);
	//mantenimiento
	public List<UsuarioDTO> listadoUsuarios();
	public void registraUsuario(UsuarioDTO objUsuario);
	public void eliminaUsuario(int codUsuario);
	public void actualizaUsuario(UsuarioDTO objUsua);
	
	
}
