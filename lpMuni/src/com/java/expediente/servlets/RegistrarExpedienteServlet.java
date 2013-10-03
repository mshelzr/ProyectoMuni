package com.java.expediente.servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.beans.ExpedienteCompraDTO;
import com.java.beans.FormatoRequerimientoDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.beans.SolicitudCotizacionDTO;
import com.java.beans.UsuarioDTO;
import com.java.expediente.services.ExpedienteCompraService;
import com.java.util.Constantes;

public class RegistrarExpedienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExpedienteCompraService ecService=new ExpedienteCompraService();

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response)   throws ServletException, IOException, FileUploadException{

		ExpedienteCompraDTO ec=new ExpedienteCompraDTO();

		String urlDelDocumentoAdjuntado=null;
		String aprobado=null;
		
		String path = this.getServletConfig().getServletContext().getRealPath("/");
		path=path.concat("docstore");
		/*Comprobamos si el formulario es multipart/form-data.*/
		boolean reciboFile = ServletFileUpload.isMultipartContent(request);

		if (reciboFile){
			String nombreOriginal=" ";
			/* Nos irá generando los items y según su tamaño los guardará en memoria, 
	             en un fichero temporal o no los aceptará si son muy grandes.*/
			DiskFileItemFactory fileItem = new DiskFileItemFactory();
			/* Creamos un manejador para todos los items de la petición.*/
			ServletFileUpload upload = new ServletFileUpload(fileItem);
			/* Obtenemos la lista con todos los items de la petición.*/
			List<?> items = upload.parseRequest(request);
			Iterator<?> iter = items.iterator();
			while (iter.hasNext()){
				FileItem item = (FileItem) iter.next();
				/*Si no es un tipo file.*/
				if (item.isFormField()) {
					String campo = item.getFieldName();
					if(campo.equals("aprobado")){
						aprobado=item.getString();
					}
					if(campo.equals("descripcion")){
					ec.setDescripcion(item.getString());
					}
				}else{
					nombreOriginal = item.getName();
					if(nombreOriginal.contains("\\"))
						nombreOriginal=nombreOriginal.substring(nombreOriginal.lastIndexOf("\\")+1);
					/*Guarmados el file.*/
					urlDelDocumentoAdjuntado="docstore/".concat(nombreOriginal);
					File saveTo = new File(path,nombreOriginal);
					try {
						item.write(saveTo);
					}
					catch (Exception e){
						e.printStackTrace();
						
					}
				}
			}

		}		
		HttpSession lasesion=request.getSession(true);
		
		//Creacion de Usuario para el registro_f_e
		UsuarioDTO usuario=(UsuarioDTO)lasesion.getAttribute("v_usuario");
		//Creacion de Formato Req
		FormatoRequerimientoDTO fr=new FormatoRequerimientoDTO();
		fr.setCodFormato((int)lasesion.getAttribute("codFormatoSeleccionaDeLaGrilla"));
		
		//Creacion del registro_f_e
		RegistroFormatoExpedienteDTO registro_f_e=new RegistroFormatoExpedienteDTO();
		registro_f_e.setFormatoRequerimientoDTO(fr);
		registro_f_e.setExpedienteCompraDTO(ec);
		registro_f_e.setUsuarioDTO(usuario);
		registro_f_e.setAprobado(aprobado);
		registro_f_e.setPosicion((int)lasesion.getAttribute("v_posicion")+1); //sumamos la siguiente posicion
		
		//Creacion de Solicitud cotizacion
		SolicitudCotizacionDTO sc=new SolicitudCotizacionDTO();
		sc.setExpedienteCompraDTO(ec);
		sc.setFecha(Constantes.getFecha());
		sc.setUrl(urlDelDocumentoAdjuntado);
		
		//Agregando los datos necesarios al Expediente
		ec.setFormatoRequerimientoDTO(fr);
		ec.setFecha(Constantes.getFecha());
		
		//Creamos las entidades relacionadas con el expedientecompra que se insertaran 
		Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDTOs=new HashSet<RegistroFormatoExpedienteDTO>();
		Set<SolicitudCotizacionDTO> solicitudCotizacionDTOs=new HashSet<SolicitudCotizacionDTO>();
		registroFormatoExpedienteDTOs.add(registro_f_e);
		solicitudCotizacionDTOs.add(sc);
		
		ec.setRegistroFormatoExpedienteDTOs(registroFormatoExpedienteDTOs);
		ec.setSolicitudCotizacionDTOs(solicitudCotizacionDTOs);
		
		ecService.procesarExpedienteCompraServices(ec);
		request.getRequestDispatcher("/principal.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (FileUploadException ex) {
			Logger.getLogger(RegistrarExpedienteServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
	} 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (FileUploadException ex) {
			Logger.getLogger(RegistrarExpedienteServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
