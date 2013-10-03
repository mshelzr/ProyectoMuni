<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<title>Logueo</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="js/prototype/prototype.js" type="text/javascript"></script>
<script type="text/javascript">

function okButton(){
   $('#mybuttondialog').dialog('close');
 };
function validar(){
	
	var usuario=$F("usuario");
	var pass=$F("clave");

	var cad="";
	var todEsCorrecto = true;
	if( usuario.empty()){ cad+="Ingrese Usuario \n"; todEsCorrecto = false;	}
	if(	pass.empty()){ cad+="Ingrese Contraseña \n";todEsCorrecto = false;	}
	
	if(todEsCorrecto){
		document.forms[0].submit();
	}else{
		alert(cad);
		document.forms[0].usuario.focus();
	}
}
</script>
</head>
<body style="margin:10">
<form action="LogueoServlet" method="post">
<input type="hidden" name="operacion" value="logueo" />
<div align="center">
	<jsp:include page="cabecera.jsp"></jsp:include>
	<table width=955 >
		<tr>
			<td valign="top">
				<img src="imagenes/img/mainpic.gif" width=264 height=172><br>
				<table border=0 >
		            <tr>
		              <td><img src="imagenes/img/sideheader1.gif" width=264 height=32></td>
		            </tr>
	          	</table>
	 		    <table width="264" cellpadding=5 cellspacing=5 bgcolor="#A9A9A9" class="tabla">
					<tr>
						<td width="94" height=29 align="right">Usuario</td>
						<td width="100" height=29><input type="text"  name="usuario" /> </td>
					</tr>
					<tr>
						<td align="right">Contraseña</td>
						<td><input  type="password" name="clave" /></td>
					</tr>

		            <tr height=40 valign="bottom">
		                <td colspan="2" align="center"><input type="submit" 
								name="boton01" 
								value="Ingresar"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"></td>
					</tr>
										<tr>
						<td colspan="2" align="center"> <a href="#">Olvido su contraseña?</a> </td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						</td>
					</tr>
	            </table>
	            <table border=0 cellpadding=0 cellspacing=0>
        	      	<tr>
            	  		<td><img src="imagenes/img/sidefooter1.gif" width=264 height=26></td>
            		</tr>
            	</table>
	  		</td>
			<td width=691 height=400 valign="middle" align="center" style="padding:25px">
	               <img src="imagenes/img/llave.png">
	               <h1>Municipalidad de Ilo - Perú</h1> 
	        </td>
		</tr>		
	</table>
		<jsp:include page="pie.jsp"></jsp:include>
   </div>
	</form>
</body>
</html>