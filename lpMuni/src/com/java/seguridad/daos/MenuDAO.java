package com.java.seguridad.daos;

import java.util.List;

import com.java.beans.MenuDTO;

public interface MenuDAO {

	public List<MenuDTO> listadoMenu();
	public void registraMenu(MenuDTO obj);
	public void eliminaMenu(int cod);
	public void actualizaMenu(MenuDTO obj);
	
}
