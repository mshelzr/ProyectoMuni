<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="jmesa" uri="http://code.google.com/p/jmesa" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<fieldset>
		<legend>Expediente de Compra</legend>
		<table>
			<tr>
				<td>Cod Expediente</td>
				<td><input type="text"
					value="${requestScope.r_objExpedienteComp.codExpediente}"
					disabled="disabled" name="codigo"></td>
				<td>Fecha</td>
				<td><input type="text" disabled="disabled"
					value="${requestScope.r_objExpedienteComp.fecha}"></td>
			</tr>
			<tr>
				<td>Descripcion</td>
				<td><input type="text"
					value="${requestScope.r_objExpedienteComp.descripcion}"
					disabled="disabled"></td>
		</table>
	</fieldset>
	<fieldset>
		<legend>Documentos Enlazados</legend>
		<table>
			<tr>
				<td colspan="4">
				<jmesa:tableModel id="datito" items="${requestScope.r_listaDocumentosDelExpediente}" var="data" view="com.java.util.CustomView">
						<jmesa:htmlTable>
							<jmesa:htmlRow>
								<jmesa:htmlColumn>
									<c:set var="extencion" scope="request" value="${data}"/>
									  	<c:choose>
										  	  <c:when test="${fn:contains(extencion, '.doc')}">				  	  	
													<a href="${data}" class="control"><img src="imagenes/ico_word.jpg"> ${data}</a>		  
											  </c:when>
											  <c:otherwise>
													<a href="${data}" class="control"><img src="images/table/excel.gif"> ${data}</a>		   
											  </c:otherwise>
										  </c:choose>
								</jmesa:htmlColumn>
							</jmesa:htmlRow>
						</jmesa:htmlTable>
					</jmesa:tableModel></td>
			</tr>
		</table>
	</fieldset>
</body>
</html>