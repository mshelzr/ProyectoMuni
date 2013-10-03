<%@page import="java.util.List"%>
<%@page import="com.java.beans.MenuDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Importamos la librería de DisplayTag -->
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>

<!-- Importamos la librería de JMessa - URI obtenido del archivo jmesa.tld -->
<%@taglib prefix="jmesa" uri="http://code.google.com/p/jmesa" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa.css"></link>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/generalizaciones.js"></script>
</head>
<body style="margin:10">
<div align="center">
	<jsp:include page="cabecera.jsp"></jsp:include>
	<table width=955 >
		<tr>
			<td valign="top">
				<jsp:include page="menu.jsp"></jsp:include>
	  		</td>
			<td width=691 height=400 valign="middle"  style="padding:25px">
				<form action="ExpedienteServlet" id="data">
					<input type="hidden" name="operacion" value="cargaFormularioExpediente">
				    <table>
				  	  <tr>
					    <td colspan="2"><h2>Seleccione Expediente de Compra:</h2></td>
					  </tr>
					  <tr>
					    <td><br/>
					    	<!-- id  Es identificador para JavaScript y var es el identificador para Java-Html -->
					    	<jmesa:tableModel id="datito" items="${requestScope.listarec}" var="data" >
					    		    <jmesa:htmlTable>
										<jmesa:htmlRow>
											<jmesa:htmlColumn title="">
													<input type="radio" name="seleccionRadio" class="required" value="${data.codExpediente}"/>
											</jmesa:htmlColumn>					
											<jmesa:htmlColumn title="Descripcion" width="300" property="descripcion" sortOrder="asc,desc" />
											<jmesa:htmlColumn title="Fecha" width="70" property="fecha" sortOrder="asc,desc" />
										</jmesa:htmlRow>    		    
					    		    </jmesa:htmlTable>
					    	</jmesa:tableModel>
					    </td>
					  </tr>
					    <tr align="center" >
							<td colspan="2" > <input type="submit" value="Seleccionar" class="boton" onclick="validaRadio();"></td>
				  		</tr>
				    </table >
				</form>
	        </td>
		</tr>		
	</table>
		<jsp:include page="pie.jsp"></jsp:include>
   </div>
</body>
</html>


