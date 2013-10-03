<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="jmesa" uri="http://code.google.com/p/jmesa" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<fieldset>
    	<legend>Formato Requerimiento</legend>
    	<table>
    	<tr>
    		<td>Cod Formato</td>
	    	<td><input type="text" value="${requestScope.r_objFormatoReq.codFormato}" disabled="disabled" name="codigo"></td>
	    	<td>CodUsuario</td>
	    	<td><input type="text" disabled="disabled" value="${requestScope.r_objFormatoReq.detallePacDTO.codDetallePac}"></td>
   		</tr>
   		<tr>
   			<td>Fecha</td>
		    <td><input type="text" value="${requestScope.r_objFormatoReq.fecha}" disabled="disabled"></td>		    
	    	<td>Tipo</td> 
		    <td><select disabled="disabled">
				  <option value="bienes" >Bienes</option>
				  <option value="servicios">Servicios</option>
				</select></td> 	
	   <tr>		  
		    <td><br/>Descripcion</td> 
		    <td colspan="4"><br/><input type="text" value="${requestScope.r_objFormatoReq.descripcion}" disabled="disabled" size="80" ></td> 
		</tr>
		</table>  
    </fieldset>
	<fieldset>
    	<legend>Detalle Paac</legend>
    	<table>
    	<tr>
    		<td>Objeto</td>
	    	<td colspan="4"><input type="text" size="80" value="${requestScope.r_listaDetalleProd.objeto}" disabled="disabled" name="codigo"></td>
		</tr>
   		<tr>
   		   	<td>Valor Ref</td>
	    	<td><input type="text" disabled="disabled" value="${requestScope.r_listaDetalleProd.valorRef}"></td>
   		
   			<td>Fecha Convocatoria</td>
		    <td><input type="text" value="${requestScope.r_listaDetalleProd.fechaConvocatoria}" disabled="disabled"></td>
		</tr>
		</table>  
    </fieldset>
	<fieldset >
		<legend>Firmas</legend>
    	<jmesa:tableModel id="datito" items="${requestScope.r_listaFirmaUsua}" var="data"  view="com.java.util.CustomView">
    		    <jmesa:htmlTable>
					<jmesa:htmlRow>
						<jmesa:htmlColumn>
						  	  <img src="data:image/jpeg;base64,${data[0]}" >
						</jmesa:htmlColumn>
						<jmesa:htmlColumn title="Autoridad Firmante  :" property="" ><br/>				
							<ins>${data[1]}</ins>
							<br>${data[2]}
						</jmesa:htmlColumn>
					</jmesa:htmlRow>
    		    </jmesa:htmlTable>
    	</jmesa:tableModel> 
	 </fieldset>
</body>
</html>