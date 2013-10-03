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
import com.java.factoria.DAOFactory;
import com.java.seguridad.daos.JMesaUMLDAO;
import com.java.seguridad.daos.PerfilDAO;
import com.java.util.Constantes;

public class PerfilService implements JMesaUMLDAO {

	private WebContext webContext;
	private List<PerfilDTO> perfiles;
	private PerfilDAO perfilDAO; 
	
	private static final Logger log = LoggerFactory.getLogger(PerfilService.class);
	
	public PerfilService(WebContext webContext){
		DAOFactory daofactory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
		perfilDAO=daofactory.getPerfilDao();
		this.webContext=webContext;
	}
		
	public PerfilService(){
		
	}
	@SuppressWarnings("unchecked")
	public Collection<PerfilDTO> getPerfiles(){
    	if (webContext.getParameter("restore") != null) {
    		webContext.removeSessionAttribute("perfilesCollection");
    	} else {
    		perfiles = (List<PerfilDTO>)webContext.getSessionAttribute("perfilesCollection");
    	}
    	
    	if (perfiles != null) {
    		log.debug("Getting the list from session.");
    		return perfiles;
        }
    	log.debug("List not found, creating a new list.");
    	perfiles=new ArrayList<PerfilDTO>();
		perfiles=perfilDAO.listadoPerfil();

		webContext.setSessionAttribute("perfilesCollection", perfiles);
        webContext.setSessionAttribute("lastId", perfiles.size());
		return perfiles;
	}//fin de getBeansitos

	@Override
	public Map<String, ?> getAsMap() {
        Map<String, PerfilDTO> results = new HashMap<String, PerfilDTO>();

        for (PerfilDTO usuarito : getPerfiles()) {
            results.put(String.valueOf(usuarito.getCodPerfil()), usuarito);
        }
        return results;
	}

	@Override
	public void save(Object b, boolean isNew) {
		PerfilDTO b2=(PerfilDTO)b;
		if (isNew) {
			int id = (Integer)webContext.getSessionAttribute("lastId") + 1;
    		webContext.setSessionAttribute("lastId", id);
    		
    		b2.setCodPerfil(id);
    		perfilDAO.registraPerfil(b2);
    		perfiles.add(b2);
    	}
    	else {
    		perfilDAO.actualizaPerfil(b2);
    		int i = perfiles.indexOf(b2);
    		perfiles.remove(i);
    		perfiles.add(i, b2);
    	}
    	
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("perfilesCollection", perfiles);
	}

	@Override
	public void delete(Object p) {
		PerfilDTO p2=(PerfilDTO)p;
    	perfilDAO.eliminaPerfil(p2.getCodPerfil());
    	perfiles.remove(p2);
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("perfilesCollection", perfiles);
	}

	@Override
	public Object getNewObjeto() {
        PerfilDTO usuarito = new PerfilDTO();
        usuarito.setDescripcion("new");
        
        return usuarito;
	}
}
