package com.java.seguridad.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmesa.web.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.beans.DetallePacDTO;
import com.java.factoria.DAOFactory;
import com.java.formato.daos.DetallePacDAO;
import com.java.seguridad.daos.JMesaUMLDAO;
import com.java.util.Constantes;

public class DetallePacService implements JMesaUMLDAO {

	private WebContext webContext;
	private List<DetallePacDTO> detallesPac;
	private DetallePacDAO detallePacDAO; 
	
	private static final Logger log = LoggerFactory.getLogger(DetallePacService.class);
	
	public DetallePacService(WebContext webContext){
		DAOFactory daofactory=DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
		detallePacDAO=daofactory.getDetallePacDao();
		this.webContext=webContext;
	}
		
	public DetallePacService(){
		
	}
	@SuppressWarnings("unchecked")
	public Collection<DetallePacDTO> getDetallePacs(){
    	if (webContext.getParameter("restore") != null) {
    		webContext.removeSessionAttribute("detallesPacCollection");
    	} else {
    		detallesPac = (List<DetallePacDTO>)webContext.getSessionAttribute("detallesPacCollection");
    	}
    	
    	if (detallesPac != null) {
    		log.debug("Getting the list from session.");
    		return detallesPac;
        }
    	log.debug("List not found, creating a new list.");
    	detallesPac=new ArrayList<DetallePacDTO>();
		detallesPac=detallePacDAO.listadoDetallePac();

		webContext.setSessionAttribute("detallesPacCollection", detallesPac);
        	webContext.setSessionAttribute("lastId", detallesPac.size());
		return detallesPac;
	}//fin de getBeansitos

	@Override
	public Map<String, ?> getAsMap() {
        Map<String, DetallePacDTO> results = new HashMap<String, DetallePacDTO>();

        for (DetallePacDTO detallePac : getDetallePacs()) {
            results.put(String.valueOf(detallePac.getCodDetallePac()), detallePac);
        }
        return results;
	}

	@Override
	public void save(Object b, boolean isNew) {
		DetallePacDTO b2=(DetallePacDTO)b;
		if (isNew) {
			int id = (Integer)webContext.getSessionAttribute("lastId") + 1;
    		webContext.setSessionAttribute("lastId", id);
    		
    		b2.setCodDetallePac(id);
    		detallePacDAO.registraDetallePac(b2);
    		detallesPac.add(b2);
    	}
    	else {
    		detallePacDAO.actualizaDetallePac(b2);
    		int i = detallesPac.indexOf(b2);
    		detallesPac.remove(i);
    		detallesPac.add(i, b2);
    	}
    	
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("detallesPacCollection", detallesPac);
	}

	@Override
	public void delete(Object p) {
		DetallePacDTO p2=(DetallePacDTO)p;
    	detallePacDAO.eliminaDetallePac(p2.getCodDetallePac());
    	detallesPac.remove(p2);
    	// required for distributed environment. e.g. GAE
    	webContext.setSessionAttribute("detallesPacCollection", detallesPac);
	}

	@Override
	public Object getNewObjeto() {
        DetallePacDTO detallePac = new DetallePacDTO();
        detallePac.setObjeto("new");
        detallePac.setValorRef(0.0);
        detallePac.setFechaConvocatoria("new");
        
        return detallePac;
	}
}
