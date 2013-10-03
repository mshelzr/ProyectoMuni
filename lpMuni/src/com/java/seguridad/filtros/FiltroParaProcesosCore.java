package com.java.seguridad.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.beans.UsuarioDTO;
import com.java.util.Constantes;

public class FiltroParaProcesosCore implements Filter {

    public FiltroParaProcesosCore() {
    }
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession sesion=req.getSession(true);
		
		String urlTry = req.getServletPath();
		UsuarioDTO userLoged=(UsuarioDTO)sesion.getAttribute("v_usuario");
		if(userLoged!=null){ //si el codPerfil existe procede
			
			//Comprobamos si la pag que desea entrar está dentro de su privilegio
			if(Constantes.getPrivilegiosPorPerfil(userLoged.getPerfilDTO().getCodPerfil(), urlTry)) 
				chain.doFilter(request, response);
			else if(urlTry.equals("/principal.jsp")) //lo direccionamos a principal si no es su rol
				chain.doFilter(request, response);
			else
				res.sendRedirect("principal.jsp");
		}
		else
			res.sendRedirect("logueo.jsp"); // a logueo si aún no se loggea(intruso)
	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}


}
