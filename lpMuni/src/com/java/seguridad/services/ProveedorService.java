package com.java.seguridad.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmesa.web.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.beans.ProveedorDTO;
import com.java.factoria.DAOFactory;
import com.java.seguridad.daos.JMesaUMLDAO;
import com.java.solicitudcotiz.daos.ProveedorDAO;
import com.java.util.Constantes;

public class ProveedorService implements JMesaUMLDAO {

	private WebContext webContext;
	private List<ProveedorDTO> proveedores;
	private ProveedorDAO proveedoresDAO; 
	
	private static final Logger log = LoggerFactory.getLogger(ProveedorService.class);
	
	public ProveedorService(WebContext webContext){
		DAOFactory daofactory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
		proveedoresDAO=daofactory.getProveedorDao();
		this.webContext=webContext;
	}
		
	public ProveedorService(){
		
	}
	@SuppressWarnings("unchecked")
	public Collection<ProveedorDTO> getProveedores(){
    	if (webContext.getParameter("restore") != null) {
    		webContext.removeSessionAttribute("proveedoresCollection");
    	} else {
    		proveedores = (List<ProveedorDTO>)webContext.getSessionAttribute("proveedoresCollection");
    	}
    	
    	if (proveedores != null) {
    		log.debug("Getting the list from session.");
    		return proveedores;
        }
    	log.debug("List not found, creating a new list.");
    	proveedores=new ArrayList<ProveedorDTO>();
		proveedores=proveedoresDAO.listadoProveedor();

		webContext.setSessionAttribute("proveedoresCollection", proveedores);
        webContext.setSessionAttribute("lastId", proveedores.size());
		return proveedores;
	}//fin de getBeansitos

	@Override
	public Map<String, ?> getAsMap() {
        Map<String, ProveedorDTO> results = new HashMap<String, ProveedorDTO>();

        for (ProveedorDTO usuarito : getProveedores()) {
            results.put(String.valueOf(usuarito.getCodProveedor()), usuarito);
        }
        return results;
	}

	@Override
	public void save(Object b, boolean isNew) {
		ProveedorDTO b2=(ProveedorDTO)b;
		if (isNew) {
			int id = (Integer)webContext.getSessionAttribute("lastId") + 1;
    		webContext.setSessionAttribute("lastId", id);
    		
    		b2.setCodProveedor(id);
    		proveedoresDAO.registraProveedor(b2);
    		proveedores.add(b2);
    	}
    	else {
    		proveedoresDAO.actualizaProveedor(b2);
    		int i = proveedores.indexOf(b2);
    		proveedores.remove(i);
    		proveedores.add(i, b2);
    	}
    	
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("proveedoresCollection", proveedores);
	}

	@Override
	public void delete(Object p) {
		ProveedorDTO p2=(ProveedorDTO)p;
    	proveedoresDAO.eliminaProveedor(p2.getCodProveedor());
    	proveedores.remove(p2);
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("proveedoresCollection", proveedores);
	}

	@Override
	public Object getNewObjeto() {
        ProveedorDTO usuarito = new ProveedorDTO();
        usuarito.setNombre("new");
        usuarito.setCorreo("new");
        usuarito.setRuc(0);
        usuarito.setTelefono(0);
        
        return usuarito;
	}
}
