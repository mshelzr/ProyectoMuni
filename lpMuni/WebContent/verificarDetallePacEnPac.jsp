<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verificacion dentro del PaaC</title>
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
			<form action="FormatoServlet" id="data">
				<input type="hidden" name="operacion" value="procesoVerificaEnPaac">
			  <table class="control">
			  <tr>
			    <td colspan="4"><h3>Verificacion dentro del PaaC:</h3></td>			  	
			  </tr>
			  <tr>
			  	<td colspan="4"><jsp:include page="bodyFormato.jsp"></jsp:include> </td>
			  </tr>
			  <tr>
			    <td>
			    <a target="_blank" href="docstore/Res004-2013-SERVIR-GG.pdf">ABRIR PAAC<img src="imagenes/ico_pdf.png"></a>
			    </td>
			  </tr>
		  	  <tr align="center">
  					<td><br/>Aprobar:<input type="radio" class="required" name="aprobado" value="y"><img src="imagenes/ico_check.PNG"/></td>
  			  </tr>	
		  	  <tr align="center">
  					<td>Rechazar:<input type="radio" name="aprobado" value="n"><img src="imagenes/ico_uncheck.PNG"/></td>
  			  </tr>						  
			  <tfoot>
					<tr>
						<td align="center"><br/><br/><input type="submit" value="Procesar" class="boton" onclick="validaRadio();"> 
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