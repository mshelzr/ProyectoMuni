<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verificacion del Saldo Presupuestal</title>
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
				<form action="SaldoPresupServlet" id="dataUsuarios">
					<input type="hidden" name="operacion" value="procesoVerificarSaldo">
			  <table class="control">
			  <tr>
			    <td colspan="4"><h3>Verificacion del Saldo Presupuestal:</h3></td>			  	
			  </tr>
			  <tr>
			  	<td colspan="4"><jsp:include page="bodyFormato.jsp"></jsp:include> </td>
			  </tr>
			  <tr >
			    <td align="right">Monto Base</td>
			    <td> <input type="text" disabled="disabled" value="${requestScope.r_saldoBase}"></td>
			  </tr>
			  <c:set var="resultadoCalculado" scope="request" value="${r_resultadoCalculado}"/>
			  	<c:choose>
			  	  <c:when test="${resultadoCalculado > 0}">
				  	  <tr align="right">
	   					<td><br/>Aprobar:<input type="radio" checked="checked" name="aprobado" value="y"><img src="imagenes/ico_check.PNG"/></td>
	   				  </tr>	
				  	  <tr align="right">
	   					<td>Rechazar:<input type="radio" name="aprobado" value="n"><img src="imagenes/ico_uncheck.PNG"/></td>
	   				  </tr>	   				  
				  </c:when>
				  <c:otherwise>
				   <tr align="right">
				  	<td><br/>Aprobar:<input type="radio" disabled="disabled" name="aprobado" value="y"><img src="imagenes/ico_check.PNG"/></td>
				   </tr>
				   <tr align="right">
				  	<td>Desaprobar:<input type="radio" checked="checked" name="aprobado" value="y"><img src="imagenes/ico_uncheck.PNG"/></td>
				   </tr>				   
				  </c:otherwise>
			  	</c:choose>
			  			  
			  <tfoot>
					<tr>
						<td align="center"><br/><br/>
							<input type="submit" value="Procesar" class="boton"> 
							<input type="reset" value="Cancelar" onclick="window.location.href='./principal.jsp';">
						</td>
					</tr>				
			  </tfoot>
		</table>		
				</form>
			</td>
		</tr>		
	</table>
		<jsp:include page="pie.jsp"></jsp:include>
   </div>
</body>
</html>