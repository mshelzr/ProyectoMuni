<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<jsp:useBean id="fechita" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generar Expediente de Bienes</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script src="http://jquery.bassistance.de/validate/additional-methods.js"></script>
</head>
<body style="margin:10">
<script>
	$(document).ready(function () {

		$( "#data" ).validate({
			  rules: {
			    field: {
			      required: true,
			      extension: "doc|docx"
			    }
			  }
			});
	});
</script>
<div align="center">
	<jsp:include page="cabecera.jsp"></jsp:include>
	<table width=955 >
		<tr>
			<td valign="top">
				<jsp:include page="menu.jsp"></jsp:include>
	  		</td>
			<td width=691 height=400 valign="middle"  style="padding:25px">
				<form action="RegistrarExpedienteServlet" id="data" enctype="multipart/form-data" method="post">
			    <table class="control">
			  <tr>
			    <td colspan="4"><h3>Generar Expediente de Bienes:</h3></td>			  	
			  </tr>
			  <tr>
			  	<td colspan="4"><jsp:include page="bodyFormato.jsp"></jsp:include> </td>
			  </tr>
			  <tr>
			    <td><br/>Cod Expediente</td>
			    <td><br/><input type="text" name="codexpediente" disabled="disabled"> </td>
			  </tr>			  
			  <tr>
			    <td>Descripcion Expediente</td>
			    <td><input type="text" name="descripcion" size="60" class="required"> </td>
			  </tr>
			  <tr>
			    <td>Fecha</td>
			    <td><input type="text" name="fecha" value="<fmt:formatDate value="${fechita}" type="date" pattern="yyyy-MM-dd" />" > </td>
			  </tr>
			  <tr>
			    <td colspan="30">Adjuntar: <input type="file" id="field" name="field" accept="application/msword"></td>
			  </tr>
		  	  <tr align="center">
  					<td><br/>Confirmar:<input type="radio" class="required" name="aprobado" value="y"><img src="imagenes/ico_check.PNG"/></td>
  			  </tr>	
		  	  <tr align="center">
  					<td>Rechazar:<input type="radio" name="aprobado" value="n"><img src="imagenes/ico_uncheck.PNG"/></td>
  			  </tr>			  
			  <tfoot>
				<tr>
					<td align="center"><br/><br/>
					<input type="submit" value="Generar" class="boton" > 
					<input type="reset" value="Cancelar" onclick="window.location.href='./principal.jsp';">
					</td>
				</tr>				  
				</table>	
				</form>
	        </td>
		</tr>		
	</table>
		<jsp:include page="pie.jsp"></jsp:include>
   </div>
</body>
</html>