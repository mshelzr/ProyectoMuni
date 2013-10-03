package com.java.solicitudcotiz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.beans.ExpedienteCompraDTO;
import com.java.beans.ProveedorDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.beans.SolicitudCotizacionDTO;
import com.java.beans.SolicitudCotizacionHasProveedorDTO;
import com.java.beans.UsuarioDTO;
import com.java.expediente.services.ExpedienteCompraService;
import com.java.solicitudcotiz.services.SolicitudCotizService;
import com.java.util.EnvioMail;

/**
 * Servlet implementation class SolicitudCotizServlet
 */
public class SolicitudCotizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SolicitudCotizService scservice=new SolicitudCotizService();
		ExpedienteCompraService expedienteCompraService=new ExpedienteCompraService();
		
		HttpSession lasesion=request.getSession(true);
		
		int codExpediente=(int)lasesion.getAttribute("codExpedienteSeleccionaDeLaGrilla");
		UsuarioDTO usuario=((UsuarioDTO)lasesion.getAttribute("v_usuario"));
		int vPosicion=(int)lasesion.getAttribute("v_posicion");
		String aprobado=(String)request.getParameter("aprobado");
		String firmado=(String)request.getParameter("firmado");

		ExpedienteCompraDTO expediente=new ExpedienteCompraDTO();
		expediente.setCodExpediente(codExpediente);
		
		RegistroFormatoExpedienteDTO registro_f_e=new RegistroFormatoExpedienteDTO();
		registro_f_e.setExpedienteCompraDTO(expediente);
		registro_f_e.setUsuarioDTO(usuario);
		registro_f_e.setPosicion(++vPosicion); //aumentamos la posicion
		registro_f_e.setAprobado(aprobado);
		registro_f_e.setFirmado(firmado);
		
		String vOpe=(String)request.getParameter("operacion");
		if(vOpe.equals("procesoAprobarSolicitud")){

		
		}else if(vOpe.equals("procesoInvitacionProveedores")){
			
			SolicitudCotizacionDTO sc=new SolicitudCotizacionDTO();
			int codSolicitudCotiz=scservice.obtenerCodSolicitudCotizService(codExpediente);
			sc.setCodSolicitudCotizacion(codSolicitudCotiz);
			String[] arrayObjProv=request.getParameterValues("r_listaprovSeleccionados");
			
			String[] arrayCodsProv=new String[arrayObjProv.length];
			String[] arrayEmailProv=new String[arrayObjProv.length];
			int i=0;
			for(String codprov:arrayObjProv){
				SolicitudCotizacionHasProveedorDTO scHasprov=new SolicitudCotizacionHasProveedorDTO();
				ProveedorDTO prov=new ProveedorDTO();
				
				arrayCodsProv[i]=codprov.substring(0, 1);
				int auxprov=Integer.parseInt(codprov.substring(0, 1));
				prov.setCodProveedor(auxprov);
				arrayEmailProv[i]=codprov.substring(2, codprov.length());
				i++;
								
				scHasprov.setProveedorDTO(prov);
				scHasprov.setSolicitudCotizacionDTO(sc);
				//Registrar a los proveedores seleccionados
				scservice.registrarAlDetalleLosProveedoresInvitados(scHasprov);
			}
			
			//Se envia el email a los proveedores seleccionados
			EnvioMail.setEnvio(arrayEmailProv, "Solicitud de Cotizacion", "",(String)lasesion.getAttribute("v_nomdoc"), (String)lasesion.getAttribute("v_direccionAdjuntar"));

		}
		//Cualquier proceso se necesita registrar
		expedienteCompraService.procesarLogExpediente(registro_f_e);

		request.getRequestDispatcher("/principal.jsp").forward(request, response);
	
	}

}
