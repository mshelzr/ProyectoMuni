package com.java.seguridad.daos;

import java.util.Map;

public interface JMesaUMLDAO {

	public Map<String,?> getAsMap();
	public void save(Object b,boolean isNew);
	public void delete(Object p);
	public Object getNewObjeto();
	
}
