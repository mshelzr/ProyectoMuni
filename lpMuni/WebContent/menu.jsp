<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Grupo Formato de la librería JSTL(Java Statndar Tag Library) -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%-- <%@taglib prefix="display" uri="http://displaytag.sf.net" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!-- Importamos una clase Java en un JSP -->
<jsp:useBean id="fechita" class="java.util.Date" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
				<img src="imagenes/img/mainpic.gif" width=264 height=172><br>
				<table border=0 >
		            <tr>
		              <td><img src="imagenes/img/sideheader2.gif" width=264 height=32></td>
		            </tr>
	          	</table>
	 		    <table width="264" cellpadding=5 cellspacing=5 bgcolor="#A9A9A9" class="tabla">
					<tr>
						<td><a href="principal.jsp" class="padeado"> Inicio </a></td>
					</tr>
					<tr class="enlaces">
						<td><c:forEach items="${sessionScope.v_menu}" var="fila">
								<a class="padeado" href="${fila.direccion}">${fila.descripcion}</a>
							</c:forEach></td>
					</tr>
					<tr>
						<td><a href="LogueoServlet?operacion=loguot" class="padeado" > Cerrar Sesion </a></td>
					</tr>
					<tr>
						<td class="control" align="right">
						<fmt:formatDate value="${fechita}" type="date" pattern="yyyy-MM-dd" /></td>
					</tr>
	            </table>
	            <table border=0 cellpadding=0 cellspacing=0>
        	      	<tr>
            	  		<td><img src="imagenes/img/sidefooter1.gif" width=264 height=26></td>
            		</tr>
            	</table>
</body>
</html>