package com.java.seguridad.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmesa.web.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.beans.MenuDTO;
import com.java.factoria.DAOFactory;
import com.java.seguridad.daos.JMesaUMLDAO;
import com.java.seguridad.daos.MenuDAO;
import com.java.util.Constantes;

public class MenuService implements JMesaUMLDAO {

	private WebContext webContext;
	private List<MenuDTO> menusitos;
	private MenuDAO menuDAO; 
	
	private static final Logger log = LoggerFactory.getLogger(MenuService.class);
	
	public MenuService(WebContext webContext){
		DAOFactory daofactory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
		menuDAO=daofactory.getMenuDao();
		this.webContext=webContext;
	}
		
	public MenuService(){
		
	}
	@SuppressWarnings("unchecked")
	public Collection<MenuDTO> getMenus(){
    	if (webContext.getParameter("restore") != null) {
    		webContext.removeSessionAttribute("menusitosCollection");
    	} else {
    		menusitos = (List<MenuDTO>)webContext.getSessionAttribute("menusitosCollection");
    	}
    	
    	if (menusitos != null) {
    		log.debug("Getting the list from session.");
    		return menusitos;
        }
    	log.debug("List not found, creating a new list.");
    	menusitos=new ArrayList<MenuDTO>();
		menusitos=menuDAO.listadoMenu();

		webContext.setSessionAttribute("menusitosCollection", menusitos);
        webContext.setSessionAttribute("lastId", menusitos.size());
		return menusitos;
	}//fin de getBeansitos

	@Override
	public Map<String, ?> getAsMap() {
        Map<String, MenuDTO> results = new HashMap<String, MenuDTO>();

        for (MenuDTO menusito : getMenus()) {
            results.put(String.valueOf(menusito.getCodMenu()), menusito);
        }
        return results;
	}

	@Override
	public void save(Object b, boolean isNew) {
		MenuDTO b2=(MenuDTO)b;
		if (isNew) {
			int id = (Integer)webContext.getSessionAttribute("lastId") + 1;
    		webContext.setSessionAttribute("lastId", id);
    		
    		b2.setCodMenu(id);
    		menuDAO.registraMenu(b2);
    		menusitos.add(b2);
    	}
    	else {
    		menuDAO.actualizaMenu(b2);
    		int i = menusitos.indexOf(b2);
    		menusitos.remove(i);
    		menusitos.add(i, b2);
    	}
    	
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("menusitosCollection", menusitos);
	}

	@Override
	public void delete(Object p) {
		MenuDTO p2=(MenuDTO)p;
    	menuDAO.eliminaMenu(p2.getCodMenu());
    	menusitos.remove(p2);
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("menusitosCollection", menusitos);
	}

	@Override
	public Object getNewObjeto() {
        MenuDTO menusito = new MenuDTO();
        menusito.setDescripcion("new");
        menusito.setDireccion("new");
        
        return menusito;
	}
}
