<%@page import="com.java.util.WorksheetSaveReusable"%>
<%@page import="com.java.seguridad.services.UsuarioService"%>
<%@page import="org.jmesa.model.TableModelUtils"%>
<%@page import="org.jmesa.web.HttpServletRequestWebContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa"%>
<%
	// set the collection in the request for JMesa tag lib
	String tableId = "taghome";
	UsuarioService objDao = new UsuarioService(new HttpServletRequestWebContext(request));

	WorksheetSaveReusable worksheetSaver = new WorksheetSaveReusable(objDao);
	TableModelUtils.saveWorksheet(tableId, request, worksheetSaver);

	request.setAttribute("saveResults", worksheetSaver.getSaveResults());
	request.setAttribute("usuaritos", objDao.getUsuaritos());
	request.setAttribute("newRecord", objDao.getNewObjeto());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mantenimiento</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/web.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css"></link>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.3.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery.bgiframe.pack.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery.jmesa.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jmesa.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
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
					  	<hr class="space" />
		<p>${saveResults}</p>
  	  <form name="beansitosForm" id="beansitosForm" action="mantenimientousuario.jsp">
  	  	<input type="hidden" name="type" value="taghome" />
		<jmesa:tableModel 
			id="taghome" 
			items="${usuaritos}" 
			maxRows="12"
			maxRowsIncrements="12,20,36" 
			stateAttr="restore" 
			var="bean"
			editable="true" 
			addedRowObject="${newRecord}">
			<jmesa:htmlTable 
				caption="Usuarios" 
				width="700px">
				<jmesa:htmlRow uniqueProperty="codUsuario">
					<jmesa:htmlColumn property="remove" title="&nbsp;" 
						sortable="false"
						filterable="false"
						worksheetEditor="org.jmesa.worksheet.editor.RemoveRowWorksheetEditor" />				
					<jmesa:htmlColumn property="nombre" title="Name"
						worksheetValidation="required:true"
						customWorksheetValidation="customFirstNameValidation:validateFirstName"
						errorMessage="customFirstNameValidation:You cannot use foo as a value" />
					<jmesa:htmlColumn property="ape_paterno" title="Last Name"
						worksheetValidation="required:true"
						customWorksheetValidation="customFirstNameValidation:validateFirstName"
						errorMessage="customFirstNameValidation:You cannot use foo as a value" />
					<jmesa:htmlColumn property="ape_materno" title="Second Last Name"
						worksheetValidation="required:true"
						customWorksheetValidation="customFirstNameValidation:validateFirstName"
						errorMessage="customFirstNameValidation:You cannot use foo as a value" />
					<jmesa:htmlColumn property="usuario" title="Usuario"
						worksheetValidation="required:true"
						customWorksheetValidation="customFirstNameValidation:validateFirstName"
						errorMessage="customFirstNameValidation:You cannot use foo as a value" />
					<jmesa:htmlColumn property="password" title="Password"
						worksheetValidation="required:true"
						customWorksheetValidation="customFirstNameValidation:validateFirstName"
						errorMessage="customFirstNameValidation:You cannot use foo as a value" />						
					<jmesa:htmlColumn property="perfilDTO.codPerfil" title="Perfil"
						worksheetValidation="required:true"
						customWorksheetValidation="customFirstNameValidation:validateFirstName"
						errorMessage="customFirstNameValidation:You cannot use foo as a value" />												
<%-- 					<jmesa:htmlColumn property="foto" title="foto"> --%>
<!-- 						<span class="file-wrapper"> -->
<!-- 						  <input type="file" name="photo" id="photo" /> -->
<!-- 						  <span class="button">Choose a Photo</span> -->
<!-- 						</span> -->
<%-- 					</jmesa:htmlColumn>	 --%>
				</jmesa:htmlRow>
			</jmesa:htmlTable>
		</jmesa:tableModel>
	  </form>	
	<hr class="space" /> 
	        </td>
		</tr>		
	</table>
		<jsp:include page="pie.jsp"></jsp:include>
   </div>
<script type="text/javascript">
       function onInvokeAction(id) {
           $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
       }

       // custom validation
       function validateFirstName(val) {
          if (val == 'foo') {
             return false;
          }

          return true;
       }
</script>	 
</body>
</html>