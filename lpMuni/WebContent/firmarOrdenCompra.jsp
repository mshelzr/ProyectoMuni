<%@page import="java.util.List"%>
<%@page import="com.java.beans.MenuDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Importamos la librería de DisplayTag -->
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@taglib prefix="jmesa" uri="http://code.google.com/p/jmesa" %>
<!-- Importamos la librería de JMessa - URI obtenido del archivo jmesa.tld -->
<%@taglib prefix="jmesa" uri="http://code.google.com/p/jmesa" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Firmar Orden de Compra</title>
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
				<form action="OrdenCompraServlet" id="data">
					<input type="hidden" name="operacion" value="firmaOrdenCompra">
				    	<table class="control">
			  <tr>
			    <td colspan="4"><h3>Firmar Orden de Compra:</h3></td>			  	
			  </tr>
			  <tr>
			  	<td colspan="4">
			  		<jsp:include page="bodyFormato.jsp"></jsp:include>
					<jsp:include page="bodyExpediente.jsp"></jsp:include> 		  	
			  	</td>
			  </tr>
		  	  <tr align="center">
  					<td><br/>Firmar:<input type="radio" class="required" name="firmado" value="y"><img src="imagenes/ico_check.PNG"/></td>
  			  </tr>	
		  	  <tr align="center">
  					<td>Rechazar:<input type="radio" name="firmado" value="n"><img src="imagenes/ico_uncheck.PNG"/></td>
  			  </tr>
		  <tfoot>
			<tr>
				<td align="center"><br/><br/>
					<input type="submit" value="Procesar" class="boton" onclick="validaRadio();alert('Enviando...');"> 
					<input type="reset" value="Cancelar" onclick="window.location.href='./principal.jsp';">
				</td>
			</tr>				
		  </tfoot>		  
    </table >
				</form>
	        </td>
		</tr>		
	</table>
		<jsp:include page="pie.jsp"></jsp:include>
   </div>
</body>
</html>