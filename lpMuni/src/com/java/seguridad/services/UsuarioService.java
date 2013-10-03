package com.java.seguridad.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmesa.web.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.beans.PerfilDTO;
import com.java.beans.UsuarioDTO;
import com.java.factoria.DAOFactory;
import com.java.seguridad.daos.JMesaUMLDAO;
import com.java.seguridad.daos.UsuarioDAO;
import com.java.util.Constantes;

public class UsuarioService implements JMesaUMLDAO {

	private WebContext webContext;
	private List<UsuarioDTO> usuaritos;
	private UsuarioDAO usuarioDAO; 
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	public UsuarioService(WebContext webContext){
		DAOFactory daofactory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
		usuarioDAO=daofactory.getUsuarioDAO();
		this.webContext=webContext;
	}
		
	public UsuarioService(){
		
	}
	@SuppressWarnings("unchecked")
	public Collection<UsuarioDTO> getUsuaritos(){
    	if (webContext.getParameter("restore") != null) {
    		webContext.removeSessionAttribute("usuaritosCollection");
    	} else {
    		usuaritos = (List<UsuarioDTO>)webContext.getSessionAttribute("usuaritosCollection");
    	}
    	
    	if (usuaritos != null) {
    		log.debug("Getting the list from session.");
    		return usuaritos;
        }
    	log.debug("List not found, creating a new list.");
    	usuaritos=new ArrayList<UsuarioDTO>();
		usuaritos=usuarioDAO.listadoUsuarios();

		webContext.setSessionAttribute("usuaritosCollection", usuaritos);
        webContext.setSessionAttribute("lastId", usuaritos.size());
		return usuaritos;
	}//fin de getBeansitos

	@Override
	public Map<String, ?> getAsMap() {
        Map<String, UsuarioDTO> results = new HashMap<String, UsuarioDTO>();

        for (UsuarioDTO usuarito : getUsuaritos()) {
            results.put(String.valueOf(usuarito.getCodUsuario()), usuarito);
        }
        return results;
	}

	@Override
	public void save(Object b, boolean isNew) {
		UsuarioDTO b2=(UsuarioDTO)b;
		if (isNew) {
			int id = (Integer)webContext.getSessionAttribute("lastId") + 1;
    		webContext.setSessionAttribute("lastId", id);
    		
    		b2.setCodUsuario(id);
    		usuarioDAO.registraUsuario(b2);
    		usuaritos.add(b2);
    	}
    	else {
    		usuarioDAO.actualizaUsuario(b2);
    		int i = usuaritos.indexOf(b2);
    		usuaritos.remove(i);
    		usuaritos.add(i, b2);
    	}
    	
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("usuaritosCollection", usuaritos);
	}

	@Override
	public void delete(Object p) {
		UsuarioDTO p2=(UsuarioDTO)p;
    	usuarioDAO.eliminaUsuario(p2.getCodUsuario());
    	usuaritos.remove(p2);
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("usuaritosCollection", usuaritos);
	}

	@Override
	public Object getNewObjeto() {
        UsuarioDTO usuarito = new UsuarioDTO();
        usuarito.setNombre("new");
        usuarito.setApe_paterno("new");
        usuarito.setApe_materno("new");
        usuarito.setUsuario("new");
        usuarito.setPassword("");
        usuarito.setPerfilDTO(new PerfilDTO());
        
        return usuarito;
	}
}
