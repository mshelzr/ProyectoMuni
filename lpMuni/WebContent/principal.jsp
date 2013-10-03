<%@page import="com.java.beans.UsuarioDTO"%>
<%@page import="javax.mail.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.io.InputStream"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="javax.xml.bind.DatatypeConverter;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
</head>
<body style="margin: 10">
	<div align="center">
		<jsp:include page="cabecera.jsp"></jsp:include>
		<table width=955>
			<tr>
				<td valign="top"><jsp:include page="menu.jsp"></jsp:include></td>
				<td width=691 height=400 valign="middle" style="padding: 25px">
					<table class="control">
						<tr>
							<td colspan="3"><h2>
									Bienvenido estimado <b style="color: grey;">${sessionScope.v_usuario.nombre}</b>
									sus datos son:
								</h2></td>
						</tr>
						<tr>
							<td>Nombre</td>
							<td>${sessionScope.v_usuario.nombre }</td>
							<td rowspan="8" align="right"><img
								src="data:image/jpeg;base64,${sessionScope.contentFotoOnBase64}" /></td>
						</tr>
						<tr>
							<td>Apellido Paterno</td>
							<td width="300">${sessionScope.v_usuario.ape_paterno }</td>
						</tr>
						<tr>
							<td>Apellido Materno</td>
							<td>${sessionScope.v_usuario.ape_materno }</td>
						</tr>
						<tr>
							<td>Usuario</td>
							<td>${sessionScope.v_usuario.usuario }</td>
						</tr>
						<tr>
							<td>Password</td>
							<td>***</td>
						</tr>
						<tr>
							<td>Perfil</td>
							<td>${sessionScope.v_usuario.perfilDTO.descripcion}</td>
						</tr>
						<tr>
							<td>Firma</td>
						</tr>
						<tr>
							<td colspan="2"><img
								src="data:image/jpeg;base64,${sessionScope.contentFirmaOnBase64}" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<jsp:include page="pie.jsp"></jsp:include>
	</div>
</body>
</html>