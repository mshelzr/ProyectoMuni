package com.java.ordencompra.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
import com.java.beans.OrdenCompraDTO;
import com.java.beans.ProveedorDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.beans.UsuarioDTO;
import com.java.expediente.services.ExpedienteCompraService;
import com.java.ordencompra.services.OrdenCompraService;
import com.java.util.Constantes;
import com.java.util.EnvioMail;
import common.Logger;

public class OrdenCompraServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, FileUploadException {
		// TODO Auto-generated method stub
		
		OrdenCompraService ordenCompraService=new OrdenCompraService();
		ExpedienteCompraService expedienteCompraService=new ExpedienteCompraService();
		
		HttpSession lasesion=request.getSession(true);
		
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

		String vOpe=(String)request.getParameter("operacion");
		if(vOpe.equals("generarOrdenCompra")){
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
					if (item.isFormField()) {
						String campo = item.getFieldName();
						if(campo.equals("aprobado")){
							aprobado = item.getString();
						}
					}else{
						nombreOriginal = item.getName();
						if(nombreOriginal.contains("\\"))
							nombreOriginal=nombreOriginal.substring(nombreOriginal.lastIndexOf("\\")+1);
						/*Guarmados el file.*/
						urlDelDocumentoAdjuntado="docstore/".concat(nombreOriginal);
						File saveTo = new File(sc,nombreOriginal);
						try {
							item.write(saveTo);
							Logger.getLogger(OrdenCompraServlet.class).info("Se cargo el archivo");
						}
						catch (Exception e){
							e.printStackTrace();
							Logger.getLogger(OrdenCompraServlet.class).error("Hubo un problema al intentar cargar el archivo");
						}
					}
				}

			}
			registro_f_e.setAprobado(aprobado);
			registro_f_e.setFirmado(firmado);
			
			OrdenCompraDTO oc=new OrdenCompraDTO();
			oc.setFecha(Constantes.getFecha());
			oc.setUrl(urlDelDocumentoAdjuntado);
			oc.setExpedienteCompraDTO(ec);

			ordenCompraService.procesarOrdenCompra(oc);
			expedienteCompraService.procesarLogExpediente(registro_f_e);
		}
		else if(vOpe.equals("firmaOrdenCompra")){
			ProveedorDTO objProveedor=ordenCompraService.obtenerCodProveedorElegido(codExpediente);
			
			registro_f_e.setAprobado(aprobado);
			registro_f_e.setFirmado(firmado);
			
			String direccionUrlDeOrdenCompra=ordenCompraService.obtenerUrlOrdenCompraParaEnviarEmail(codExpediente);
			String sc = this.getServletConfig().getServletContext().getRealPath("/");
			
			String nombreDoc=direccionUrlDeOrdenCompra.replaceAll("docstore/", "");
			String urlObtenido= sc+direccionUrlDeOrdenCompra;
			
			String asunto="Ha sido seleccionado";
			String textoDelCuerpo= "Después de una rigurosa evaluación se ha llegado a la conclusión de que "+objProveedor.getNombre()+" ha sido elegido para el contrato";
			String[] mailsProveedores={objProveedor.getCorreo()};

			EnvioMail.setEnvio(mailsProveedores, asunto,textoDelCuerpo,nombreDoc, urlObtenido);
						
			expedienteCompraService.procesarLogExpediente(registro_f_e);
		}

		request.getRequestDispatcher("/principal.jsp").forward(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (FileUploadException ex) {
			Logger.getLogger(OrdenCompraServlet.class).error("error en doGet");
		}
	} 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (FileUploadException ex) {
			Logger.getLogger(OrdenCompraServlet.class).error("error en doPost");
		}
	}
	
}
