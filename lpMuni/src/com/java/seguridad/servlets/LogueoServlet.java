package com.java.seguridad.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.java.beans.MenuDTO;
import com.java.beans.UsuarioDTO;
import com.java.seguridad.services.LogueoService;

public class LogueoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LogueoService servicio=new LogueoService();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String vOpe=(String)request.getParameter("operacion");

		if(vOpe.equals("logueo")){
			String vUsuario=request.getParameter("usuario");
			String vClave=request.getParameter("clave");

			UsuarioDTO objCandidato=new UsuarioDTO();
			objCandidato.setUsuario(vUsuario);
			objCandidato.setPassword(vClave);
			UsuarioDTO objValidado=servicio.validaPorUsuario(objCandidato);

			if(objValidado!=null){
				if(vClave.equals(objValidado.getPassword())){
					List<MenuDTO> objMenu=servicio.buscarMenu_Serv(objValidado);
					if( request.isRequestedSessionIdValid()){
						HttpSession tempo = request.getSession(false);
						tempo.invalidate();
					}
					HttpSession lasesion=request.getSession(true);
					lasesion.setAttribute("v_usuario", objValidado );
					
					try {
						lasesion.setAttribute("contentFotoOnBase64", DatatypeConverter.printBase64Binary(objValidado.getFoto()));	
						lasesion.setAttribute("contentFirmaOnBase64", DatatypeConverter.printBase64Binary(objValidado.getFirma()));						
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
					lasesion.setAttribute("v_menu", objMenu);

					request.getRequestDispatcher("/principal.jsp").forward(request, response);

				}else{
					request.setAttribute("msg", "Clave incorrecta!!!");
					request.getRequestDispatcher("/logueo.jsp").forward(request, response);
				}

			}else{
				request.setAttribute("msg", "Usuario incorrecto!!!");
				request.getRequestDispatcher("/logueo.jsp").forward(request, response);
			}
		}else if(vOpe.equals("loguot")){

			HttpSession lasesion=request.getSession(true);
			lasesion.removeAttribute("v_usuario");
			request.getRequestDispatcher("/logueo.jsp").forward(request, response);
		}
	}

}
