<%@page import="com.java.beans.UsuarioDTO"%>
<%@page import="javax.mail.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.InputStream" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="org.apache.commons.io.IOUtils" %>
<%@page import="javax.xml.bind.DatatypeConverter;" %>
<%@taglib prefix="jmesa" uri="/WEB-INF/tld/jmesa.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
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
			if($(".radio").is(':checked')) {
				document.form1.submit();	
			} else {
				$("#mielemento").empty();
				$("#mielemento").append('* Seleccione una opción');
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
			<h2>Restablecer la aprobación ejecutada</h2>
			 <div class="alert-box warning"><span>Noticia: </span>
			 	Esta operación es sólo si cometió un error al aprobar o rechazar. Si ud no
			 	<br/>encuentra el formato o expediente, significa que la siguiente actividad que lo continua
			 	<br/>ha aprobado el proceso.
			 </div>
				<form action="FormatoServlet" name="form1" id="data">
					<input type="hidden" name="operacion" value="ejecutarRestlacerFormato">
					<input type="hidden" name="tabla" />
					<input type="hidden" name="codFormatoReq" />
					<input type="hidden" name="codusuario" />
				   	<input type="hidden" name="aprobado" />
				   	<input type="hidden" name="firmado" />
				</form>
				    <fieldset>
					   <legend>Formatos de Requerimiento</legend>
					      <form action="estadoDelFormatoOExpediente.jsp" name="form2" id="data">
					    	<table>
						    	<tr>
						    		<td>
						 		    	<jmesa:tableModel id="table" items="${sessionScope.r_listaDeEstadoDeFormatos}" var="data" maxRows="10" maxRowsIncrements="10,20,30,40">
			   				    		    <jmesa:htmlTable>
												<jmesa:htmlRow>
													<jmesa:htmlColumn title="Cod" property="codFormatoRequerimiento" />
													<jmesa:htmlColumn title="Actividad donde se paralizó" property="fecha" width="400" />
													<jmesa:htmlColumn title="Obs. del Formato" property="tipo" />
													<jmesa:htmlColumn title="Aprobado?" property="descripcion" />
													<jmesa:htmlColumn title="Firmado?" property="codDetallePac" />
													<jmesa:htmlColumn>
														<input type="radio" id="radio" class="radio" name="escodigo" onclick="document.form1.tabla.value='formato';document.form1.codFormatoReq.value='${data.codFormatoRequerimiento}';document.form1.codusuario.value='${data.codUsuario}';document.form1.aprobado.value='${data.descripcion}';document.form1.firmado.value='${data.codDetallePac}';document.form3.reset();" />
													</jmesa:htmlColumn>
													<jmesa:htmlColumn  title="" property="">
														 <c:set var="aprobado" scope="request" value="${data.descripcion}" />
														 <c:set var="firmado" scope="request" value="${data.codDetallePac}" />
														  	<c:choose>
														  	  <c:when test="${aprobado eq 'y' or firmado eq 'y'}">
											   					<img src="imagenes/ico_check.PNG" title="Aprobado" />
															  </c:when>
															  <c:otherwise>
											   					<img src="imagenes/ico_uncheck.PNG" title="Rechazado" />
															  </c:otherwise>
														  	</c:choose>
													</jmesa:htmlColumn>
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
					    <fieldset>
					    	<legend>Expedientes de Compra</legend>
					      <form action="estadoDelFormatoOExpediente.jsp" name="form3" id="data">
					    	<table>
						    	<tr>
						    		<td>
						 		    	<jmesa:tableModel id="table" items="${sessionScope.r_listaDeEstadoDeExpedientes}" var="data" maxRows="10" maxRowsIncrements="10,20,30,40">
			   				    		    <jmesa:htmlTable>
												<jmesa:htmlRow>
													<jmesa:htmlColumn title="Cod" property="codFormatoRequerimiento" />
													<jmesa:htmlColumn title="Actividad donde se paralizó" property="fecha" width="400"/>
													<jmesa:htmlColumn title="Obs. del Exp" property="tipo" />
													<jmesa:htmlColumn title="Aprobado?" property="descripcion" />
													<jmesa:htmlColumn title="Firmado?" property="codDetallePac" />
													<jmesa:htmlColumn>
														<input type="radio" id="radio" class="radio" name="escodigo" onclick="document.form1.tabla.value='expediente';document.form1.codFormatoReq.value='${data.codFormatoRequerimiento}';document.form1.codusuario.value='${data.codUsuario}';document.form1.aprobado.value='${data.descripcion}';document.form1.firmado.value='${data.codDetallePac}';document.form2.reset();" />
													</jmesa:htmlColumn>									
													<jmesa:htmlColumn title="" property="">
														 <c:set var="aprobado" scope="request" value="${data.descripcion}" />
														 <c:set var="firmado" scope="request" value="${data.codDetallePac}" />
														  	<c:choose>
														  	  <c:when test="${aprobado eq 'y' or firmado eq 'y'}">
											   					<img src="imagenes/ico_check.PNG" title="Aprobado" />
															  </c:when>
															  <c:otherwise>
											   					<img src="imagenes/ico_uncheck.PNG" title="Rechazado" />
															  </c:otherwise>
														  	</c:choose>
													</jmesa:htmlColumn>
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
				    		<input type="submit" id="registrar" value="Procesar">
							<input type="reset" value="Cancelar" onclick="window.location.href='./principal.jsp';">
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