<%@page import="com.java.beans.DetallePacDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.java.formato.services.FormatoRequerimientoService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jmesa" uri="/WEB-INF/tld/jmesa.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<jsp:useBean id="fechita" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generar Formato de Requerimiento de Bienes</title>
<link href="css/jmesa.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.jmesa.js"></script>
<script type="text/javascript" src="js/jmesa.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/generalizaciones.js"></script>
</head>
<body style="margin:10">
<script type="text/javascript">
	$(document).ready(function(){
		$("#registrar").click(function() {
			if($(".radio").is(':checked') && $("#descripcion").val()!="") {
				document.form1.submit();	
				$("#mielemento").append('<div class="alert-box success"><span>success: </span>Se generó el formato.</div>');	
				alert('Se generó el formato.');
			} else {
				$("#mielemento").empty();
				$("#mielemento").append('* Complete los campos');
			}
		});
	
	});
</script>
<%
	List<DetallePacDTO> lista=new FormatoRequerimientoService().listarDetallePac();
	request.setAttribute("r_lista", lista);
%>
<body style="margin:10">
<div align="center">
	<jsp:include page="cabecera.jsp"></jsp:include>
	<table width=955 >
		<tr>
			<td valign="top">
				<jsp:include page="menu.jsp"></jsp:include>
	  		</td>
			<td width=691 height=400 valign="middle"  style="padding:25px">
			<h2>Generar Formato de Requerimiento</h2>
			     <fieldset>
			    <legend>Formato Requerimiento</legend>
			      <form action="FormatoServlet" name="form1" id="data">
					<input type="hidden" name="operacion" value="registrarFormatoRequerimiento"> 
					<input type="hidden" name="escodigo"/>   	
			    	<table>
					<tr>
			    		<td>Cod Formato</td>
				    	<td><input type="text" disabled="disabled" name="codigo"></td>
				    	<td>Cod Usuario</td>
				    	<td><input type="text" name="codusuario" disabled="disabled" value="${sessionScope.v_usuario.usuario }"></td>
			   		</tr>
			   		<tr>
			   			<td>Fecha</td>
					    <td><input type="text" value="<fmt:formatDate value="${fechita}" type="date" pattern="yyyy-MM-dd" />" name="fecha"></td>
			   			<td>Tipo</td> 
					    <td><select disabled="disabled">
							  <option value="bienes" >Bienes</option>
							  <option value="servicios">Servicios</option>
							</select></td>
					</tr>
					<tr>
						<td><br/>Observaciones:</td>	  
					    <td colspan="4"><br/><input type="text" id="descripcion" name="descripcion" size="80" /> </td> 
					</tr>
					</table>
				  </form>
						<br/><br/><br/>   
					    <fieldset>
					    	<legend>Detalle Pac</legend>
					      <form action="generarFormato.jsp" name="form2" id="data2">
					    	<table>
						    	<tr>
						    		<td>
						 		    	<jmesa:tableModel id="table" items="${requestScope.r_lista}" var="data" maxRows="6" maxRowsIncrements="6,12,18,24">
			   				    		    <jmesa:htmlTable>
												<jmesa:htmlRow>
													<jmesa:htmlColumn>
														<input type="radio" id="radio" class="radio" name="escodigo" onclick="document.form1.escodigo.value='${data.codDetallePac}'" />
													</jmesa:htmlColumn>
													<jmesa:htmlColumn title="Objeto" property="objeto" width="400" />
													<jmesa:htmlColumn title="Valor Ref" property="valorRef" />
													<jmesa:htmlColumn title="Fecha de Convocatoria" property="fechaConvocatoria" />
												</jmesa:htmlRow>    		    
							    		    </jmesa:htmlTable>
			    						</jmesa:tableModel>
						    		</td>
						    	</tr>
								<tr>
									<td>
										<div id="mielemento" style="color: red;font-size: 15px;">
										</div>
									</td>
								</tr>    
							</table>
						  </form>   
					    </fieldset>
					    <br/><br/><input type="submit" id="registrar" value="Generar">
								  <input type="reset" value="Cancelar" onclick="window.location.href='./principal.jsp';">
						
						</fieldset>
	        </td>
		</tr>		
	</table>
		<jsp:include page="pie.jsp"></jsp:include>
   </div>
</body>
<script type="text/javascript">
        function onInvokeAction(id) {
            createHiddenInputFieldsForLimitAndSubmit(id);

        }
        
 </script>
 </html>