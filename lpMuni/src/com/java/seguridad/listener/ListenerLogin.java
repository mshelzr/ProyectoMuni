package com.java.seguridad.listener;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.java.beans.AuditoriaDTO;
import com.java.beans.UsuarioDTO;
import com.java.seguridad.services.AuditoriaService;

public class ListenerLogin implements HttpSessionAttributeListener {

	private AuditoriaService servicio=new AuditoriaService();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	private String fechaLogin=null;
	private long tpoIni, tpoFin, tiempo;
	private int hh, mm,ss;
	private String tpo;
	private AuditoriaDTO audi;
	
    public void attributeAdded(HttpSessionBindingEvent arg0) {
    	try {
    		String fechaLogout=null;
    		String usuario="";
			// Capturamos el nombre del atributo cargado a la sesión
    		String nameObjeto=arg0.getName();
			if( nameObjeto.equals("v_usuario") ){
				fechaLogin=sdf.format(new Date());
				
				// Obtenemos el tiempo de inicio de sesión
				tpoIni=System.currentTimeMillis();
				
				// Obtenemos el objeto Usuario que se está logueando
				UsuarioDTO objUsu=(UsuarioDTO) arg0.getSession().getAttribute("v_usuario");
				
				String namehost=InetAddress.getLocalHost().getHostName();
				// Capturamos el usuario
				usuario=objUsu.getUsuario();
				
				audi=new AuditoriaDTO(fechaLogin, fechaLogout, usuario,namehost,null);
				// Llamamos al método del Service
				servicio.registraUsuarioLogin(audi);
			}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void attributeRemoved(HttpSessionBindingEvent arg0) {
    	// Se ejecuta cuando se termina una sesión-logout (Servlet)
    	try {
    		String fechaLogout=null;
    		
    		String nameObjeto=arg0.getName();
    		if( nameObjeto.equals("v_usuario") ){
    			fechaLogout=sdf.format(new Date());
				// Obtenemos el tiempo de inicio de sesión
				tpoFin=System.currentTimeMillis();
    			// Tiempo de duración de la sesión (Segundos)
    			tiempo = (tpoFin - tpoIni)/1000;
    			// Convertir a horas, minutos y segundos
    			hh = (int)tiempo/3600;
    			mm = (int)tiempo%3600/60;
    			ss = (int)tiempo%3600%60;
    			
    			tpo = hh+":"+mm+":"+ss;
    			// tpo="0:2:45"
    			
    			audi.setFechaLogout(fechaLogout);
    			audi.setTiempo(tpo);
//    			=new AuditoriaDTO(fechaLogin, fechaLogout, null, null,tpo);
    			servicio.actualizaLogout(audi);
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
