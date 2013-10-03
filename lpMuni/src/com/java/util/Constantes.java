package com.java.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Constantes {
	
	public static final int ORIGENDATOS=6; //6 - Hibernate
	
	public static String getFecha(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	
	public static boolean getPrivilegiosPorPerfil(int codPerfil,String urlTry){
		String[] representante={"/generarFormato.jsp"};
		String[] gerente={"/firmararAutorizacionFormato.jsp"};
		String[] tecnicopresup={"/verificarSaldoPresupuestal"};
		String[] gerenteadm={"/verificarDetallePacEnPac"};
		String[] jefeul={"/verificarDatosDelFormato.jsp","/aprobarSolicitudCotizacion.jsp","/escojerCuadroComparativo.jsp","/firmarOrdenCompra.jsp"};
		String[] tecnicoadm={"/verificarUit.jsp"};
		String[] tecnicosnp={"/generarExpediente.jsp","/invitarProveedores.jsp","/enviarCuadroComparativo.jsp"};
		String[] tenicocontable={"/generarOrdenCompra.jsp"};
		String[] administrador={"/mantenimientodetallepac.jsp","/mantenimientomenu.jsp","/mantenimientoperfil.jsp","/mantenimientoproveedor.jsp","/mantenimientousuario.jsp"};
		
		Map<String,String[]> arreglo=new HashMap<String, String[]>();
		arreglo.put("perfil1",representante);
		arreglo.put("perfil2",gerente);
		arreglo.put("perfil3",tecnicopresup);
		arreglo.put("perfil4",gerenteadm);
		arreglo.put("perfil5",jefeul);
		arreglo.put("perfil6",tecnicoadm);
		arreglo.put("perfil7",tecnicosnp);
		arreglo.put("perfil8",tenicocontable);
		arreglo.put("perfil9",administrador);
		
		String key="perfil"+codPerfil;
		
		for(String valor:arreglo.get(key)){
			if(valor.equals(urlTry))
				return true;
		}
		return false;
	}
	
}
