package com.java.cuadrocomp.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
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

import com.java.beans.CuadroComparativoDTO;
import com.java.beans.ExpedienteCompraDTO;
import com.java.beans.ProveedorDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.beans.SolicitudCotizacionDTO;
import com.java.beans.SolicitudCotizacionHasProveedorDTO;
import com.java.beans.UsuarioDTO;
import com.java.cuadrocomp.services.CuadroCompService;
import com.java.expediente.services.ExpedienteCompraService;
import com.java.expediente.servlets.RegistrarExpedienteServlet;
import com.java.solicitudcotiz.services.SolicitudCotizService;
import com.java.util.Constantes;

public class CuadroCompServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuadroCompService ccService=new CuadroCompService();

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response)   throws ServletException, IOException, FileUploadException{

		HttpSession lasesion=request.getSession(true);
		ExpedienteCompraService expedienteCompraService=new ExpedienteCompraService();

		ExpedienteCompraDTO ec=new ExpedienteCompraDTO();
		int codExpediente=(int)lasesion.getAttribute("codExpedienteSeleccionaDeLaGrilla");
		ec.setCodExpediente(codExpediente);

		//Obteniendo datos del form
		UsuarioDTO usuario=((UsuarioDTO)lasesion.getAttribute("v_usuario"));
		int vPosicion=(int)lasesion.getAttribute("v_posicion");
		String aprobado=(String)request.getParameter("aprobado");
		String firmado=(String)request.getParameter("firmado");

		//Armando el registro_f_e
		RegistroFormatoExpedienteDTO registro_f_e=new RegistroFormatoExpedienteDTO();
		registro_f_e.setExpedienteCompraDTO(ec);
		registro_f_e.setUsuarioDTO(usuario);
		registro_f_e.setPosicion(++vPosicion); //aumentamos la posicion
		registro_f_e.setFirmado(firmado);

		String vOpe=(String)request.getParameter("operacion");
		if(vOpe.equals("enviaCuadroComparativo")){

			String urlDelDocumentoAdjuntado=null;

			String sc = this.getServletConfig().getServletContext().getRealPath("/");
			sc=sc.concat("docstore");
			/*Comprobamos si el formulario es multipart/form-data.*/
			boolean reciboFile = ServletFileUpload.isMultipartContent(request);

			if (reciboFile){
				String nombreOriginal=" ";
				DiskFileItemFactory fileItem = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(fileItem);
				List<?> items = upload.parseRequest(request);
				Iterator<?> iter = items.iterator();
				while (iter.hasNext()){
					FileItem item = (FileItem) iter.next();
					/*Si no es un tipo file.*/
					if (item.isFormField()) { //si contiene un input no file
						String campo = item.getFieldName();
						if(campo.equals("aprobado")){
							aprobado = item.getString();
						}
					}else{ //Si contiene un file
						nombreOriginal = item.getName();
						if(nombreOriginal.contains("\\"))
							nombreOriginal=nombreOriginal.substring(nombreOriginal.lastIndexOf("\\")+1);
						System.out.println(nombreOriginal);
						urlDelDocumentoAdjuntado="docstore/".concat(nombreOriginal);
						File saveTo = new File(sc,nombreOriginal);
						try {
							item.write(saveTo);
							System.out.println("El fichero cargado se ha guardado correctamente.");
						}
						catch (Exception e){
							e.printStackTrace();
							System.out.println("                    "+e.getMessage()+"  ");
							System.out.println("Ha ocurrido un error cuando intentábamos guardar el fichero cargado.");
						}
					}
				}
			}

			registro_f_e.setAprobado(aprobado);
			
			CuadroComparativoDTO cc=new CuadroComparativoDTO();
			cc.setFecha(Constantes.getFecha());
			cc.setExpedienteCompraDTO(ec);
			cc.setUrl(urlDelDocumentoAdjuntado);

			ccService.procesarCuadroCompService(cc);
		}
		else if(vOpe.equals("escojeCuadroComparativo")) {

			//seteando el aprobado
			registro_f_e.setAprobado(aprobado);
			
			SolicitudCotizService solicitudCotizService=new SolicitudCotizService();
			//Obteniendo los datos del form
			int codSolicitudCotiz= solicitudCotizService.obtenerCodSolicitudCotizService(codExpediente);
			int codProveedor=Integer.parseInt(request.getParameter("ri_codProveedor"));
			String escogido=(String)request.getParameter("proveedorElegido");

			//Creando SolicitudCotizacionDTO para el hasId
			SolicitudCotizacionDTO sc=new SolicitudCotizacionDTO();
			sc.setCodSolicitudCotizacion(codSolicitudCotiz);
			//Creando provedor paara el hasId
			ProveedorDTO prov=new ProveedorDTO();
			prov.setCodProveedor(codProveedor);
			
//			Creando Solicitx-xProveedor
			SolicitudCotizacionHasProveedorDTO scHasProv=new SolicitudCotizacionHasProveedorDTO();
			scHasProv.setElegido(escogido);

			scHasProv.setProveedorDTO(prov);
			scHasProv.setSolicitudCotizacionDTO(sc);
			
			solicitudCotizService.modificarDetalleProveedorSolicitudService(scHasProv);
		}
		expedienteCompraService.procesarLogExpediente(registro_f_e);

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
