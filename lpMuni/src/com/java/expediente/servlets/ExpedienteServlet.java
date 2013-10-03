package com.java.expediente.servlets;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.NullArgumentException;

import com.java.beans.DetallePacDTO;
import com.java.beans.ExpedienteCompraDTO;
import com.java.beans.FormatoRequerimientoDTO;
import com.java.beans.ProveedorDTO;
import com.java.cuadrocomp.services.CuadroCompService;
import com.java.expediente.services.ExpedienteCompraService;
import com.java.formato.services.FormatoRequerimientoService;
import com.java.solicitudcotiz.services.SolicitudCotizService;
import common.Logger;

public class ExpedienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExpedienteCompraService expedienteCompraService=new ExpedienteCompraService();
	private static int APROBAR_SOLICITUD_COTIZACION=6;
	private static int INVITAR_PROVEEDORES=7;
	private static int ENVIAR_CUADROCOMPARATIVO=8;
	private static int ESCOJER_CUADROCOMPARATIVO=9;
	private static int GENERAR_ORDENCOMPRA=10;
	private static int FIRMAR_ORDENCOMPRA=11;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String vOpe=(String)request.getParameter("operacion");
		HttpSession sesion=request.getSession(true);

		if(vOpe.equals("grillaexpediente")){
			int etapa=Integer.parseInt(request.getParameter("vposicion"));

			List<ExpedienteCompraDTO> ecList=expedienteCompraService.listarExpedienteCompraServices(etapa);
			request.setAttribute("listarec", ecList);
			sesion.setAttribute("v_posicion", etapa);
			request.getRequestDispatcher("/grillaExpedienteComp.jsp").forward(request, response);
		}
		else if(vOpe.equals("cargaFormularioExpediente"))
			this.cargarDatosDelFormularioDespuesDeLaGrilla(request,response);

	}

	private void cargarDatosDelFormularioDespuesDeLaGrilla(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion=request.getSession(true);
		
		int codExpediente=Integer.parseInt(request.getParameter("seleccionRadio"));
		sesion.setAttribute("codExpedienteSeleccionaDeLaGrilla",codExpediente );

		SolicitudCotizService solicitudCotizacionService=new SolicitudCotizService();
		FormatoRequerimientoService formatoReqService=new FormatoRequerimientoService();

		int codFormato=expedienteCompraService.cambiarExpXformService(codExpediente);
		FormatoRequerimientoDTO objFormatoReq=formatoReqService.obtenerFormatoReqService(codFormato);
		DetallePacDTO objDetallePac=formatoReqService.obtenerDetallePacService(codFormato);
		List<Object[]> listFirmaUsua=formatoReqService.listarFirmaUsuarioService(codFormato);
		
		for (int i = 0; i < listFirmaUsua.size(); i++) {
			try {
				listFirmaUsua.get(i)[0]=DatatypeConverter.printBase64Binary((byte[]) listFirmaUsua.get(i)[0]);
			} catch (NullArgumentException e) {
				Logger.getLogger(ExpedienteServlet.class).error("No hay valor para convertir a imagen");
			}
		}
		ExpedienteCompraDTO objExpediente=expedienteCompraService.obtenerExpedienteCompraService(codExpediente);
		List<String> urlsDeDocumentos=expedienteCompraService.listarDocumentosPorExpediente(codExpediente);
		//body FormatoReq
		request.setAttribute("r_objFormatoReq", objFormatoReq);
		request.setAttribute("r_listaDetalleProd", objDetallePac);
		request.setAttribute("r_listaFirmaUsua", listFirmaUsua);
		//body ExpedienteCompra
		request.setAttribute("r_objExpedienteComp", objExpediente);
		request.setAttribute("r_listaDocumentosDelExpediente", urlsDeDocumentos);

		int vPosicion=(int)sesion.getAttribute("v_posicion");
		if(vPosicion==APROBAR_SOLICITUD_COTIZACION){

			String docSolicitudCotizacion=expedienteCompraService.obtenerUrlSolicitudCotizacionService(codExpediente);

			ListIterator<String> listaIterator=urlsDeDocumentos.listIterator();
			
			while(listaIterator.hasNext()){
				String urlAux=listaIterator.next();
				if(urlAux.equals(docSolicitudCotizacion))
					listaIterator.remove();
			}

			request.setAttribute("r_listaDocumentosDelExpediente", urlsDeDocumentos);
			request.setAttribute("v_direccion", docSolicitudCotizacion);

			request.getRequestDispatcher("/aprobarSolicitudCotizacion.jsp").forward(request, response);
		}
		else if(vPosicion==INVITAR_PROVEEDORES){
			//Cargar en el requestScope "v_direccion" el doc cargado
			String direccion=expedienteCompraService.obtenerUrlSolicitudCotizacionService(codExpediente);
			List<ProveedorDTO> listProv=solicitudCotizacionService.listaProveedorService();

			request.setAttribute("r_listaprov", listProv);
			//Obtiene la direccion de la app web 
			String sc = this.getServletConfig().getServletContext().getRealPath("/");

			sesion.setAttribute("v_nomdoc", direccion.replaceAll("docstore/", ""));
			sesion.setAttribute("v_direccionAdjuntar", sc+direccion);
			request.setAttribute("v_direccion", direccion);
			
			request.getRequestDispatcher("/invitarProveedores.jsp").forward(request, response);
		}
		else if(vPosicion==ENVIAR_CUADROCOMPARATIVO)
			request.getRequestDispatcher("/enviarCuadroComparativo.jsp").forward(request, response);
		else if(vPosicion==ESCOJER_CUADROCOMPARATIVO){

			CuadroCompService ccService=new CuadroCompService();
			//Cargar en el requestScope "v_direccion" el doc cargado
			String direccion=ccService.obtenerCuadroCompService(codExpediente);

			ListIterator<String> listaIterator=urlsDeDocumentos.listIterator();
			while(listaIterator.hasNext()){
				String urlAux=listaIterator.next();
				if(urlAux.equals(direccion))
					listaIterator.remove();
			}

			request.setAttribute("r_listaDocumentosDelExpediente", urlsDeDocumentos);
			List<ProveedorDTO> listaProveedorPorExpediente=new SolicitudCotizService().listaProveedorPorExpediente(codExpediente);
			request.setAttribute("v_direccion", direccion);
			request.setAttribute("r_listaProveedoresPorExpediente", listaProveedorPorExpediente);

			request.getRequestDispatcher("/escojerCuadroComparativo.jsp").forward(request, response);
		}
		else if(vPosicion==GENERAR_ORDENCOMPRA)
			request.getRequestDispatcher("/generarOrdenCompra.jsp").forward(request, response);
		else if(vPosicion==FIRMAR_ORDENCOMPRA)
			request.getRequestDispatcher("/firmarOrdenCompra.jsp").forward(request, response);

	}
}
