package com.java.formato.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.NullArgumentException;

import com.java.beans.DetallePacDTO;
import com.java.beans.FormatoRequerimientoDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.beans.UsuarioDTO;
import com.java.formato.services.FormatoRequerimientoService;
import com.java.saldopresup.services.SaldoPresupService;
import common.Logger;

public class FormatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormatoRequerimientoService formatoReqService=new FormatoRequerimientoService();	
	private static int FIRMAR_AUTORIZACION=0;
	private static int VERIFICAR_SALDO_PRESUPUESTAL=1;
	private static int VERIFICAR_EN_PAC=2;
	private static int VERIFICAR_DATOS_JUL=3;
	private static int VERIFICAR_UIT=4;
	private static int GENERAR_EXPEDIENTE=5;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession(true);

		String vOpe=(String)request.getParameter("operacion");

		if(vOpe.equals("grillaformato")){

			int etapa=Integer.parseInt(request.getParameter("vposicion"));

			List<FormatoRequerimientoDTO> frLista=formatoReqService.listarFormatoRequerimientoServices(etapa) ;
			request.setAttribute("listarfr", frLista);
			sesion.setAttribute("v_posicion", etapa);
			request.getRequestDispatcher("/grillaFormatoReq.jsp").forward(request, response);
		}
		else if(vOpe.equals("cargaFormularioFormato"))
			this.cargarDatosParaFormularioDespuesDeLaGrilla(request,response);
		else if(vOpe.equals("registrarFormatoRequerimiento"))
			this.registrarFormularioRequerimiento(request,response);
		else if(vOpe.equals("firmaAutorizacion"))
			this.procesoFirmarAutorizacion(request,response);
		else if(vOpe.equals("procesoVerificaEnPaac"))
			this.procesoVerificarEnPaac(request,response);
		else if(vOpe.equals("restableceroperacion"))
			this.cargarAlJspLosEstadosDelFormatoOExpediente(request,response);
		else if(vOpe.equals("ejecutarRestlacerFormato"))
			this.ejecutarRestablecerFormato(request,response);
	}

	private void procesoVerificarEnPaac(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		formatoReqService.procesarLogFormato(getRegistroFormatoExpedienteDTO(request, response));

		request.getRequestDispatcher("/principal.jsp").forward(request, response);
	}

	private void procesoFirmarAutorizacion(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		formatoReqService.procesarLogFormato(getRegistroFormatoExpedienteDTO(request, response));

		request.getRequestDispatcher("/principal.jsp").forward(request, response);

	}

	private void registrarFormularioRequerimiento(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion=request.getSession(true);
		//Creando el formatorequerimiento a insertar
		FormatoRequerimientoDTO frDTO=new FormatoRequerimientoDTO();
		frDTO.setFecha(request.getParameter("fecha"));
		frDTO.setDescripcion(request.getParameter("descripcion"));
		UsuarioDTO objusuario=(UsuarioDTO)sesion.getAttribute("v_usuario");

		DetallePacDTO detalle=new DetallePacDTO();
		detalle.setCodDetallePac(Integer.parseInt(request.getParameter("escodigo")));

		//Creando el registro_formatoexpediente
		RegistroFormatoExpedienteDTO registro_f_e=new RegistroFormatoExpedienteDTO();
		registro_f_e.setUsuarioDTO(objusuario);
		registro_f_e.setPosicion(0);
		registro_f_e.setFormatoRequerimientoDTO(frDTO); //necesario para la persist()
		registro_f_e.setFirmado("y");

		//insertando valores al objeto fr
		frDTO.setDetallePacDTO(detalle);		
		Set<RegistroFormatoExpedienteDTO> registroFormatoExpedienteDtos=new HashSet<RegistroFormatoExpedienteDTO>();
		registroFormatoExpedienteDtos.add(registro_f_e); //
		frDTO.setRegistroFormatoExpedienteDTOs(registroFormatoExpedienteDtos);
		frDTO.setTipo("Bienes");

		formatoReqService.registrarFormatoRequerimientoService(frDTO);
		request.getRequestDispatcher("/principal.jsp").forward(request, response);
	}

	private void cargarDatosParaFormularioDespuesDeLaGrilla(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {

		HttpSession sesion=request.getSession(true);

		int codFormatoSeleccionadoEnLaGrilla=Integer.parseInt(request.getParameter("seleccionRadio"));
		int vPosicion=(int) sesion.getAttribute("v_posicion");

		sesion.setAttribute("codFormatoSeleccionaDeLaGrilla", codFormatoSeleccionadoEnLaGrilla);

		FormatoRequerimientoDTO objFormatoReq=formatoReqService.obtenerFormatoReqService(codFormatoSeleccionadoEnLaGrilla);
		DetallePacDTO objDetallePac=formatoReqService.obtenerDetallePacService(codFormatoSeleccionadoEnLaGrilla);
		List<Object[]> listFirmaUsua=formatoReqService.listarFirmaUsuarioService(codFormatoSeleccionadoEnLaGrilla);
		for (int i = 0; i < listFirmaUsua.size(); i++) {
			try {
				listFirmaUsua.get(i)[0]=DatatypeConverter.printBase64Binary((byte[]) listFirmaUsua.get(i)[0]);
			} catch (NullArgumentException e) {
				Logger.getLogger(null).error("No hay valor para convertir a imagen");
			}
		}
		request.setAttribute("r_objFormatoReq", objFormatoReq);
		request.setAttribute("r_listaDetalleProd", objDetallePac);
		request.setAttribute("r_listaFirmaUsua", listFirmaUsua);

		if(vPosicion==FIRMAR_AUTORIZACION)
			request.getRequestDispatcher("/firmarAutorizacionFormato.jsp").forward(request, response);			
		else if(vPosicion==VERIFICAR_SALDO_PRESUPUESTAL){

			SaldoPresupService saldoPresupuestalService=new SaldoPresupService();

			double saldoBase=saldoPresupuestalService.getSaldoBase();

			request.setAttribute("r_saldoBase", saldoBase);
			request.setAttribute("r_resultadoCalculado", saldoBase-objDetallePac.getValorRef());
			request.getRequestDispatcher("/verificarSaldoPresupuestal.jsp").forward(request, response);
		}
		else if(vPosicion==VERIFICAR_EN_PAC)
			request.getRequestDispatcher("/verificarDetallePacEnPac.jsp").forward(request, response);			
		else if(vPosicion==VERIFICAR_DATOS_JUL)
			request.getRequestDispatcher("/verificarDatosDelFormato.jsp").forward(request, response);
		else if(vPosicion==VERIFICAR_UIT){

			SaldoPresupService saldoPresupuestalService=new SaldoPresupService();

			double valor=saldoPresupuestalService.valorEnUit(codFormatoSeleccionadoEnLaGrilla);
			double uit1=saldoPresupuestalService.uitInicial();
			double uit2=saldoPresupuestalService.uitFinal();
			request.setAttribute("r_valor", valor);
			request.setAttribute("r_uit1", uit1);
			request.setAttribute("r_uit2", uit2);
			String resultado=null;

			if(valor>uit1 && valor<uit2)
				resultado="Yes";

			request.setAttribute("r_resultado", resultado);
			request.getRequestDispatcher("/verificarUit.jsp").forward(request, response);
		}
		else if(vPosicion==GENERAR_EXPEDIENTE)
			request.getRequestDispatcher("/generarExpediente.jsp").forward(request, response);

	}

	private void cargarAlJspLosEstadosDelFormatoOExpediente(
			HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {

		HttpSession sesion=request.getSession(true);
		UsuarioDTO usuario=(UsuarioDTO)sesion.getAttribute("v_usuario");
		int codUsuario=usuario.getCodUsuario();
		List<FormatoRequerimientoDTO> listaDeEstadoDeFormatos=formatoReqService.obtenerEstadosDeFormatosReq(codUsuario);
		List<FormatoRequerimientoDTO> listaDeEstadoDeExpedientes=formatoReqService.obtenerEstadosDeExpedienteCompra(codUsuario);

		sesion.setAttribute("r_listaDeEstadoDeFormatos", listaDeEstadoDeFormatos);
		sesion.setAttribute("r_listaDeEstadoDeExpedientes", listaDeEstadoDeExpedientes);

		request.getRequestDispatcher("/estadoDelFormatoOExpediente.jsp").forward(request, response);
	}


	private void ejecutarRestablecerFormato(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {

		String getProceso=(String)request.getParameter("tabla");

		int codFormato=Integer.parseInt(request.getParameter("codFormatoReq"));
		String posicion=(String)request.getParameter("codusuario");
		String aprobado=(String)request.getParameter("aprobado")+"";
		String firmado=(String)request.getParameter("firmado")+"";
		if(getProceso.equals("formato")){

			if(!aprobado.isEmpty()){
				aprobado=aprobado.equals("y")? "n": "y";
				formatoReqService.modificarEstadoDelFormato(codFormato, posicion, aprobado, firmado);
			}
			else
				firmado=firmado.equals("y")? "n":"y";
			formatoReqService.modificarEstadoDelFormato(codFormato, posicion, aprobado, firmado);
		}
		else if(getProceso.equals("expediente")){
			if(!aprobado.isEmpty()){
				aprobado=aprobado.equals("y")? "n": "y";
				formatoReqService.modificarEstadoDelExpediente(codFormato, posicion, aprobado, firmado);
			}
			else
				firmado=firmado.equals("y")? "n":"y";
			formatoReqService.modificarEstadoDelExpediente(codFormato, posicion, aprobado, firmado);

		}
		request.getRequestDispatcher("/principal.jsp").forward(request, response);			
	}

	public RegistroFormatoExpedienteDTO getRegistroFormatoExpedienteDTO(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {

		HttpSession lasesion=request.getSession(true);

		FormatoRequerimientoDTO formato=new FormatoRequerimientoDTO();
		formato.setCodFormato((int)lasesion.getAttribute("codFormatoSeleccionaDeLaGrilla"));
		UsuarioDTO usuario=((UsuarioDTO)lasesion.getAttribute("v_usuario"));
		int vPosicion=(int)lasesion.getAttribute("v_posicion");
		String aprobado=(String)request.getParameter("aprobado");
		String firmado=(String)request.getParameter("firmado");

		RegistroFormatoExpedienteDTO registro_f_e=new RegistroFormatoExpedienteDTO();
		registro_f_e.setFormatoRequerimientoDTO(formato);
		registro_f_e.setUsuarioDTO(usuario);
		registro_f_e.setPosicion(++vPosicion); //aumentamos la posicion
		registro_f_e.setAprobado(aprobado);
		registro_f_e.setFirmado(firmado);

		return registro_f_e;
	}

}
