package com.java.seguridad.services;

import java.util.List;

import com.java.beans.MenuDTO;
import com.java.beans.UsuarioDTO;
import com.java.factoria.DAOFactory;
import com.java.seguridad.daos.UsuarioDAO;
import com.java.util.Constantes;

public class LogueoService {
	
	DAOFactory fabrica=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	UsuarioDAO objUsuarioDAO = fabrica.getUsuarioDAO();
	
	public UsuarioDTO validaPorUsuario(UsuarioDTO objCandidato ){
		UsuarioDTO objUsuario=null;
		objUsuario = objUsuarioDAO.buscaPorUsuario(objCandidato.getUsuario());
		return objUsuario;
	}
	
	public List<MenuDTO> buscarMenu_Serv(UsuarioDTO us){
		List<MenuDTO> objMenu=null;
		objMenu=objUsuarioDAO.buscarMenu_DAO(us.getUsuario());
		return objMenu;
	}

	
	
	
}







