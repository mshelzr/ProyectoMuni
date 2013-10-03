package com.java.saldopresup.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.beans.FormatoRequerimientoDTO;
import com.java.beans.RegistroFormatoExpedienteDTO;
import com.java.beans.UsuarioDTO;
import com.java.formato.services.FormatoRequerimientoService;

public class SaldoPresupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FormatoRequerimientoService formatoreqService=new FormatoRequerimientoService();
		
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
		
		formatoreqService.procesarLogFormato(registro_f_e);
		
		request.getRequestDispatcher("/principal.jsp").forward(request, response);
	}

}
